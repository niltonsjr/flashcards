import { render, screen } from "@testing-library/react";
import CustomRouter from "components/CustomRouter";
import { Route, Routes } from "react-router-dom";
import history from "util/history";
import Register from "..";


describe("Register tests", () => {
  test("should render Register page", () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/" element={<Register />} />
        </Routes>
      </CustomRouter>
    );
    
    expect(screen.getByText("Regístrate para empezar a crear tus tarjetas")).toBeInTheDocument();
    expect(screen.getByText("Nombre de usuario:")).toBeInTheDocument();
    expect(screen.getByText("Contraseña:")).toBeInTheDocument();
    expect(screen.getByText("Confirmar contraseña:")).toBeInTheDocument();
    expect(screen.getByText("Nombre:")).toBeInTheDocument();
    expect(screen.getByText("Apellidos:")).toBeInTheDocument();
    expect(screen.getByText("Correo electrónico:")).toBeInTheDocument();    
    expect(screen.getByRole("link", {name: "Términos y Condiciones"})).toBeInTheDocument();
    expect(screen.getByRole("checkbox")).toBeInTheDocument();
    expect(screen.getByRole("button", {name: "Aceptar"})).toBeInTheDocument();

  });
});