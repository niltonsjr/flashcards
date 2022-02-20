import { AuthContext, AuthContextData } from "AuthContext";
import { useState } from "react";
import { Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import CustomRouter from "components/CustomRouter";
import Navbar from "components/Navbar";
import Admin from "pages/Admin";
import Auth from "pages/Admin/Auth";
import Home from "pages/Home";
import history from "util/history";
import "react-toastify/dist/ReactToastify.css";
import "./assets/styles/custom.scss";
import "./App.css";

const App = () => {
  const [authContextData, setAuthContextData] = useState<AuthContextData>({
    authenticated: false,
  });

  return (
    <AuthContext.Provider value={{ authContextData, setAuthContextData }}>
      <CustomRouter history={history}>
        <ToastContainer
          autoClose={2000}
          pauseOnFocusLoss
          draggable
          pauseOnHover
        />
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/auth/*" element={<Auth />} />
          <Route path="admin/*" element={<Admin />} />
        </Routes>
      </CustomRouter>
    </AuthContext.Provider>
  );
};

export default App;
