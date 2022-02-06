import { AxiosRequestConfig } from "axios";
import Pagination from "components/Pagination";
import TarjetaCard from "components/TarjetaCard";
import TarjetaFilter from "components/TarjetaFiltrer";
import { useCallback, useEffect, useState } from "react";
import { SpringPage } from "types/spring";
import { Tarjeta } from "types/tarjeta";
import { requestBackend } from "util/requests";
import "./styles.css";

type ControlComponentsData = {
  activePage: number;
};

const List = () => {
  const [page, setPage] = useState<SpringPage<Tarjeta>>();
  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
    });

  const getTarjetas = useCallback(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/tarjetas",
      withCredentials: true,
      params: {
        page: controlComponentsData.activePage,
        size: 3,
      },
    };

    requestBackend(config).then((response) => {
      setPage(response.data);
    });
  }, [controlComponentsData]);

  useEffect(() => {
    getTarjetas();
  }, [getTarjetas]);

  const handlePageChange = (pageNumber: number) => {
    setControlComponentsData({ activePage: pageNumber });
  };

  return (
    <>
      <TarjetaFilter />
      <div className="tarjetas-list-container">
        {page?.content.map((tarjeta) => (
          <div key={tarjeta.id}>
            <TarjetaCard tarjeta={tarjeta} onDelete={getTarjetas} />
          </div>
        ))}
      </div>
      <Pagination
        pageCount={page ? page?.totalPages : 0}
        range={2}
        onChange={handlePageChange}
      />
    </>
  );
};

export default List;
