import "./assets/styles/custom.scss";
import "./App.css";
import Home from "pages/Home";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "components/Navbar";

const App = () => {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        {/* <Route path="/form">
          <Route path=":movieId" element={<Form />} />
        </Route> */}
      </Routes>
    </BrowserRouter>
  );
};

export default App;
