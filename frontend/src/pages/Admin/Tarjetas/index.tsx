import { Route, Routes } from "react-router-dom";
import Form from "./Form";
import List from "./List";
import "./styles.css";

const Tarjetas = () => {
  return (
      <Routes>
           <Route index element={<List />} />  
           <Route path=":tarjetaId" element={<Form />} />  
      </Routes>
  );
  
};

export default Tarjetas;
