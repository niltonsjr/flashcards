import { useState } from "react";
import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import { requestBackendLogin } from "util/requests";
import "./styles.css";

type FormData = {
  username: string;
  password: string;
};

const LoginCard = () => {
  const [hasError, setHasError] = useState(false);
  
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>();

  const onSubmit = (formData: FormData) => {
    requestBackendLogin(formData)
      .then((response) => {
        setHasError(false);
        console.log("Suceso", response);
      })
      .catch((error) => {
        setHasError(true);
        console.log("Erro:", error);
      });
  };

  return (
    <div className="login-card">
      <h1>Iniciar sesión</h1>
      {hasError && (
        <div className="alert alert-danger">
          Ha ocurrido un error al intentar realizar el login.
        </div>
      )}
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="mb-4">
          <input
            {...register("username", {
              required: "Campo obligatorio",
              pattern: {
                value: new RegExp(
                  "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$"
                ),
                message:
                  "El nombre de usuario debe tener entre 5 y 20 caracteres alfanuméricos." ,
              },
            })}
            type="text"
            className="form-control base-input"
            placeholder="Nombre de Usuario"
            name="username"
          />
          <div className="invalid-feedback d-block">
            {errors.username?.message}
          </div>
        </div>
        <div className="mb-2">
          <input
            {...register("password", { required: "Campo obligatorio" })}
            type="password"
            className="form-control base-input "
            placeholder="Contraseña"
            name="password"
          />
          <div className="invalid-feedback d-block">
            {errors.password?.message}
          </div>
        </div>
        <Link to="/admin/auth/recover" className="login-link-recover">
          ¿Olvidó su contraseña?
        </Link>
        <div>
          <button type="submit" className="login-submit">
            Aceptar
          </button>
        </div>
        <div className="signup-container">
          <Link to="/register" className="login-link-register">
            Regístrate gratis
          </Link>
        </div>
      </form>
    </div>
  );
};

export default LoginCard;
