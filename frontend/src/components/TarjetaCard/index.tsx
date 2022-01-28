import { Tarjeta } from "types/tarjeta";
import "./styles.css";

type Props = {
  tarjeta: Tarjeta;
};

const TarjetaCard = ({ tarjeta }: Props) => {
  return (
    <div className="tarjeta-card-container">
      <div className="tarjeta-frontal-container">
        <label htmlFor="cardFrontalTextArea" className="tarjeta-label">
          Frontal
        </label>
        <textarea
          className="form-control mb-2 bg-white fs-6"
          id="cardFrontalTextArea"
          rows={5}
          // readOnly
        >
          {tarjeta.frontal}
        </textarea>
      </div>
      <div className="tarjeta-trasera-container">
        <label htmlFor="cardTraseraTextArea" className="tarjeta-label">
          Trasera
        </label>
        <textarea
          className="form-control mb-2 bg-white fs-6"
          id="cardTraseraTextArea"
          rows={5}
          // readOnly
        >
          {tarjeta.trasera}
        </textarea>
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
      </div>
    </div>
  );
};

export default TarjetaCard;
