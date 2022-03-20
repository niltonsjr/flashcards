import PrivateRoute from "components/PrivateRoute";
import { Route, Routes } from "react-router-dom";
import Categorias from "./Categorias";
import GestionUsuarios from "./GestionUsuarios";
import MisDatos from "./MisDatos";
import CambiarContrasena from "./MisDatos/CambiarContrasena";
import Navbar from "./Navbar";
import "./styles.css";
import Tarjetas from "./Tarjetas";

const Admin = () => {
 
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content">
        <Routes>
          <Route index element={<PrivateRoute><Tarjetas /></PrivateRoute>} />               
          <Route path='/tarjetas/*' element={<PrivateRoute><Tarjetas /></PrivateRoute>} />  
          <Route path="/categorias/*" element={<PrivateRoute><Categorias /></PrivateRoute>} />  
          <Route path="/misdatos" element={<PrivateRoute><MisDatos /></PrivateRoute>} />  
          <Route path="/cambiar-contrasena" element={<PrivateRoute><CambiarContrasena /></PrivateRoute>} />  
          <Route path="usuarios/*" element={<PrivateRoute roles={["ROLE_ADMINISTRADOR"]}><GestionUsuarios /></PrivateRoute>} />        
        </Routes>
      </div>
    </div>
  );
};

export default Admin;
