import { Route, Routes } from "react-router-dom";
import UsuariosForm from "./UsuariosForm";
import UsuariosList from "./UsuariosList";

const GestionUsuarios = () => {
  return (
    <Routes>
      <Route index element={<UsuariosList />} />
      <Route path=":usuarioId" element={<UsuariosForm />} />
    </Routes>
  );
};

export default GestionUsuarios;
