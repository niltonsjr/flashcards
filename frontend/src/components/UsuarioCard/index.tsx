import { AxiosRequestConfig } from "axios";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Usuario } from "types/usuario";
import { requestBackend } from "util/requests";
import "./styles.css";

type Props = {
  usuario: Usuario;
  onDelete: Function;
};

const UsuarioCard = ({ usuario, onDelete }: Props) => {
  const navigate = useNavigate();

  const handleDelete = (usuarioId: number) => {
    if (!window.confirm("Al borrar el usuario, se borrarán todas las tarjetas y categorias asociadas a este usuario. \n¿Está seguro que desea borrar el usuario?")) {
      return;
    }

    const config: AxiosRequestConfig = {
      method: "DELETE",
      url: `/usuarios/${usuarioId}`,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      onDelete();
      toast.success("Usuario eliminado.");
    }).catch((error) => {
      toast.error(`Error al eliminar el usuario: ${error.response.data.message}`);
    });
  };

  const handleEdit = () => {
    navigate(`/admin/usuarios/${usuario.id}`);
  }

  return (
    <div className="usuario-card-container base-card">
      <div className="usuario-card-datos">
        <div>
          <h4>
            {usuario.nombre} {usuario.apellidos} - {usuario.nombreDeUsuario}
          </h4>
        </div>
        <div>
          {usuario.roles.map((rol) => (
            <span key={rol.id} className="badge bg-secondary">
              {rol.nombre.split("_")[1]}
            </span>
          ))}
        </div>
      </div>
      <div className="usuario-card-buttons-container">
        <button
          type="button"
          onClick={handleEdit}
          className="btn btn-outline-success boton-usuario-card fw-bold"
        >
          Editar
        </button>

        <button
          type="button"
          onClick={() => handleDelete(usuario.id)}
          className="btn btn-outline-danger boton-usuario-card fw-bold"
        >
          Borrar
        </button>
      </div>
    </div>
  );
};

export default UsuarioCard;
