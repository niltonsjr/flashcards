import { AxiosRequestConfig } from "axios";
import Pagination from "components/Pagination";
import TarjetaCard from "components/TarjetaCard";
import TarjetaFilter, { TarjetaFilterData } from "components/TarjetaFilter";
import { useCallback, useEffect, useState } from "react";
import { Categoria } from "types/categoria";
import { SpringPage } from "types/spring";
import { Tarjeta } from "types/tarjeta";
import { requestBackend } from "util/requests";

type ControlComponentsData = {
  activePage: number;
  filterData: TarjetaFilterData;
};

type Props = {
  categorias: Categoria[];
};

const TarjetasList = ({ categorias }: Props) => {
  const [page, setPage] = useState<SpringPage<Tarjeta>>();
  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
      filterData: { texto: "", categoria: null },
    });

  const getTarjetas = useCallback(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/tarjetas",
      withCredentials: true,
      params: {
        page: controlComponentsData.activePage,
        size: 3,
        categoriaId: controlComponentsData.filterData.categoria?.id,
        texto: controlComponentsData.filterData.texto,
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
    setControlComponentsData({
      activePage: pageNumber,
      filterData: controlComponentsData.filterData,
    });
  };

  const handleSubmitFilter = (data: TarjetaFilterData) => {
    setControlComponentsData({
      activePage: 0,
      filterData: data,
    });
  };

  return (
    <>
      {!categorias.length ? (
        <div className="base-card w-100 h-100 p-3">
          <span>Por favor, acceda al apartado de <b>Mis Categorias</b> para crear una categoria.</span>
        </div>
      ) : (
        <>
          <TarjetaFilter onSubmitFilter={handleSubmitFilter} />
          <div>
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
      )}
    </>
  );
};

export default TarjetasList;
