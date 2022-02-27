import { AxiosRequestConfig } from "axios";
import CategoriaCard from "components/CategoriaCard";
import Pagination from "components/Pagination";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Categoria } from "types/categoria";
import { SpringPage } from "types/spring";
import { requestBackend } from "util/requests";
import "./styles.css";

type ControlComponentsData = {
  activePage: number;
};

const CategoriasList = () => {
  const [page, setPage] = useState<SpringPage<Categoria>>();
  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
    });

  const getCategorias = useCallback(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/categorias",
      withCredentials: true,
      params: {
        page: controlComponentsData.activePage,
        size: 9,
      },
    };

    requestBackend(config).then((response) => {
      setPage(response.data);
    });
  }, [controlComponentsData]);

  useEffect(() => {
    getCategorias();
  }, [getCategorias]);

  const handlePageChange = (pageNumber: number) => {
    setControlComponentsData({
      activePage: pageNumber,
    });
  };

  return (
    <>
      <div className="tarjeta-filter-crud-container base-card">
        <Link to="/admin/categorias/nueva">
          <button className="btn btn-primary text-white fw-bold">
            Nueva Categoría
          </button>
        </Link>
      </div>
      <div className="tarjetas-list-container">
        {page?.content.map((categoria) => (
          <div key={categoria.id}>
            <CategoriaCard categoria={categoria} onDelete={getCategorias} />
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

export default CategoriasList;
