import { AxiosRequestConfig } from "axios";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import { requestBackend } from "util/requests";
import "./styles.css";

type CorreoDTO = {
  email: string;
};

const OlvidoContrasenaCard = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CorreoDTO>();
  const [enviado, setEnviado] = useState<boolean>(false);
  const navigate = useNavigate();

  const onSubmit = (formData: CorreoDTO) => {
    const config: AxiosRequestConfig = {
      method: "POST",
      url: "/contrasena_olvidada",
      data: formData,
      withCredentials: false,
    };

    requestBackend(config)
      .then((response) => {
        setEnviado(true);
      })
      .catch(() => {
        setEnviado(true);
      });
  };

  const handleClick = () => {
    navigate("/auth/login");
  };

  return (
    <div className="olvido-contrasena-card">
      <h1>Recuperar contraseña</h1>
      {!enviado ? (
        <>
          <p>
            Introduzca su correo electrónico abajo y le enviaremos un correo con
            las instrucciones para resetear su contraseña.
          </p>
          <form onSubmit={handleSubmit(onSubmit)}>
            <div className="mb-4">
              <input
                {...register("email", {
                  required: "Campo obligatorio",
                })}
                type="text"
                className={`form-control base-input ${
                  errors.email ? "is-invalid" : ""
                }`}
                placeholder="Email"
                name="email"
              />
              <div className="invalid-feedback d-block">
                {errors.email?.message}
              </div>
            </div>
            <Link to="/auth/login" className="login-link-recover">
              Volver al Login
            </Link>
            <div>
              <button type="submit" className="olvido-contrasena-submit">
                Aceptar
              </button>
            </div>
          </form>
        </>
      ) : (
        <>
          <p>
            Si el correo electrónico facilitado se encuentra registrado,
            recibirá un correo con instrucciones para resetear su contraseña. Si
            no ha recibido el correo, deberá crear una cuenta{" "}
            <Link to="/auth/register" className="link-primary">
              aquí
            </Link>
            .
          </p>
          <div>
            <button
              type="button"
              className="olvido-contrasena-submit"
              onClick={handleClick}
            >
              Volver al Login
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default OlvidoContrasenaCard;
