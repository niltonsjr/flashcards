import { render, screen } from "@testing-library/react";
import CustomRouter from "components/CustomRouter";
import { Route, Routes } from "react-router-dom";
import history from "util/history";
import ResetearContrasenaCard from "..";

describe("ResetearContrasenaCard tests", () => {
  test("should render ResetearContrasenaCard", () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/" element={<ResetearContrasenaCard />} />
        </Routes>
      </CustomRouter>
    );
    
    expect(screen.getByText("Resetear contraseña")).toBeInTheDocument();
    expect(screen.getByText("Nueva contraseña")).toBeInTheDocument();
    expect(screen.getByText("Confirmar nueva contraseña")).toBeInTheDocument();
    expect(screen.getByRole("button", {name: "Aceptar"})).toBeInTheDocument();

  });
});