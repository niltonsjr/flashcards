import { AxiosRequestConfig } from "axios";
import EstudiarFilter, { EstudiarFilterData } from "components/EstudiarFilter";
import TarjetaFlipCard from "components/TarjetaFlipCard";
import { useCallback, useEffect, useState } from "react";
import { SpringPage } from "types/spring";
import { Tarjeta } from "types/tarjeta";
import { requestBackend } from "util/requests";
import "./styles.css";

type ControlComponentsData = {
  activePage: number;
  filterData: EstudiarFilterData;
};

const Estudiar = () => {
  const [page, setPage] = useState<SpringPage<Tarjeta>>();
  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
      filterData: { categoria: null },
    });

  const getTarjetas = useCallback(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/tarjetas",
      withCredentials: true,
      params: {
        page: controlComponentsData.activePage,
        size: 1,
        categoriaId: controlComponentsData.filterData.categoria?.id,
      },
    };

    requestBackend(config).then((response) => {
      setPage(response.data);
    });
  }, [controlComponentsData]);

  useEffect(() => {
    getTarjetas();
  }, [getTarjetas]);

  const handleSubmitFilter = (data: EstudiarFilterData) => {
    setControlComponentsData({
      activePage: 0,
      filterData: data,
    });
  };

  return (
    <div className="estudiar-container">
      <div className="estudiar-filter-container">
        <EstudiarFilter onSubmitFilter={handleSubmitFilter} />
      </div>
      {page?.content.map((tarjeta) => (
        <div key={tarjeta.id}>
          <TarjetaFlipCard tarjeta={tarjeta} />
        </div>
      ))}

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
