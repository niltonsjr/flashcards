import TarjetaCard from "components/TarjetaCard";
import { Link } from "react-router-dom";
import "./styles.css";

const List = () => {
  const tarjeta = {
    id: 3,
    frontal: "Capital de la provincia de Sevilla.",
    trasera: "Sevilla.",
    conocida: true,
    fechaUltimaRespuesta: "2020-07-13T20:50:07Z",
    totalConocidas: 3,
    totalNoConocidas: 1,
    categoriaId: 1,
    usuarioId: 1,
  };

  return (
    <>
      <div className="tarjeta-filter-crud base-card">
        <select
          className="form-select tarjeta-filter-crud-select bg-white"
          aria-label="Default select example"
        >
          <option selected>Categoría</option>
          <option value="1">One</option>
          <option value="2">Two</option>
          <option value="3">Three</option>
        </select>

        <Link to="/admin/tarjetas/create">
          <button className="btn btn-primary text-white fw-bold btn-tarjeta-crud-nueva">
            Nueva tarjeta
          </button>
        </Link>
      </div>

      <div className="tarjetas-list-container">
        <TarjetaCard tarjeta={tarjeta} />
        <TarjetaCard tarjeta={tarjeta} />
        <TarjetaCard tarjeta={tarjeta} />
      </div>
    </>
  );
};

export default List;
