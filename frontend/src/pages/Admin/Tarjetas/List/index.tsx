import { AxiosRequestConfig } from "axios";
import Pagination from "components/Pagination";
import TarjetaCard from "components/TarjetaCard";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
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
      <div className="tarjeta-filter-crud base-card">
        <select
          className="form-select tarjeta-filter-crud-select bg-white"
          aria-label="Default select example"
          defaultValue={"categoria"}
        >
          <option value="categoria">Categoría</option>
          <option value="1">One</option>
          <option value="2">Two</option>
          <option value="3">Three</option>
        </select>

        <Link to="/admin/tarjetas/nueva">
          <button className="btn btn-primary text-white fw-bold btn-tarjeta-crud-nueva">
            Nueva tarjeta
          </button>
        </Link>
      </div>

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
