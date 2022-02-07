import { AxiosRequestConfig } from "axios";
import { Categoria } from "types/categoria";
import { requestBackend } from "util/requests";
import "./styles.css";

type Props = {
  categoria: Categoria;
  onDelete: Function;
};

const CategoriaCard = ({ categoria, onDelete }: Props) => {
  const handleDelete = (categoriaId: number) => {
    if (!window.confirm("¿Está seguro que desea borrar la categoría?")) {
      return;
    }

    const config: AxiosRequestConfig = {
      method: "DELETE",
      url: `/tarjetas/${categoriaId}`,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      onDelete();
    });
  };

  return (
    <form className="base-card categoria-card-container">
      <input
        type="text"
        className="form-control base-inpu"
        placeholder={categoria.nombre}
        name="username"
      />
      <div className="categoria-card-button-container">
        <button
          type="button"
          onClick={() => {}}
          className="btn btn-outline-success boton-categoria-card fw-bold"
        >
          Editar
        </button>
        <button
          type="button"
          onClick={() => handleDelete(categoria.id)}
          className="btn btn-outline-danger boton-categoria-card fw-bold"
        >
          Borrar
        </button>
      </div>
    </form>
  );
};

export default CategoriaCard;
