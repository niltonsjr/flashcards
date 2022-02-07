import { Route, Routes } from "react-router-dom";
import TarjetasForm from "./TarjetasForm";
import TarjetasList from "./TarjetasList";

const Tarjetas = () => {
  return (
      <Routes>
           <Route index element={<TarjetasList />} />  
           <Route path=":tarjetaId" element={<TarjetasForm />} />  
      </Routes>
  );
  
};

export default Tarjetas;
