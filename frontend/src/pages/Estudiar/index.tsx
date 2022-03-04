import TarjetaFilter, { TarjetaFilterData } from "components/TarjetaFilter";
import { useState } from "react";
import "./styles.css";

type ControlComponentsData = {
  activePage: number;
  filterData: TarjetaFilterData;
};

const Estudiar = () => {
  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
      filterData: { texto: "", categoria: null },
    });

  const handleSubmitFilter = (data: TarjetaFilterData) => {
    setControlComponentsData({
      activePage: 0,
      filterData: data,
    });
  };

  return (
    <div className="estudiar-container">
      <div className="estudiar-filter-container">
        <TarjetaFilter onSubmitFilter={handleSubmitFilter} />
      </div>
      <div className="estudiar-card-container">
        <div className="flip-card">
          <div className="front estudiar-card">
            <span> front</span>
          </div>
          <div className="back estudiar-card">
            <span> back</span>
          </div>
        </div>
      </div>
      <div className="estudiar-botones-container">
        <button className="btn btn-outline-danger boton-estudiar-card fw-bold">
          No lo sé
        </button>
        <button className="btn btn-outline-success boton-estudiar-card fw-bold">
          Lo sé
        </button>
      </div>
    </div>
  );
};

export default Estudiar;
