import { render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import CustomRouter from "components/CustomRouter";
import { Route, Routes, useParams } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import history from "util/history";
import CategoriasForm from "../CategoriasForm";
import { categoriaResponse, mockedServer } from "./fixtures";

beforeAll(() => mockedServer.listen());
beforeEach(() => mockedServer.resetHandlers());
afterAll(() => mockedServer.close());

jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"),
  useParams: jest.fn(),
}));

describe("CategoriasForm create tests", () => {
  beforeEach(() => {
    (useParams as jest.Mock).mockReturnValue({
      categoriaId: "nueva",
    });
  });

  test("should render CategoriaForm", () => {
    render(
      <CustomRouter history={history}>
        <ToastContainer />
        <Routes>
          <Route path="/*" element={<CategoriasForm />} />
        </Routes>
      </CustomRouter>
    );

    const nombreInput = screen.getByTestId("nombre");
    const guardarButton = screen.getByRole("button", { name: "Guardar" });
    const cancelarButton = screen.getByRole("button", { name: "Cancelar" });

    expect(nombreInput).toBeInTheDocument();
    expect(guardarButton).toBeInTheDocument();
    expect(cancelarButton).toBeInTheDocument();
  });

  test("should show toast and redirect when submited correctly", async () => {
    render(
      <CustomRouter history={history}>
        <ToastContainer />
        <Routes>
          <Route path="/*" element={<CategoriasForm />} />
        </Routes>
      </CustomRouter>
    );

    const nombreInput = screen.getByTestId("nombre");
    const guardarButton = screen.getByRole("button", { name: "Guardar" });

    userEvent.type(nombreInput, "Nueva categoria");
    userEvent.click(guardarButton);

    await waitFor(() => {
      const toastElement = screen.getByText(
        "Categoría creada de forma correcta."
      );
      expect(toastElement).toBeInTheDocument();
    });

    expect(history.location.pathname).toEqual("/admin/categorias");
  });

  test("should show validation error message when submit form whithout fill categoria name", async () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/*" element={<CategoriasForm />} />
        </Routes>
      </CustomRouter>
    );

    const guardarButton = screen.getByRole("button", { name: "Guardar" });

    userEvent.click(guardarButton);
    await waitFor(() => {
      const errorMessage = screen.getAllByText("Campo obligatorio");
      expect(errorMessage).toHaveLength(1);
    });
  });

  test("should clear validation error message when filling out the form correctly", async () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/*" element={<CategoriasForm />} />
        </Routes>
      </CustomRouter>
    );

    const guardarButton = screen.getByRole("button", { name: "Guardar" });

    userEvent.click(guardarButton);
    await waitFor(() => {
      const errorMessage = screen.getAllByText("Campo obligatorio");
      expect(errorMessage).toHaveLength(1);
    });

    const nombreInput = screen.getByTestId("nombre");
    userEvent.type(nombreInput, "Nueva categoria");

    await waitFor(() => {
      const errorMessage = screen.queryAllByText("Campo obligatorio");
      expect(errorMessage).toHaveLength(0);
    });
  });

  test("should redirect when press cancel button", async () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/*" element={<CategoriasForm />} />
        </Routes>
      </CustomRouter>
    );

    const cancelButton = screen.getByRole("button", { name: "Guardar" });

    userEvent.click(cancelButton);
    await waitFor(() => {
      expect(history.location.pathname).toEqual("/admin/categorias");
    });
  });
});

describe("CategoriasForm update tests", () => {
  beforeEach(() => {
    (useParams as jest.Mock).mockReturnValue({
      categoriaId: "1",
    });
  });

  test("should render editing Categoria and show toast and redirect when submited correctly", async () => {
    render(
      <CustomRouter history={history}>
        <ToastContainer />
        <Routes>
          <Route path="/*" element={<CategoriasForm />} />
        </Routes>
      </CustomRouter>
    );

    await waitFor(() => {
      const nombreInput = screen.getByTestId("nombre");

      expect(nombreInput).toHaveValue(categoriaResponse.nombre);
    });

    const guardarButton = screen.getByRole("button", { name: "Guardar" });

    userEvent.click(guardarButton);

    await waitFor(() => {
      const toastElement = screen.getByText(
        "Categoría actualizada de forma correcta."
      );
      expect(toastElement).toBeInTheDocument();
    });

    expect(history.location.pathname).toEqual("/admin/categorias");
  });
});
