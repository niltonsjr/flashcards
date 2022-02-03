import { AxiosRequestConfig } from "axios";
import Pagination from "components/Pagination";
import TarjetaCard from "components/TarjetaCard";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { SpringPage } from "types/spring";
import { Tarjeta } from "types/tarjeta";
import { requestBackend } from "util/requests";
import "./styles.css";

const List = () => {
  const [page, setPage] = useState<SpringPage<Tarjeta>>();
  useEffect(() => {
    getTarjetas(0);
  }, []);

  const getTarjetas = (pageNumber: number) => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/tarjetas",
      withCredentials: true,
      params: {
        page: pageNumber,
        size: 5,
      },
    };

    requestBackend(config).then((response) => {
      setPage(response.data);
    });
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
            <TarjetaCard
              tarjeta={tarjeta}
              onDelete={() => getTarjetas(page.number)}
            />
          </div>
        ))}
      </div>
      <Pagination
        pageCount={page ? page?.totalPages : 0}
        range={3}
        onChange={getTarjetas}
      />
    </>
  );
};

export default List;
