import { AxiosRequestConfig } from "axios";
import { useForm } from "react-hook-form";
import { useNavigate, useSearchParams } from "react-router-dom";
import { toast } from "react-toastify";
import ReactTooltip from "react-tooltip";
import { requestBackend } from "util/requests";
import "./styles.css";

type NuevaContrasenaDTO = {
  token: string;
  nuevaContrasena: string;
  confirmaNuevaContrasena: string;
};

const ResetearContrasenaCard = () => {
  const {
    register,
    handleSubmit,
    getValues,
    formState: { errors },
  } = useForm<NuevaContrasenaDTO>();
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  const onSubmit = (formData: NuevaContrasenaDTO) => {
    const config: AxiosRequestConfig = {
      method: "POST",
      url: "/reset_contrasena",
      data: formData,
      withCredentials: false,
    };

    requestBackend(config)
      .then((response) => {
        navigate("/auth/login");
        toast.success("Contraseña cambiada con éxito.");
      })
      .catch((error) => {
        navigate("/auth/login");
        toast.error(
          `Error al cambiar la contraseña: ${error.response.data.message}`
        );
      });
  };

  return (
    <div className="olvido-contrasena-card">
      <h1>Resetear contraseña</h1>

      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="mb-4">
          <input
            {...register("token")}
            type="hidden"
            name="token"
            defaultValue={searchParams.get("token") || ""}
          />
          <label htmlFor="nuevaContrasena" className="form-label">
            Nueva contraseña
          </label>
          <input
            {...register("nuevaContrasena", {
              required: {
                value: true,
                message: "Campo obligatorio"},
              pattern: {
                value: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/,
                message: "La contraseña no cumple con las condiciones.",
              }
            })}
            type="password"
            id="nuevaContrasena"
            className={
              "form-control base-input contrasena-nueva-input bg-white"
            }
            name="nuevaContrasena"
            data-tip
            data-for="condicionesContrasenaTooltip"
          />
          <div className="invalid-feedback d-block">
            {errors.nuevaContrasena?.message}
          </div>
          <ReactTooltip
            id="condicionesContrasenaTooltip"
            place="bottom"
            effect="solid"
          >
            <p>
              Condiciones de la contraseña: <br />
              - Mínimo 8 caracteres. <br />
              - Al menos una letra mayúscula, una minúscula y un número.
              <br />
              - Puede contener caracteres especiales.
              <br />
            </p>
          </ReactTooltip>
        </div>
        <div>
          <label htmlFor="confirmaNuevaContrasena" className="form-label">
            Confirmar nueva contraseña
          </label>
          <input
            {...register("confirmaNuevaContrasena", {
              required: "Campo obligatorio",
              validate: {
                matchPreviousPassword: (value) => {
                  const { nuevaContrasena } = getValues();
                  return (
                    nuevaContrasena === value ||
                    "Los campos nueva contraseña y confirmar nueva contraseña deben coincidir."
                  );
                },
              },
            })}
            type="password"
            id="confirmaNuevaContrasena"
            className={
              "form-control base-input contrasena-nueva-input bg-white"
            }
            name="confirmaNuevaContrasena"
          />
          <div className="invalid-feedback d-block">
            {errors.confirmaNuevaContrasena?.message}
          </div>
        </div>

        <div>
          <button type="submit" className="olvido-contrasena-submit">
            Aceptar
          </button>
        </div>
      </form>
    </div>
  );
};

export default ResetearContrasenaCard;
