import { useState } from "react";
import { Tarjeta } from "types/tarjeta";
import "./styles.css";

type Props = {
  tarjeta: Tarjeta;
};

const TarjetaFlipCard = ({ tarjeta }: Props) => {
  const [showBack, setShowBack] = useState(false);
  const handleClick = () => {
      setShowBack(!showBack);
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
    </>
  );
};

export default TarjetaFlipCard;
