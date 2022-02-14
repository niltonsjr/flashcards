import { AxiosRequestConfig } from "axios";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import { Usuario } from "types/usuario";
import { requestBackend } from "util/requests";
import { getAuthData } from "util/storage";
import "./styles.css";

const MisDatos = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    getValues,
  } = useForm<Usuario>();
  const [isEditing, setIsEditing] = useState<boolean>(false);
  
  const loggedUser = getAuthData();


  useEffect(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: `/usuarios/basico/${loggedUser.usuarioId}`,
      withCredentials: true,
    };

    requestBackend(config).then((response) => {
      const usuario = response.data as Usuario;
      setValue("nombreDeUsuario", usuario.nombreDeUsuario);
      setValue("nombre", usuario.nombre);
      setValue("apellidos", usuario.apellidos);
      setValue("email", usuario.email);
    });
  }, [loggedUser.usuarioId, setValue]);

  const onSubmit = (formData: Usuario) => {
    const data = {
      ...formData,
      usuarioId: loggedUser.usuarioId,
      nombre: formData.nombre,
      apellidos: formData.apellidos,
      email: formData.email,
    };

    const config: AxiosRequestConfig = {
      method: "PUT",
      url: `/usuarios/${loggedUser.usuarioId}`,
      data,
      withCredentials: true,
    };

    requestBackend(config)
      .then((response) => {
        console.log("Suceso", response);
        setIsEditing(false);
      })
      .catch((error) => {
        console.log("Erro:", error);
      });
  };

  const handleEdit = (event: React.MouseEvent) => {
    event.preventDefault();
    setIsEditing(true);
  };

  const handleCancel = (event: React.MouseEvent) => {
    event.preventDefault();
    setIsEditing(false);
  };

  return (
    <div className="mis-datos-container">
      <div className="mis-datos-form-container">
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
                className="form-control base-input"
                name="nombreDeUsuario"
                disabled
              />
            </div>
            
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
                className="form-control base-input"
                name="nombre"
                disabled={!isEditing}
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
                className="form-control base-input"
                name="apellidos"
                disabled={!isEditing}
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
                className="form-control base-input"
                name="email"
                disabled={!isEditing}
              />
            </div>
          </div>
          {!isEditing && (
            <div className="row row-cols-lg-1 g-3 mb-3 mis-datos-cambiar-contrasena-link">
              <Link to="cambiar_contraseña">Cambiar contraseña</Link>
            </div>
          )}

          <div className="row row-cols-lg-2 row-cols-sm-2 g-3">
            {isEditing ? (
              <>
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
              </>
            ) : (
              <>
                <div className="col-12"></div>
                <div className="col-12">
                  <button
                    type="button"
                    className="mis-datos-buttom mis-datos-editar-buttom col-12"
                    onClick={handleEdit}
                  >
                    Editar
                  </button>
                </div>
              </>
            )}
          </div>
        </form>
      </div>
    </div>
  );
};

export default MisDatos;
