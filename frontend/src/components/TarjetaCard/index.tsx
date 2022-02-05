import { AxiosRequestConfig } from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Categoria } from "types/categoria";
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

  // const [selectCategoria, setSelectCategoria] = useState<Categoria>();

  // useEffect(() => {
  //   const config: AxiosRequestConfig = {
  //     method: "GET",
  //     url: `/categorias/${tarjeta.categoria}`,
  //     withCredentials: true,
  //   };

  //   requestBackend(config).then((response) => {
  //     setSelectCategoria(response.data);
  //   });
    
  // }, [tarjeta.categoria]);

  return (
    <>
      <form className="tarjeta-card-container base-card">
        <div className="tarjeta-select-container">
          <select
            className="form-select tarjeta-filter-crud-select bg-white"
            aria-label="Default select example"
            defaultValue={tarjeta.categoria.id}
            disabled
          >
            <option value={tarjeta.categoria.id}>
              {tarjeta.categoria.nombre}
            </option>
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
            <Link to={`/admin/tarjetas/${tarjeta.id}`}>
              <button
                type="button"
                className="btn btn-outline-success boton-tarjeta-card fw-bold"
              >
                Editar
              </button>
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
