import { AxiosRequestConfig } from "axios";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import ReactTooltip from "react-tooltip";
import { Usuario } from "types/usuario";
import { requestBackend, requestBackendRegister } from "util/requests";
import { getAuthData } from "util/storage";
import "./styles.css";

type RegisterData = {
  nombreDeUsuario: string;
  contrasena: string;
  confirmaContrasena: string;
  nombre: string;
  apellidos: string;
  email: string;
};

const MisDatos = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
    getValues
  } = useForm<RegisterData>();

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

  const onSubmit = (registerData: RegisterData) => {
    requestBackendRegister(registerData)
      .then((response) => {
        console.log("Suceso", response);
      })
      .catch((error) => {
        console.log("Erro:", error);
      });
  };

  return (
    <div className="mis-datos-container">
      <div className="mis-datos-form-container">
        <form onSubmit={handleSubmit(onSubmit)} className="row">
          <div className="row row-cols-lg-2 g-3">
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
          <div className="row row-cols-lg-2 g-3">
            <div className="col-12">
              <label htmlFor="nombre" className="form-label">
                Nombre:
              </label>
              <input
                {...register("nombre")}
                type="text"
                id="nombre"
                className="form-control base-input"
                name="nombre"
                disabled
              />
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
                disabled
              />
            </div>
          </div>
          <div className="row row-cols-lg-1 g-3">
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
                disabled
              />
            </div>
          </div>
          {/* <div className="row row-cols-lg-2 g-3">
            <div className="col-12">
              <label htmlFor="contrasena" className="form-label">
                Contraseña:
              </label>
              <input
                {...register("contrasena", {
                  required: "Campo obligatorio",
                  pattern: {
                    value: new RegExp(
                      "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
                    ),
                    message: "La contraseña no cumple con las condiciones.",
                  },
                })}
                type="password"
                id="contrasena"
                className="form-control base-input"
                name="contrasena"
                data-tip
                data-for="condicionesContrasenaTooltip"
                disabled
              />
              <div className="invalid-feedback d-block">
                {errors.contrasena?.message}
              </div>
              <ReactTooltip
                id="condicionesContrasenaTooltip"
                place="bottom"
                effect="solid"
              >
                <p>
                  Condiciones de la contraseña: <br />
                  - Mínimo 8 caracteres. <br />
                  - Al menos una letra mayúscula, una minúscula y un número.
                  <br />
                  - Puede contener caracteres especiales.
                  <br />
                </p>
              </ReactTooltip>
            </div>
            <div className="col-12">
              <label htmlFor="confirmaContrasena" className="form-label">
                Confirmar contraseña:
              </label>
              <input
                {...register("confirmaContrasena", {
                  required: "Confirme la contraseña.",
                  validate: {
                    matchPreviousPassword: (value) => {
                      const { contrasena } = getValues();
                      return (
                        contrasena === value || "Contraseñas deben coincidir."
                      );
                    },
                  },
                })}
                type="password"
                id="confirmaContrasena"
                className="form-control base-input"
                name="confirmaContrasena"
                disabled
              />
              <div className="invalid-feedback d-block">
                {errors.confirmaContrasena?.message}
              </div>
            </div>
          </div> */}
          <div className="row row-cols-lg-2 g-3">
            <div className="col-12">
              {/* <button type="submit" className="mis-datos-editar-buttom col-12 ">
                Editar
              </button> */}
            </div>
            <div className="col-12">
              <button type="submit" className="mis-datos-editar-buttom col-12">
                Editar
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default MisDatos;
