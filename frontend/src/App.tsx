import Navbar from "components/Navbar";
import Admin from "pages/Admin";
import Home from "pages/Home";
import Register from "pages/Register";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./assets/styles/custom.scss";
import "./App.css";


const App = () => {
  return (
    <BrowserRouter>
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
    </BrowserRouter>
  );
};

export default App;
