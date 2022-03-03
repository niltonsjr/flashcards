import { AxiosRequestConfig } from "axios";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { Link, useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import { toast } from "react-toastify";
import { Rol } from "types/rol";
import { Usuario } from "types/usuario";
import { requestBackend } from "util/requests";
import "./styles.css";

type UrlParams = {
  usuarioId: string;
};

const UsuariosForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    control,
  } = useForm<Usuario>();
  const { usuarioId } = useParams<UrlParams>();
  const navigate = useNavigate();
  const isEditing = usuarioId !== "nuevo";
  const [selectRoles, setSelectRoles] = useState<Rol[]>([]);

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

  useEffect(() => {
    if (isEditing) {
      const config: AxiosRequestConfig = {
        method: "GET",
        url: `/usuarios/basico/${usuarioId}`,
        withCredentials: true,
      };

      requestBackend(config).then((response) => {
        const usuario = response.data as Usuario;
        setValue("nombreDeUsuario", usuario.nombreDeUsuario);
        setValue("nombre", usuario.nombre);
        setValue("apellidos", usuario.apellidos);
        setValue("email", usuario.email);
        setValue("roles", usuario.roles);
        console.log(usuario);
      });
    }
  }, [isEditing, setValue, usuarioId]);

  const onSubmit = (formData: Usuario) => {
    const data = {
      ...formData,
      nombreDeUsuario: formData.nombreDeUsuario,
      nombre: formData.nombre,
      apellidos: formData.apellidos,
      email: formData.email,
      roles: formData.roles,
      contrasena: isEditing ? null : 123456
    };

    const config: AxiosRequestConfig = {
      method: isEditing ? "PUT" : "POST",
      url: isEditing ? `/usuarios/${usuarioId}` : `/usuarios`,
      data,
      withCredentials: true,
    };

    requestBackend(config)
      .then(() => {
        toast.success(
          `Usuario ${isEditing ? "actualizado" : "creado"} de forma correcta.`
        );
        navigate("/admin/usuarios");
      })
      .catch(() => {
        toast.error(
          `Ocurrió un error al ${
            isEditing ? "actualizar" : "crear"
          } el usuario.`
        );
      });
  };

  const handleCancel = () => {
    navigate("/admin/usuarios");
  };

  return (
    <div className="usuario-datos-container base-card">
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="row row-cols-lg-2 g-3 mb-3">
          <div className="col-12">
            <label htmlFor="nombreDeUsuario" className="form-label">
              Nombre de usuario:
            </label>
            <input
              {...register("nombreDeUsuario")}
              type="text"
              id="nombreDeUsuario"
              className="form-control base-input bg-white"
              name="nombreDeUsuario"
            />
          </div>
        </div>
        <div className="tarjeta-select-container mb-3">
          <Controller
            name="roles"
            control={control}
            rules={{ required: true }}
            render={({ field }) => (
              <Select
                {...field}
                classNamePrefix="tarjeta-filter-select"
                options={selectRoles}
                getOptionLabel={(rol: Rol) => rol.nombre.split("_")[1]}
                getOptionValue={(rol: Rol) => String(rol.id)}
                placeholder="Roles"
                isClearable
                isMulti
              />
            )}
          />
          {errors.roles && (
            <div className="invalid-feedback d-block">Campo obligatorio.</div>
          )}
        </div>
        <div className="row row-cols-lg-2 g-3 mb-3">
          <div className="col-12">
            <label htmlFor="nombre" className="form-label">
              Nombre:
            </label>
            <input
              {...register("nombre", {
                required: "Campo obligatorio",
              })}
              type="text"
              id="nombre"
              className="form-control base-input bg-white"
              name="nombre"
            />
            <div className="invalid-feedback d-block">
              {errors.nombre?.message}
            </div>
          </div>
          <div className="col-12">
            <label htmlFor="apellidos" className="form-label">
              Apellidos:
            </label>
            <input
              {...register("apellidos")}
              type="text"
              id="apellidos"
              className="form-control base-input bg-white"
              name="apellidos"
            />
          </div>
        </div>
        <div className="row row-cols-lg-1 g-3 mb-3">
          <div className="col-12">
            <label htmlFor="email" className="form-label">
              Correo electrónico:
            </label>
            <input
              {...register("email")}
              type="email"
              id="email"
              className="form-control base-input bg-white"
              name="email"
            />
          </div>
        </div>
        {isEditing && (
          <div className="row row-cols-lg-1 g-3 mb-3 mis-datos-cambiar-contrasena-link">
            <Link to="/admin/cambiar-contrasena">Resetear contraseña</Link>
          </div>
        )}
        <div className="row row-cols-lg-2 row-cols-sm-2 g-3">
          <div className="col-12">
            <button
              type="button"
              className="mis-datos-buttom mis-datos-cancelar-buttom col-12"
              onClick={handleCancel}
            >
              Cancelar
            </button>
          </div>
          <div className="col-12">
            <button
              type="submit"
              className="mis-datos-buttom mis-datos-editar-buttom col-12"
            >
              Aceptar
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default UsuariosForm;
