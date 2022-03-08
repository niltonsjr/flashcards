import { AxiosRequestConfig } from "axios";
import { useState } from "react";
import { Tarjeta } from "types/tarjeta";
import { requestBackend } from "util/requests";
import "./styles.css";

type Props = {
  tarjeta: Tarjeta;
  onReply: Function;
};

const TarjetaFlipCard = ({ tarjeta, onReply }: Props) => {
  const [showBack, setShowBack] = useState(false);
  const handleClick = () => {
    setShowBack(!showBack);
  };

  console.log(tarjeta);

  const handleLoSe = (tar: Tarjeta) => {
    let date: Date = new Date();
    const data = {
      ...tar,
      conocida: true,
      totalConocidas: tar.totalConocidas + 1,
      fechaUltimaRespuesta: date.toISOString(),
    };

    const config: AxiosRequestConfig = {
      method: "PUT",
      url: `/tarjetas/${tar.id}`,
      data,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      onReply();
    });
  };

  const handleNoLoSe = (tar: Tarjeta) => {
    let date: Date = new Date();
    const data = {
      ...tar,
      conocida: false,
      totalNoConocidas: tar.totalNoConocidas + 1,
      fechaUltimaRespuesta: date.toISOString(),
    };

    const config: AxiosRequestConfig = {
      method: "PUT",
      url: `/tarjetas/${tar.id}`,
      data,
      withCredentials: true,
    };

    requestBackend(config).then(() => {
      onReply();
    });
  };

  return (
    <>
      <div className="estudiar-card-container" onClick={handleClick}>
        <div className={`flip-card ${showBack ? "showBack" : ""}`}>
          <div className="front estudiar-card">
            <span>{tarjeta.frontal}</span>
          </div>
          <div className="back estudiar-card">
            <span>{tarjeta.trasera}</span>
          </div>
        </div>
      </div>
      <div className="estudiar-botones-container">
        <button
          className="btn btn-outline-danger boton-estudiar-card fw-bold"
          onClick={() => handleNoLoSe(tarjeta)}
        >
          No lo sé
        </button>
        <button
          className="btn btn-outline-success boton-estudiar-card fw-bold"
          onClick={() => handleLoSe(tarjeta)}
        >
          Lo sé
        </button>
      </div>
    </>
  );
};

export default TarjetaFlipCard;
