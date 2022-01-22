import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import "./styles.css";

type FormData = {
  nombreUsuario: string;
  contrasena: string;
};

const LoginCard = () => {
  const { register, handleSubmit } = useForm<FormData>();
  const onSubmit = (formData: FormData) => {
    console.log(formData);
  };

  return (
    <div className="login-card">
      <h1>Iniciar sesión</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="mb-5">
          <input
            {...register("nombreUsuario")}
            type="text"
            className="form-control base-input"
            placeholder="Nombre de Usuario"
            name="nombreUsuario"
          />
        </div>
        <div className="mb-2">
          <input
            {...register("contrasena")}
            type="password"
            className="form-control base-input "
            placeholder="Contraseña"
            name="contrasena"
          />
        </div>
        <Link to="/admin/auth/recover" className="login-link-recover">
          ¿Olvidó su contraseña?
        </Link>
        <div>
          <button type="submit" className="login-submit">Aceptar</button>
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
