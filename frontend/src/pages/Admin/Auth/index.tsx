import Register from "pages/Admin/Auth/Register";
import { Route, Routes } from "react-router-dom";
import Login from "./Login";
import OlvidoContrasena from "./OlvidoContrasena";
import ResetearContrasena from "./ResetearContrasena";

const Auth = () => {
  return (
    <div>
      <Routes>
        <Route index element={<Login />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="recover" element={<OlvidoContrasena />} />
        <Route path="reset_contrasena" element={<ResetearContrasena />} />
      </Routes>
    </div>
  );
};

export default Auth;
