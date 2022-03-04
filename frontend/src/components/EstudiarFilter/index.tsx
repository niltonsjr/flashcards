import { AxiosRequestConfig } from "axios";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import Select from "react-select";
import { Categoria } from "types/categoria";
import { requestBackend } from "util/requests";
import "./styles.css";

export type EstudiarFilterData = {
  categoria: Categoria | null;
};

type Props = {
  onSubmitFilter: (data: EstudiarFilterData) => void;
};

const EstudiarFilter = ({ onSubmitFilter }: Props) => {
  const [selectCategorias, setSelectCategorias] = useState<Categoria[]>([]);

  const { handleSubmit, setValue, getValues, control } =
    useForm<EstudiarFilterData>();

  const onSubmit = (formData: EstudiarFilterData) => {
    onSubmitFilter(formData);
  };

  const handleChangeCategoria = (value: Categoria) => {
    setValue("categoria", value);
    const obj: EstudiarFilterData = {
      categoria: getValues("categoria"),
    };

    onSubmitFilter(obj);
  };

  useEffect(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/categorias",
      withCredentials: true,
    };

    requestBackend(config).then((response) => {
      setSelectCategorias(response.data.content);
    });
  }, []);

  return (
    <div className="estudiar-filter-crud-container base-card">
      <form onSubmit={handleSubmit(onSubmit)} className="estudiar-filter-form">
        <div className="estudiar-filter-categoria-contaier">
          <Controller
            name="categoria"
            control={control}
            render={({ field }) => (
              <Select
                {...field}
                classNamePrefix="estudiar-filter-select"
                options={selectCategorias}
                getOptionLabel={(cat: Categoria) => cat.nombre}
                getOptionValue={(cat: Categoria) => String(cat.id)}
                placeholder="Categoría"
                isClearable
                onChange={(value) => handleChangeCategoria(value as Categoria)}
              />
            )}
          />
        </div>
      </form>
    </div>
  );
};

export default EstudiarFilter;
