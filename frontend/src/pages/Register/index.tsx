import { Link } from "react-router-dom";
import "./styles.css";

const Register = () => {
  return (
    <div className="register-container">
      <h1>
        Regístrate para empezar a <br /> crear tus tarjetas
      </h1>
      <div className="register-form-container">
        <form>
          <div className="mb-3">
            <label htmlFor="nombreUsuario" className="form-label">
              Nombre de usuario:
            </label>
            <input
              type="text"
              id="nombreUsuario"
              className="form-control base-input"
              name="nombreUsuario"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="contrasena" className="form-label">
              Contraseña:
            </label>
            <input
              type="password"
              id="contrasena"
              className="form-control base-input"
              name="contrasena"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="confirmContrasena" className="form-label">
              Confirmar contraseña:
            </label>
            <input
              type="password"
              id="confirmContrasena"
              className="form-control base-input"
              name="confirmContrasena"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="nombre" className="form-label">
              Nombre:
            </label>
            <input
              type="text"
              id="nombre"
              className="form-control base-input"
              name="nombre"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="apellidos" className="form-label">
              Apellidos:
            </label>
            <input
              type="text"
              id="apellidos"
              className="form-control base-input"
              name="apellidos"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Correo electrónico:
            </label>
            <input
              type="email"
              id="email"
              className="form-control base-input"
              name="email"
            />
          </div>
          <div className="form-check">
            <input
              className="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckDefault"
            />
            <label className="form-check-label" htmlFor="flexCheckDefault">
              Acepto los <Link to="/condiciones" className="conditions-link-register">Términos y Condiciones</Link> y doy mi consentimiento para el
              uso de datos tal y como se detalla en la Política de Privacidad.
            </label>
          </div>
          <div>
            <button className="register-submit">Aceptar</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register;
