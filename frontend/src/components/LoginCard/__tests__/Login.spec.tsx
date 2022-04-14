import { render, screen } from "@testing-library/react";
import CustomRouter from "components/CustomRouter";
import { Route, Routes } from "react-router-dom";
import history from "util/history";
import LoginCard from "..";


describe("LoginCard tests", () => {
  test("should render LoginCard", () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/" element={<LoginCard />} />
        </Routes>
      </CustomRouter>
    );
    
    expect(screen.getByText("Iniciar sesión")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Nombre de Usuario")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Contraseña")).toBeInTheDocument();
    expect(screen.getByRole("link", {name: "¿Olvidó su contraseña?"})).toBeInTheDocument();
    expect(screen.getByRole("button", {name: "Aceptar"})).toBeInTheDocument();
    expect(screen.getByRole("link", {name: "Regístrate gratis"})).toBeInTheDocument();

  });
});