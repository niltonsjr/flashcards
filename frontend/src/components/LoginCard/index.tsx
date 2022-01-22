import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import { requestBackendLogin } from "util/requests";
import "./styles.css";

type FormData = {
  username: string;
  password: string;
};

const LoginCard = () => {
  const { register, handleSubmit } = useForm<FormData>();
  const onSubmit = (formData: FormData) => {
    requestBackendLogin(formData)
      .then((response) => {
        console.log("Suceso", response);
      })
      .catch((error) => {
        console.log("Erro:", error);
      });
  };

  return (
    <div className="login-card">
      <h1>Iniciar sesión</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="mb-5">
          <input
            {...register("username")}
            type="text"
            className="form-control base-input"
            placeholder="Nombre de Usuario"
            name="username"
          />
        </div>
        <div className="mb-2">
          <input
            {...register("password")}
            type="password"
            className="form-control base-input "
            placeholder="Contraseña"
            name="password"
          />
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
