import { AxiosRequestConfig } from "axios";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import { Tarjeta } from "types/tarjeta";
import { requestBackend } from "util/requests";
import "./styles.css";

type UrlParams = {
  tarjetaId: string;
};

const Form = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm<Tarjeta>();
  const { tarjetaId } = useParams<UrlParams>();
  const navigate = useNavigate();
  const isEditing = tarjetaId !== "nueva";

  useEffect(() => {
    if (isEditing) {
      const config: AxiosRequestConfig = {
        method: "GET",
        url: `/tarjetas/${tarjetaId}`,
        withCredentials: true,
      };
      requestBackend(config).then((response) => {
        const tarjeta = response.data as Tarjeta;
        setValue("frontal", tarjeta.frontal);
        setValue("trasera", tarjeta.trasera);
        setValue("categoriaId", tarjeta.categoriaId);
        setValue("conocida", tarjeta.conocida);
        setValue("fechaUltimaRespuesta", tarjeta.fechaUltimaRespuesta);
        setValue("totalConocidas", tarjeta.totalConocidas);
        setValue("totalNoConocidas", tarjeta.totalNoConocidas);
        setValue("usuarioId", tarjeta.usuarioId);
      });
    }
  }, [isEditing, setValue, tarjetaId]);

  const onSubmit = (formData: Tarjeta) => {
    const data = {
      ...formData,
      categoriaId: isEditing ? formData.categoriaId : 2,
      usuarioId: isEditing ? formData.usuarioId : 2,
      conocida: isEditing ? formData.conocida : false,
      fechaUltimaRespuesta: isEditing ? formData.fechaUltimaRespuesta : null,
      totalConocidas: isEditing ? formData.totalConocidas : 0,
      totalNoConocidas: isEditing ? formData.totalNoConocidas : 0,
    };

    const config: AxiosRequestConfig = {
      method: isEditing ? "PUT" : "POST",
      url: isEditing ? `/tarjetas/${tarjetaId}` : "/tarjetas",
      data,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      navigate("/admin/tarjetas");
    });
  };

  const handleCancelar = () => {
    navigate("/admin/tarjetas");
  };

  return (
    <>
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="tarjeta-card-container base-card"
      >
        <div className="tarjeta-select-container">
          <select
            {...register("categoriaId", {
              required: "Seleccione una categoría",
            })}
            name="categoriaId"
            className="form-select tarjeta-filter-crud-select bg-white"
            aria-label="Default select example"
            defaultValue={"categoria"}
          >
            <option value="categoria">Categoría</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
          </select>
          <div className="invalid-feedback d-block">
            {errors.categoriaId?.message}
          </div>
        </div>
        <div className="tarjeta-text-buttons-container">
          <div className="tarjeta-textarea-container">
            <div className="tarjeta-frontal-container">
              <label htmlFor="cardFrontalTextArea" className="tarjeta-label">
                Frontal
              </label>
              <textarea
                {...register("frontal", {
                  required: "Campo obligatorio",
                })}
                name="frontal"
                className="form-control mb-2 bg-white fs-6"
                id="cardFrontalTextArea"
                placeholder="Frontal"
                defaultValue="Frontal"
                rows={5}
              />
              <div className="invalid-feedback d-block">
                {errors.frontal?.message}
              </div>
            </div>
            <div className="tarjeta-trasera-container">
              <label htmlFor="cardTraseraTextArea" className="tarjeta-label">
                Trasera
              </label>
              <textarea
                {...register("trasera", {
                  required: "Campo obligatorio",
                })}
                name="trasera"
                className="form-control mb-2 bg-white fs-6 h-auto"
                id="cardTraseraTextArea"
                placeholder="Trasera"
                defaultValue="Trasera"
                rows={5}
              />
              <div className="invalid-feedback d-block">
                {errors.trasera?.message}
              </div>
            </div>
          </div>
          <div className="tarjeta-butons-container">
            <button
              type="submit"
              className="btn btn-outline-success boton-tarjeta-card fw-bold"
            >
              Aceptar
            </button>
            <button
              type="button"
              onClick={handleCancelar}
              className="btn btn-outline-danger boton-tarjeta-card fw-bold"
            >
              Cancelar
            </button>
          </div>
        </div>
      </form>
    </>
  );
};

export default Form;
