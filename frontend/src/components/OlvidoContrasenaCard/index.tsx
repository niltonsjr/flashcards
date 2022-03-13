import { AxiosRequestConfig } from "axios";
import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
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
        console.log("Suceso", response);
        toast.success("Usuario creado correctamente.");
        navigate("/auth/login");
      })
      .catch((error) => {
        console.log("Erro:", error);
        toast.error(`Error al crear el usuario: ${error.message}`);
      });
  };

  return (
    <div className="olvido-contrasena-card">
      <h1>Recuperar contraseña</h1>
      <p>
        Introduzca su correo electrónico abajo y le enviaremos un correo con las
        instrucciones para resetear su contraseña.
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
          Volver al login
        </Link>
        <div>
          <button type="submit" className="login-submit">
            Aceptar
          </button>
        </div>
      </form>
    </div>
  );
};

export default OlvidoContrasenaCard;
