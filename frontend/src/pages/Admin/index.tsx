import { Route, Routes } from "react-router-dom";
import Navbar from "./Navbar";
import "./styles.css";

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content">
        <Routes>
          <Route index element={<h1>Mis Tarjetas</h1>} />      
          <Route path="tarjetas" element={<h1>Mis Tarjetas</h1>} />      
          <Route path="categorias" element={<h1>Mis categorias</h1>} />  
          <Route path="misdatos" element={<h1>Mis Datos</h1>} />  
          <Route path="usuarios" element={<h1>Gestión de Usuarios</h1>} />        
        </Routes>
      </div>
    </div>
  );
};

export default Admin;
