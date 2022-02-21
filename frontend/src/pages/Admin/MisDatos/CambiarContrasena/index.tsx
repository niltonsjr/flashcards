import { useForm } from "react-hook-form";
import "./styles.css";

const CambiarContrasena = () => {
  return (
    <div className="cambiar-contrasena-container mt-3 container-fluid">
      <form onSubmit={() => {}}>
        <div className="contrasena-actual-container  row ">
          <div className="col-12">
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
          <div className="col-6">
            <button
              type="button"
              className="mis-datos-buttom validar-contrasena-actual-buttom col-12"
              onClick={() => {}}
            >
              Validar
            </button>
          </div>
        </div>
        <div className="contrasena-nueva-container  row  mt-3">
          <div className="col-12">
            <label htmlFor="nueva-contrasena" className="form-label">
              Nueva contraseña
            </label>
            <input
              type="text"
              id="nueva-contrasena"
              className="form-control base-input bg-white"
              name="nueva-contrasena"
            />
          </div>
          <div className="col-12">
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
          <div className="col-12">
            <button
              type="button"
              className="mis-datos-buttom mis-datos-editar-buttom col-6"
              onClick={() => {}}
            >
              Aceptar
            </button>          
            <button
              type="button"
              className="mis-datos-buttom mis-datos-editar-buttom col-6"
              onClick={() => {}}
            >
              Cancelar
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default CambiarContrasena;
