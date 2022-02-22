import { useForm } from "react-hook-form";
import "./styles.css";

const CambiarContrasena = () => {
  return (
    <div className="cambiar-contrasena-container container-fluid">
      <form onSubmit={() => {}}>
        <div className="contrasena-actual-container row">
          <div className="">
            <label htmlFor="contrasena-actual" className="form-label">
              Contraseña actual
            </label>
            <input
              type="text"
              id="contrasena-actual"
              className="form-control base-input bg-white"
              name="contrasena-actual"
            />
          </div>
          <div className="validar-contrasena-actual-buttom-container">
            <button
              type="button"
              className="mis-datos-buttom validar-contrasena-actual-buttom"
              onClick={() => {}}
            >
              Validar
            </button>
          </div>
        </div>
        <div className="contrasena-nueva-container">
          <div>
            <label htmlFor="nueva-contrasena" className="form-label">
              Nueva contraseña
            </label>
            <input
              type="text"
              id="nueva-contrasena"
              className="form-control base-input bg-white  contrasena-nueva-input"
              name="nueva-contrasena"
            />
          </div>
          <div>
            <label htmlFor="confirm-nueva-contrasena" className="form-label">
              Confirmar nueva contraseña
            </label>
            <input
              type="text"
              id="confirm-nueva-contrasena"
              className="form-control base-input bg-white"
              name="confirm-nueva-contrasena"
            />
          </div>
          <div className="cambiar-contrasena-buttons-container">
            <button
              type="button"
              className="mis-datos-buttom mis-datos-cancelar-buttom col-6"
              onClick={() => {}}
            >
              Cancelar
            </button>
            <button
              type="button"
              className="mis-datos-buttom mis-datos-editar-buttom col-6"
              onClick={() => {}}
            >
              Aceptar
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default CambiarContrasena;
