import CustomRouter from "components/CustomRouter";
import Navbar from "components/Navbar";
import Admin from "pages/Admin";
import Home from "pages/Home";
import Register from "pages/Register";
import { Route, Routes } from "react-router-dom";
import history from "util/history";
import "./assets/styles/custom.scss";
import "./App.css";
import { useState } from "react";
import { AuthContext, AuthContextData } from "AuthContext";

const App = () => {
  const [authContextData, setAuthContextData] = useState<AuthContextData>({
    authenticated: false,
  });

  return (
    <AuthContext.Provider value={{ authContextData, setAuthContextData }}>
      <CustomRouter history={history}>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="register" element={<Register />} />
          <Route path="admin/*" element={<Admin />} />

          {/* 
        <Route path="/form">
          <Route path=":movieId" element={<Form />} />
        </Route> */}
        </Routes>
      </CustomRouter>
    </AuthContext.Provider>
  );
};

export default App;
