import linea from "assets/images/line-small.svg";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import { requestBackendRegister } from "util/requests";
import ReactTooltip from "react-tooltip";
import { toast } from "react-toastify";
import "./styles.css";

type RegisterData = {
  nombreDeUsuario: string;
  contrasena: string;
  confirmaContrasena: string;
  nombre: string;
  apellidos: string;
  email: string;
  terms: boolean;
};

const Register = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    getValues,
  } = useForm<RegisterData>();
  const navigate = useNavigate();

  const onSubmit = (registerData: RegisterData) => {
    requestBackendRegister(registerData)
      .then((response) => {
        console.log("Suceso", response);
        toast.success("Usuario creado correctamente.");
        navigate("/auth/login");
      })
      .catch((error) => {
        console.log("Erro:", error);
        toast.error(
          `Error al crear el usuario: \n
           ${error.response.data.errors.map(
             (e: { fieldName: string; fieldMessage: string }) => {
               return e.fieldName
                 .concat(": ")
                 .concat(e.fieldMessage)
                 .concat("\n");
             }
           )}`
        );
      });
  };

  return (
    <div className="register-page-container">
      <div className="register-container">
        <div className="register-header">
          <img src={linea} alt="linea" />
          <h1>Regístrate para empezar a crear tus tarjetas</h1>
          <img src={linea} alt="linea" />
        </div>
        <hr />
        <div className="register-form-container">
          <form onSubmit={handleSubmit(onSubmit)} className="row">
            <div className="row row-cols-lg-2 g-3">
              <div className="col-12">
                <label htmlFor="nombreDeUsuario" className="form-label">
                  Nombre de usuario:
                </label>
                <input
                  {...register("nombreDeUsuario", {
                    required: {
                      value: true,
                      message: "Campo obligatorio",
                    },
                    pattern: {
                      value:
                        /^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$/,
                      message:
                        "El nombre de usuario no cumple con las condiciones.",
                    },
                  })}
                  type="text"
                  id="nombreDeUsuario"
                  className="form-control base-input"
                  name="nombreDeUsuario"
                  data-tip
                  data-for="condicionesUsuarioTooltip"
                />
                <div className="invalid-feedback d-block">
                  {errors.nombreDeUsuario?.message}
                </div>
                <ReactTooltip
                  id="condicionesUsuarioTooltip"
                  place="top"
                  effect="solid"
                >
                  <p>
                    Condiciones del nombre de usuario: <br />
                    - Tener una longitud de entre 5 y 20 caracteres. <br />-
                    Puede contener letras mayúsculas o minúsculas y números.{" "}
                    <br />- Las letras acentuadas y las eñes no están admitidas.{" "}
                    <br />
                    - Puede contener los siguientes símbolos: <br />
                    punto (.), guión bajo (_), guión medio (-) siempre que no se
                    encuentren seguidos.
                    <br />- Debe iniciar y terminal con un caracter
                    alfanumérico.
                  </p>
                </ReactTooltip>
              </div>
            </div>
            <div className="row row-cols-lg-2 g-3">
              <div className="col-12">
                <label htmlFor="contrasena" className="form-label">
                  Contraseña:
                </label>
                <input
                  {...register("contrasena", {
                    required: {
                      value: true,
                      message: "Campo obligatorio",
                    },
                    pattern: {
                      value:
                        /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/,
                      message: "La contraseña no cumple con las condiciones.",
                    },
                  })}
                  type="password"
                  id="contrasena"
                  className="form-control base-input"
                  name="contrasena"
                  data-tip
                  data-for="condicionesContrasenaTooltip"
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
                />
                <div className="invalid-feedback d-block">
                  {errors.confirmaContrasena?.message}
                </div>
              </div>
            </div>
            <div className="row row-cols-lg-2 g-3">
              <div className="col-12">
                <label htmlFor="nombre" className="form-label">
                  Nombre:
                </label>
                <input
                  {...register("nombre", {
                    required: {
                      value: true,
                      message: "Campo obligatorio",
                    },
                  })}
                  type="text"
                  id="nombre"
                  className="form-control base-input"
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
                  {...register("apellidos", {
                    required: {
                      value: true,
                      message: "Campo obligatorio",
                    },
                  })}
                  type="text"
                  id="apellidos"
                  className="form-control base-input"
                  name="apellidos"
                />
                <div className="invalid-feedback d-block">
                  {errors.apellidos?.message}
                </div>
              </div>
            </div>
            <div className="row row-cols-lg-1 g-3">
              <div className="col-12">
                <label htmlFor="email" className="form-label">
                  Correo electrónico:
                </label>
                <input
                  {...register("email", {
                    required: {
                      value: true,
                      message: "Campo obligatorio",
                    },
                    pattern: {
                      value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                      message: "Correo electrónico inválido.",
                    },
                  })}
                  type="email"
                  id="email"
                  className="form-control base-input"
                  name="email"
                />
                <div className="invalid-feedback d-block">
                  {errors.email?.message}
                </div>
              </div>
            </div>
            <div className="row row-cols-lg-2 g-3">
              <div className="col-12">
                <div className="form-check">
                  <input
                    {...register("terms", {
                      required: {
                        value: true,
                        message: "Campo obligatorio",
                      },
                    })}
                    className="form-check-input"
                    type="checkbox"
                    value=""
                    id="flexCheckDefault"
                  />

                  <label
                    className="form-check-label"
                    htmlFor="flexCheckDefault"
                  >
                    Acepto los{" "}
                    <Link
                      to="/condiciones"
                      className="conditions-link-register"
                    >
                      Términos y Condiciones
                    </Link>{" "}
                    y doy mi consentimiento para el uso de datos tal y como se
                    detalla en la Política de Privacidad.
                  </label>
                  <div className="invalid-feedback d-block">
                    {errors.terms?.message}
                  </div>
                </div>
              </div>
              <div className="col-12">
                <button type="submit" className="register-submit col-12">
                  Aceptar
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
