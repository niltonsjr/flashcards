import { AxiosRequestConfig } from "axios";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Categoria } from "types/categoria";
import { requestBackend } from "util/requests";
import "./styles.css";

type Props = {
  categoria: Categoria;
  onDelete: Function;
};

const CategoriaCard = ({ categoria, onDelete }: Props) => {
  const navigate = useNavigate();

  const handleDelete = (categoriaId: number) => {
    if (
      !window.confirm(
        "Al borrar una categoría, se borrarán todas las tarjetas asociadas a esta categoría. \n¿Está seguro que desea borrar la categoría?"
      )
    ) {
      return;
    }
    const config: AxiosRequestConfig = {
      method: "DELETE",
      url: `/categorias/${categoriaId}`,
      withCredentials: true,
    };

    requestBackend(config)
      .then(() => {
        onDelete();
        toast.success("Categoría eliminada.");
      })
      .catch((error) => {
        console.log(error.response.data.status);
      });
  };

  const handleEdit = (id: number) => {
    navigate(`/admin/categorias/${id}`);
  };

  return (
    <form className="base-card categoria-card-container">
      <span className="fw-normal">{categoria.nombre}</span>

      <div className="categoria-card-button-container">
        <button
          type="button"
          onClick={() => handleEdit(categoria.id)}
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
