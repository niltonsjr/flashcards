import { AxiosRequestConfig } from "axios";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { Categoria } from "types/categoria";
import { requestBackend } from "util/requests";
import { getAuthData } from "util/storage";
import "./styles.css";

type UrlParams = {
  categoriaId: string;
};

const CategoriasForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm<Categoria>();
  const { categoriaId } = useParams<UrlParams>();
  const navigate = useNavigate();
  const isEditing = categoriaId !== "nueva";
  const loggedUser = getAuthData();

  useEffect(() => {
    if (isEditing) {
      const config: AxiosRequestConfig = {
        method: "GET",
        url: `/categorias/${categoriaId}`,
        withCredentials: true,
      };

      requestBackend(config).then((response) => {
        const categoria = response.data as Categoria;
        setValue("nombre", categoria.nombre);
        setValue("usuarioId", categoria.usuarioId);
      });
    }
  }, [isEditing, setValue, categoriaId]);

  const onSubmit = (formData: Categoria) => {
    const data = {
      ...formData,
      categoriaId: formData.id,
      nombre: formData.nombre,
      usuarioId: loggedUser.usuarioId,
    };

    const config: AxiosRequestConfig = {
      method: isEditing ? "PUT" : "POST",
      url: isEditing ? `/categorias/${categoriaId}` : "/categorias",
      data,
      withCredentials: true,
    };

    requestBackend(config)
      .then(() => {
        toast.info(`Categoría ${isEditing ? "actualizada" : "creada"} de forma correcta.`);
        navigate("/admin/categorias");
      })
      .catch(() => {
        toast.error(`Ocurrió un error al ${isEditing ? "actualizar" : "crear"} la categoría.`);
      });
  };

  const handleCancelar = () => {
    navigate("/admin/categorias");
  };

  return (
    <form
      className="base-card categoria-card-container"
      onSubmit={handleSubmit(onSubmit)}
    >
      <input
        {...register("nombre", {
          required: "Campo obligatorio",
        })}
        type="text"
        name="nombre"
        className="form-control base-input"
        placeholder="Nombre de la categoría"
      />
      <div className="invalid-feedback d-block">{errors.nombre?.message}</div>

      <div className="categoria-card-button-container">
        <button
          type="submit"
          className="btn btn-outline-success boton-categoria-card fw-bold"
        >
          Guardar
        </button>
        <button
          type="button"
          onClick={handleCancelar}
          className="btn btn-outline-danger boton-categoria-card fw-bold"
        >
          Cancelar
        </button>
      </div>
    </form>
  );
};

export default CategoriasForm;
