import PrivateRoute from "components/PrivateRoute";
import { Route, Routes } from "react-router-dom";
import Categorias from "./Categorias";
import MisDatos from "./MisDatos";
import Navbar from "./Navbar";
import Tarjetas from "./Tarjetas";
import CambiarContrasena from "./MisDatos/CambiarContrasena";
import GestionUsuarios from "./GestionUsuarios";
import "./styles.css";


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
