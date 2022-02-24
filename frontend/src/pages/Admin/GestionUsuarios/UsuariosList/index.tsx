import Pagination from "components/Pagination";
import UsuarioCard from "components/UsuarioCard";
import UsuariosFilter, { UsuarioFilterData } from "components/UsuariosFilter";
import { useState } from "react";
import { SpringPage } from "types/spring";
import { Usuario } from "types/usuario";

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

  const handleSubmitFilter = (data: UsuarioFilterData) => {
    setControlComponentsData({
      activePage: 0,
      filterData: data,
    });
  };

  return (
    <div>
      <UsuariosFilter  onSubmitFilter={handleSubmitFilter}/>  
      <div><UsuarioCard /></div>      
      <Pagination
        pageCount={page ? page?.totalPages : 0}
        range={2}
        onChange={handlePageChange}
      />    
    </div>
  );
};

export default UsuariosList;
