import { Route, Routes } from "react-router-dom";
import CategoriasForm from "./CategoriasForm";
import CategoriasList from "./CategoriasList";

const Categorias = () => {
  return (
      <Routes>
           <Route index element={<CategoriasList />} />  
           <Route path=":categoriaId" element={<CategoriasForm />} />  
      </Routes>
  );
  
};

export default Categorias;
