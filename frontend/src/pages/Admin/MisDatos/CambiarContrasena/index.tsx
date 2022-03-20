import { AuthContext } from "AuthContext";
import { AxiosRequestConfig } from "axios";
import { useContext, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import ReactTooltip from "react-tooltip";
import { requestBackend, requestBackendLogin } from "util/requests";
import { getAuthData } from "util/storage";
import "./styles.css";

type CredentialsDTO = {
  username: string;
  password: string;
};

type NewPasswordData = {
  nuevaContrasena: string;
  confirmaNuevaContrasena: string;
};

const CambiarContrasena = () => {
  const loggedUser = getAuthData();
  const [validActualPassword, setValidActualPassword] =
    useState<boolean>(false);
  const { authContextData } = useContext(AuthContext);
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors },
  } = useForm<CredentialsDTO>();
  const {
    register: register2,
    handleSubmit: handleSubmit2,
    setValue: setValue2,
    getValues,
    clearErrors,
    formState: { errors: errors2 },
  } = useForm<NewPasswordData>();

  const onSubmitActualPassword = (data: CredentialsDTO) => {
    requestBackendLogin(data)
      .then((response) => {
        if (response.status === 200) {
          setValidActualPassword(true);
        }
      })
      .catch((error) => {
        setValidActualPassword(false);
        toast.error("Contraseña actual incorrecta");
      });
  };

  const onSubmitNewPassword = (data: NewPasswordData) => {
    const config: AxiosRequestConfig = {
      method: "PUT",
      url: `/usuarios/nuevacontrasena/${loggedUser.usuarioId}`,
      data,
      withCredentials: true,
    };

    requestBackend(config)
      .then((response) => {
        setValue("password", "");
        setValue2("nuevaContrasena", "");
        setValue2("confirmaNuevaContrasena", "");
        setValidActualPassword(false);
        toast.success("Contraseña actualizada correctamente.");
      })
      .catch((error) => {
        toast.error(
          `Error al actualizar la contraseña: ${error.response.data.message}`
        );
      });
  };

  const handleCancel = (event: React.MouseEvent) => {
    navigate("/admin/misdatos");
  };

  return (
    <div className="cambiar-contrasena-container container-fluid">
      <form onSubmit={handleSubmit(onSubmitActualPassword)}>
        <div className="contrasena-actual-container row">
          <div>
            <input
              {...register("username")}
              type="hidden"
              name="username"
              defaultValue={authContextData.tokenData?.user_name}
            />
            <label htmlFor="password" className="form-label">
              Contraseña actual
            </label>
            <input
              {...register("password", { required: "Campo obligatorio" })}
              type="password"
              id="actual"
              className="form-control base-input bg-white"
              name="password"
            />
            <div className="invalid-feedback d-block">
              {errors.password?.message}
            </div>
          </div>
          <div className="validar-contrasena-actual-buttom-container">
            <button
              type="submit"
              className="mis-datos-buttom validar-contrasena-actual-buttom"
            >
              Validar
            </button>
          </div>
        </div>
      </form>
      <form onSubmit={handleSubmit2(onSubmitNewPassword)}>
        <div className="contrasena-nueva-container">
          <div>
            <label htmlFor="nuevaContrasena" className="form-label">
              Nueva contraseña
            </label>
            <input
              {...register2("nuevaContrasena", {
                required: {
                  value: true,
                  message: "Campo obligatorio",
                },
                pattern: {
                  value: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/,
                  message: "La contraseña no cumple con las condiciones.",
                },
              })}
              type="password"
              id="nuevaContrasena"
              className={`form-control base-input contrasena-nueva-input ${
                validActualPassword ? "bg-white" : ""
              }`}
              name="nuevaContrasena"
              data-tip
              data-for="condicionesContrasenaTooltip"
              disabled={!validActualPassword}
            />
            <div className="invalid-feedback d-block">
              {errors2.nuevaContrasena?.message}
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
              {...register2("confirmaNuevaContrasena", {
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
              className={`form-control base-input contrasena-nueva-input ${
                validActualPassword ? "bg-white" : ""
              }`}
              name="confirmaNuevaContrasena"
              disabled={!validActualPassword}
            />
            <div className="invalid-feedback d-block">
              {errors2.confirmaNuevaContrasena?.message}
            </div>
          </div>
          <div className="cambiar-contrasena-buttons-container">
            <button
              type="button"
              className="mis-datos-buttom mis-datos-cancelar-buttom col-6"
              onClick={handleCancel}
            >
              Cancelar
            </button>
            <button
              type="submit"
              className="mis-datos-buttom mis-datos-editar-buttom col-6"
              disabled={!validActualPassword}
            >
              Aceptar
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default CambiarContrasena;
