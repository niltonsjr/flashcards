import { Link } from "react-router-dom";
import "./styles.css";

const LoginCard = () => {
  return (
    <div className="login-card">
      <h1>Iniciar sesión</h1>
      <form>
        <div className="mb-5">
          <input
            type="text"
            className="form-control base-input"
            placeholder="Nombre de Usuario"
            name="nombreUsuario"
          />
        </div>
        <div className="mb-2">
          <input
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
          <button className="login-submit">Aceptar</button>
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
