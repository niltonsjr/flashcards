import TarjetaCard from "components/TarjetaCard";
import "./styles.css";

const List = () => {
  const tarjeta = {
    id: 3,
    frontal: "Capital de la provincia de Sevilla.",
    trasera: "Sevilla.",
    conocida: true,
    fechaUltimaRespuesta: "2020-07-13T20:50:07Z",
    totalConocidas: 3,
    totalNoConocidas: 1,
    categoriaId: 1,
    usuarioId: 1,
  };

  return (
    <div>
      <button className="btn btn-primary text-white">Nueva tarjeta</button>
      <div>Categoría</div>
      <div className="tarjetas-list-container">
        <TarjetaCard tarjeta={tarjeta} />
      </div>
    </div>
  );
};

export default List;
