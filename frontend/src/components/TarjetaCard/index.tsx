import { Tarjeta } from "types/tarjeta";
import "./styles.css";

type Props = {
  tarjeta: Tarjeta;
};

const TarjetaCard = ({ tarjeta }: Props) => {
  return (
    <>
      <form className="tarjeta-card-container base-card">
        <div className="tarjeta-select-container">
          <select
            className="form-select tarjeta-filter-crud-select bg-white"
            aria-label="Default select example"
            defaultValue={"categoria"}
            disabled
          >
            <option value="categoria">Categoría</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
          </select>
        </div>
        <div className="tarjeta-text-buttons-container">
          <div className="tarjeta-textarea-container">
            <div className="tarjeta-frontal-container">
              <label htmlFor="cardFrontalTextArea" className="tarjeta-label">
                Frontal
              </label>
              <textarea
                className="form-control mb-2 bg-white fs-6"
                id="cardFrontalTextArea"
                rows={5}
                readOnly
              >
                {tarjeta.frontal}
              </textarea>
            </div>
            <div className="tarjeta-trasera-container">
              <label htmlFor="cardTraseraTextArea" className="tarjeta-label">
                Trasera
              </label>
              <textarea
                className="form-control mb-2 bg-white fs-6 h-auto"
                id="cardTraseraTextArea"
                rows={5}
                readOnly
              >
                {tarjeta.trasera}
              </textarea>
            </div>
          </div>
          <div className="tarjeta-butons-container">
            <button
              type="button"
              className="btn btn-outline-success boton-tarjeta-card fw-bold"
            >
              Editar
            </button>
            <button
              type="button"
              className="btn btn-outline-danger boton-tarjeta-card fw-bold"
            >
              Borrar
            </button>

            {/* 
         
            <button
              type="button"
              className="btn btn-outline-success boton-tarjeta-card fw-bold"
            >
              Guardar
            </button>
            <button
              type="button"
              className="btn btn-outline-danger boton-tarjeta-card fw-bold"
            >
              Cancelar
            </button>
          */}
          </div>
        </div>
      </form>
    </>
  );
};

export default TarjetaCard;
