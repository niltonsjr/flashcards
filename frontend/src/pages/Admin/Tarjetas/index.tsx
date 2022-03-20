import { AxiosRequestConfig } from "axios";
import { useCallback, useEffect, useState } from "react";
import { Route, Routes } from "react-router-dom";
import { Categoria } from "types/categoria";
import { requestBackend } from "util/requests";
import DotsLoader from "../../../components/DotsLoader";
import TarjetasForm from "./TarjetasForm";
import TarjetasList from "./TarjetasList";

const Tarjetas = () => {
  const [categorias, setCategorias] = useState<Categoria[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const getCategorias = useCallback(() => {
    const config: AxiosRequestConfig = {
      method: "GET",
      url: "/categorias",
      withCredentials: true,
    };
    setIsLoading(true);
    requestBackend(config).then((response) => {
      setCategorias(response.data.content);
    }).finally(() => {
      setIsLoading(false);
    });
  }, []);

  useEffect(() => {
    getCategorias();
  }, [getCategorias]);

  return (
    <Routes>
      <Route index element={isLoading ? <DotsLoader /> : <TarjetasList categorias={categorias} />} />
      <Route path=":tarjetaId" element={<TarjetasForm />} />
    </Routes>
  );
};

export default Tarjetas;
