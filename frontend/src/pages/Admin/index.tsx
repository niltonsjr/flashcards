import PrivateRoute from "components/PrivateRoute";
import { Route, Routes } from "react-router-dom";
import Navbar from "./Navbar";
import "./styles.css";

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content">
        <Routes>
          <Route index element={<PrivateRoute><h1>Mis tarjetas</h1></PrivateRoute>} />      
          <Route path='/tarjetas' element={<PrivateRoute><h1>Mis tarjetas</h1></PrivateRoute>} />  
          <Route path="categorias" element={<PrivateRoute><h1>Mis categorías</h1></PrivateRoute>} />  
          <Route path="misdatos" element={<PrivateRoute><h1>Mis datos</h1></PrivateRoute>} />  
          <Route path="usuarios" element={<PrivateRoute roles={["ROLE_ADMINISTRADOR"]}><h1>Usuarios</h1></PrivateRoute>} />        
        </Routes>
      </div>
    </div>
  );
};

export default Admin;
