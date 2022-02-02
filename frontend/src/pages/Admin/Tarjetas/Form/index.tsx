import { AxiosRequestConfig } from "axios";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import { Categoria } from "types/categoria";
import { Tarjeta } from "types/tarjeta";
import { requestBackend } from "util/requests";
import { getAuthData } from "util/storage";
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
    control,
  } = useForm<Tarjeta>();
  const { tarjetaId } = useParams<UrlParams>();
  const navigate = useNavigate();
  const isEditing = tarjetaId !== "nueva";
  const [selectCategorias, setSelectCategorias] = useState<Categoria[]>([]);
  const loggedUser = getAuthData();

  useEffect(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: `/categorias`,
      withCredentials: true,
    };
    requestBackend(config).then((response) => {
      setSelectCategorias(response.data.content);
    });
  }, []);

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
        console.log(response.data);
      });
    }
  }, [isEditing, setValue, tarjetaId]);

  const onSubmit = (formData: Tarjeta) => {
    const data = {
      ...formData,
      categoriaId: formData.categoriaId,
      usuarioId: isEditing ? formData.usuarioId : loggedUser.usuarioId,
      conocida: isEditing ? formData.conocida : false,
      fechaUltimaRespuesta: isEditing ? formData.fechaUltimaRespuesta : null,
      totalConocidas: isEditing ? formData.totalConocidas : 0,
      totalNoConocidas: isEditing ? formData.totalNoConocidas : 0,
    };

    console.log(data);
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
          <Controller
            name="categoriaId"
            rules={{ required: true }}
            control={control}
            render={({ field: { onChange, value } }) => {
              const currentSelection = selectCategorias.find(
                (c) => c.id === value
              );
              const handleSelectChange = (selectedOption: Categoria | null) => {
                onChange(selectedOption?.id);
              };

              return (
                <Select
                  className="tarjeta-filter-crud-select"
                  classNamePrefix="tarjeta-filter-crud-select"
                  options={selectCategorias}
                  value={currentSelection}
                  getOptionLabel={(cat: Categoria) => cat.nombre}
                  getOptionValue={(cat: Categoria) => String(cat.id)}
                  onChange={handleSelectChange}
                />
              );
            }}
          />
          {errors.categoriaId && (
            <div className="invalid-feedback d-block">Campo obligatorio.</div>
          )}
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
