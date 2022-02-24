import { AxiosRequestConfig } from "axios";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import Select from "react-select";
import { Rol } from "types/rol";
import { requestBackend } from "util/requests";
import "./styles.css";

export type UsuarioFilterData = {
  rol: Rol | null;
};

type Props = {
  onSubmitFilter: (data: UsuarioFilterData) => void;
};

const UsuariosFilter = ({ onSubmitFilter }: Props) => {
  const [selectRoles, setSelectRoles] = useState<Rol[]>([]);
  const { handleSubmit, setValue, getValues, control } =
    useForm<UsuarioFilterData>();

  const onSubmit = (formData: UsuarioFilterData) => {
    onSubmitFilter(formData);
  };

  const handleChangeRol = (value: Rol) => {
    setValue("rol", value);
    const obj: UsuarioFilterData = {
      rol: getValues("rol"),
    };
    onSubmitFilter(obj);
  };

  useEffect(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/roles",
      withCredentials: true,
    };
    requestBackend(config).then((response) => {
      setSelectRoles(response.data.content);
    });
  }, []);

  return (
    <div className="usuario-filter-crud-container base-card">
      <form onSubmit={handleSubmit(onSubmit)} className="usuario-filter-form">
        <div className="usuario-filter-select-container">
          <Controller
            name="rol"
            control={control}
            render={({ field }) => (
              <Select
                classNamePrefix="usuario-filter-select"
                options={selectRoles}
                getOptionLabel={(r: Rol) => r.nombre.split("_")[1]}
                getOptionValue={(r: Rol) => String(r.id)}
                placeholder="Rol"
                isClearable
                onChange={(value) => handleChangeRol(value as Rol)}
              />
            )}
          />
        </div>
      </form>
      <Link to="/admin/usuarios/nuevo">
        <button className="btn btn-primary text-white fw-bold btn-usuario-crud-nueva">
          Nuevo Usuario
        </button>
      </Link>
    </div>
  );
};

export default UsuariosFilter;
