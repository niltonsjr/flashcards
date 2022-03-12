import { Route, Routes } from "react-router-dom";
import Register from "pages/Admin/Auth/Register";
import Login from "./Login";

const Auth = () => {
  return (
    <div>
      <Routes>
        <Route index element={<Login />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="recover" element={<Register />} />
      </Routes>
    </div>
  );
};

export default Auth;
