import { render, screen } from "@testing-library/react";
import CustomRouter from "components/CustomRouter";
import { Route, Routes } from "react-router-dom";
import history from "util/history";
import OlvidoContrasenaCard from "..";


describe("LoginCard tests", () => {
  test("should render LoginCard", () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/" element={<OlvidoContrasenaCard />} />
        </Routes>
      </CustomRouter>
    );
    
    expect(screen.getByText("Recuperar contraseña")).toBeInTheDocument();
    expect(screen.getByText("Introduzca su correo electrónico abajo y le enviaremos un correo con las instrucciones para resetear su contraseña.")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Email")).toBeInTheDocument();
    expect(screen.getByRole("link", {name: "Volver al Login"})).toBeInTheDocument();
    expect(screen.getByRole("button", {name: "Aceptar"})).toBeInTheDocument();

  });
});