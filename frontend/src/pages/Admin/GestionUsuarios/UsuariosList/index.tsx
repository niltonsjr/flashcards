import { AxiosRequestConfig } from "axios";
import Pagination from "components/Pagination";
import UsuarioCard from "components/UsuarioCard";
import UsuariosFilter, { UsuarioFilterData } from "components/UsuariosFilter";
import { useCallback, useEffect, useState } from "react";
import { SpringPage } from "types/spring";
import { Usuario } from "types/usuario";
import { requestBackend } from "util/requests";

type ControlComponentsData = {
  activePage: number;
  filterData: UsuarioFilterData;
};

const UsuariosList = () => {
  const [page, setPage] = useState<SpringPage<Usuario>>();
  const [controlComponentsData, setControlComponentsData] =
    useState<ControlComponentsData>({
      activePage: 0,
      filterData: { rol: null },
    });

  const handlePageChange = (pageNumber: number) => {
    setControlComponentsData({
      activePage: pageNumber,
      filterData: controlComponentsData.filterData,
    });
  };

  const getUsuarios = useCallback(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/usuarios",
      withCredentials: true,
      params: {
        page: controlComponentsData.activePage,
        size: 3,
        rolId: controlComponentsData.filterData.rol?.id,
      },
    };

    requestBackend(config).then((response) => {
      setPage(response.data);
    });
  }, [controlComponentsData]);

  const handleSubmitFilter = (data: UsuarioFilterData) => {
    setControlComponentsData({
      activePage: 0,
      filterData: data,
    });
  };

  useEffect(() => {
    getUsuarios();
  }, [getUsuarios]);

  return (
    <div>
      <UsuariosFilter onSubmitFilter={handleSubmitFilter} />
      <div>
        {page?.content.map((usuario) => (
          <div key={usuario.id}>
            <UsuarioCard usuario={usuario} onDelete={getUsuarios} />
          </div>
        ))}
      </div>
      <Pagination
        pageCount={page ? page?.totalPages : 0}
        range={2}
        onChange={handlePageChange}
      />
    </div>
  );
};

export default UsuariosList;
