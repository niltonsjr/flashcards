import { AxiosRequestConfig } from "axios";
import { toast } from "react-toastify";
import { Usuario } from "types/usuario";
import { requestBackend } from "util/requests";
import "./styles.css";

type Props = {
  usuario: Usuario;
  onDelete: Function;
};

const UsuarioCard = ({ usuario, onDelete }: Props) => {
  const handleDelete = (usuarioId: number) => {
    if (!window.confirm("¿Está seguro que desea borrar la tarjeta?")) {
      return;
    }

    const config: AxiosRequestConfig = {
      method: "DELETE",
      url: `/usuarios/${usuarioId}`,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      onDelete();
      toast.success("Usuario eliminada.");
    });
  };

  return (
    <div>
      <h1>{usuario.nombre}</h1>
    </div>
  );
};

export default UsuarioCard;
