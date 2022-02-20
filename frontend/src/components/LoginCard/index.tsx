import { AuthContext } from "AuthContext";
import { useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import { getTokenData } from "util/auth";
import { requestBackendLogin } from "util/requests";
import { saveAuthData } from "util/storage";
import "./styles.css";

type CredentialsDTO = {
  username: string;
  password: string;
};

const LoginCard = () => {
  const { setAuthContextData } = useContext(AuthContext);
  const [hasError, setHasError] = useState(false);
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CredentialsDTO>();

  const onSubmit = (formData: CredentialsDTO) => {
    requestBackendLogin(formData)
      .then((response) => {
        saveAuthData(response.data);
        setAuthContextData({
          authenticated: true,
          tokenData: getTokenData(),
        });
        navigate("/admin/tarjetas");
        setHasError(false);
      })
      .catch((error) => {
        setHasError(true);
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
            })}
            type="text"
            className={`form-control base-input ${
              errors.username ? "is-invalid" : ""
            }`}
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
            className={`form-control base-input ${
              errors.password ? "is-invalid" : ""
            }`}
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
          <Link to="/auth/register" className="login-link-register">
            Regístrate gratis
          </Link>
        </div>
      </form>
    </div>
  );
};

export default LoginCard;
