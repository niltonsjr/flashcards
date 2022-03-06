import { Tarjeta } from "types/tarjeta";
import "./styles.css";

type Props = {
  tarjeta: Tarjeta;
};

const TarjetaFlipCard = ({ tarjeta }: Props) => {
  return (
    <>
      <div className="estudiar-card-container">
        <div className="flip-card">
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
