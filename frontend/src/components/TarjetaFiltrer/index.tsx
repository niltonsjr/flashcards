import { Link } from "react-router-dom";
import { ReactComponent as SearchIcon } from "assets/images/search-icon.svg";
import "./styles.css";

const TarjetaFilter = () => {
  return (
    <div className="tarjeta-filter-crud-container base-card">
      <form action="" className="tarjeta-filter-form">
        <div className="tarjeta-filter-text-container">
          <input
            type="text"
            className="form-control base-input bg-white"
            placeholder="Buscar por texto"
          />
          <SearchIcon />
        </div>
        <div className="tarjeta-filter-bottom-container">
          <div className="tarjeta-filter-categoria-contaier">
            <select
              className="form-select tarjeta-filter-crud-select bg-white"
              aria-label="Default select example"
              defaultValue={"categoria"}
            >
              <option value="categoria">Categoría</option>
              <option value="1">One</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </select>
          </div>
          <button className="btn btn-outline-secondary">Limpiar</button>
        </div>
      </form>
      <Link to="/admin/tarjetas/nueva">
        <button className="btn btn-primary text-white fw-bold btn-tarjeta-crud-nueva">
          Nueva tarjeta
        </button>
      </Link>
    </div>
  );
};

export default TarjetaFilter;
