import { ReactComponent as SearchIcon } from "assets/images/search-icon.svg";
import { AxiosRequestConfig } from "axios";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import Select from "react-select";
import { Categoria } from "types/categoria";
import { requestBackend } from "util/requests";
import "./styles.css";

type TarjetaFilterData = {
  texto: string;
  categoria: Categoria;
};

const TarjetaFilter = () => {
  const [selectCategorias, setSelectCategorias] = useState<Categoria[]>([]);

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

  const { register, handleSubmit, control } = useForm<TarjetaFilterData>();

  const onSubmit = (formData: TarjetaFilterData) => {
    console.log("enviou", formData);
  };

  return (
    <div className="tarjeta-filter-crud-container base-card">
      <form onSubmit={handleSubmit(onSubmit)} className="tarjeta-filter-form">
        <div className="tarjeta-filter-text-container bg-white">
          <input
            {...register("texto")}
            type="text"
            name="texto"
            className="form-control base-input bg-white"
            placeholder="Buscar por texto"
          />
          <button className="tarjeta-filter-search-icon bg-white">
            <SearchIcon />
          </button>
        </div>
        <div className="tarjeta-filter-bottom-container">
          <div className="tarjeta-filter-categoria-contaier">
            <Controller
              name="categoria"
              control={control}
              render={({ field }) => (
                <Select
                  {...field}
                  classNamePrefix="tarjeta-filter-select"
                  options={selectCategorias}
                  getOptionLabel={(cat: Categoria) => cat.nombre}
                  getOptionValue={(cat: Categoria) => String(cat.id)}
                  placeholder="Categoría"
                  isClearable
                />
              )}
            />
          </div>
          <button className="btn btn-outline-secondary">
            Limpiar <span className="btn-tarjeta-filter-word">filtro</span>
          </button>
        </div>
      </form>
      <Link to="/admin/tarjetas/nueva">
        <button className="btn btn-primary text-white fw-bold btn-tarjeta-crud-nueva">
          Nueva Tarjeta
        </button>
      </Link>
    </div>
  );
};

export default TarjetaFilter;
