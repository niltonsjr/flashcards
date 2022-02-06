import { AxiosRequestConfig } from "axios";
import { Link } from "react-router-dom";
import { Tarjeta } from "types/tarjeta";
import { requestBackend } from "util/requests";
import "./styles.css";

type Props = {
  tarjeta: Tarjeta;
  onDelete: Function;
};

const TarjetaCard = ({ tarjeta, onDelete }: Props) => {
  const handleDelete = (tarjetaId: number) => {
    if (!window.confirm("¿Está seguro que desea borrar la tarjeta?")) {
      return;
    }

    const config: AxiosRequestConfig = {
      method: "DELETE",
      url: `/tarjetas/${tarjetaId}`,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      onDelete();
    });
  };

  console.log(tarjeta);

  return (
    <>
      <form className="tarjeta-card-container base-card">
        <div>
          <p>
            Categoría:{" "}
            <span className="badge bg-secondary">
              {tarjeta.categoria.nombre}
            </span>
          </p>
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
                defaultValue={tarjeta.frontal}
                rows={5}
                readOnly
              />
            </div>
            <div className="tarjeta-trasera-container">
              <label htmlFor="cardTraseraTextArea" className="tarjeta-label">
                Trasera
              </label>
              <textarea
                className="form-control mb-2 bg-white fs-6 h-auto"
                id="cardTraseraTextArea"
                defaultValue={tarjeta.trasera}
                rows={5}
                readOnly
              />
            </div>
          </div>
          <div className="tarjeta-butons-container">
            <Link
              to={`/admin/tarjetas/${tarjeta.id}`}
              className="btn btn-outline-success boton-tarjeta-card fw-bold"
            >
              {/* <button
                type="button"
                className="btn btn-outline-success boton-tarjeta-card fw-bold"
              > */}
              Editar
              {/* </button> */}
            </Link>

            <button
              type="button"
              onClick={() => handleDelete(tarjeta.id)}
              className="btn btn-outline-danger boton-tarjeta-card fw-bold"
            >
              Borrar
            </button>
          </div>
        </div>
      </form>
    </>
  );
};

export default TarjetaCard;
