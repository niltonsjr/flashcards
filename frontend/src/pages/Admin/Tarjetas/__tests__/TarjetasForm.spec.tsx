import { render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import CustomRouter from "components/CustomRouter";
import { Route, Routes, useParams } from "react-router-dom";
import selectEvent from "react-select-event";
import { ToastContainer } from "react-toastify";
import history from "util/history";
import TarjetasForm from "../TarjetasForm";
import { mockedServer, tarjetaResponse } from "./fixtures";

beforeAll(() => mockedServer.listen());
beforeEach(() => mockedServer.resetHandlers());
afterAll(() => mockedServer.close());

jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"),
  useParams: jest.fn(),
}));

describe("TarjetasForm create tests", () => {
  beforeEach(() => {
    (useParams as jest.Mock).mockReturnValue({
      tarjetaId: "nueva",
    });
  });

  test("should render TrajetasForm", () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/*" element={<TarjetasForm />} />
        </Routes>
      </CustomRouter>
    );

    const frontalInput = screen.getByLabelText("Frontal");
    const traseralInput = screen.getByLabelText("Trasera");
    const categorialInput = screen.getByLabelText("Categorias");
    const aceptarButton = screen.getByRole("button", { name: "Aceptar" });
    const cancelarButton = screen.getByRole("button", { name: "Cancelar" });

    expect(frontalInput).toBeInTheDocument();
    expect(traseralInput).toBeInTheDocument();
    expect(categorialInput).toBeInTheDocument();
    expect(aceptarButton).toBeInTheDocument();
    expect(cancelarButton).toBeInTheDocument();
  });

  test("should show toast and redirect when submited correctly", async () => {
    render(
      <CustomRouter history={history}>
        <ToastContainer />
        <Routes>
          <Route path="/*" element={<TarjetasForm />} />
        </Routes>
      </CustomRouter>
    );

    const categoriaInput = screen.getByLabelText("Categorias");
    await waitFor(() =>
      selectEvent.select(categoriaInput, ["Vocabulario Inglés"])
    );

    const frontalInput = screen.getByLabelText("Frontal");
    const traseralInput = screen.getByLabelText("Trasera");

    userEvent.type(frontalInput, "Nueva tarjeta Frontal");
    userEvent.type(traseralInput, "Nueva tarjeta Trasera");

    const aceptarButton = screen.getByRole("button", { name: "Aceptar" });

    userEvent.click(aceptarButton);

    await waitFor(() => {
      const toastElement = screen.getByText(
        "Tarjeta creada de forma correcta."
      );
      expect(toastElement).toBeInTheDocument();
    });

    expect(history.location.pathname).toEqual("/admin/tarjetas");
  });

  test("should show validation error message when submit form whithout select categoria", async () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/*" element={<TarjetasForm />} />
        </Routes>
      </CustomRouter>
    );

    const aceptarButton = screen.getByRole("button", { name: "Aceptar" });

    userEvent.click(aceptarButton);

    await waitFor(() => {
      const errorMessage = screen.getAllByText("Campo obligatorio.");
      expect(errorMessage).toHaveLength(1);
    });
  });

  test("should clear validation error message when filling out the form correctly", async () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/*" element={<TarjetasForm />} />
        </Routes>
      </CustomRouter>
    );

    const aceptarButton = screen.getByRole("button", { name: "Aceptar" });

    userEvent.click(aceptarButton);

    await waitFor(() => {
      const errorMessage = screen.getAllByText("Campo obligatorio.");
      expect(errorMessage).toHaveLength(1);
    });

    const categoriaInput = screen.getByLabelText("Categorias");

    await waitFor(() =>
      selectEvent.select(categoriaInput, ["Vocabulario Inglés"])
    );

    await waitFor(() => {
      const errorMessage = screen.queryAllByText("Campo obligatorio.");
      expect(errorMessage).toHaveLength(0);
    });
  });

  test("should redirect when press cancel button", async () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/*" element={<TarjetasForm />} />
        </Routes>
      </CustomRouter>
    );

    const cancelarButton = screen.getByRole("button", { name: "Cancelar" });

    userEvent.click(cancelarButton);
    await waitFor(() => {
      expect(history.location.pathname).toEqual("/admin/tarjetas");
    });
  });
});

describe("TarjetasForm update tests", () => {
  beforeEach(() => {
    (useParams as jest.Mock).mockReturnValue({
      tarjetaId: "1",
    });
  });

  test("should render editing Categoria and show toast and redirect when submited correctly", async () => {
    render(
      <CustomRouter history={history}>
        <ToastContainer />
        <Routes>
          <Route path="/*" element={<TarjetasForm />} />
        </Routes>
      </CustomRouter>
    );

    await waitFor(() => {
      const frontalInput = screen.getByLabelText("Frontal");
      expect(frontalInput).toHaveValue(tarjetaResponse.frontal);
    });

    const formElement = screen.getByTestId("form");
    expect(formElement).toHaveFormValues({
      categoria: String(tarjetaResponse.categoria.id),
    });

    const aceptarButton = screen.getByRole("button", { name: "Aceptar" });

    userEvent.click(aceptarButton);

    await waitFor(() => {
      const toastElement = screen.getByText(
        "Tarjeta actualizada de forma correcta."
      );
      expect(toastElement).toBeInTheDocument();
    });

    expect(history.location.pathname).toEqual("/admin/tarjetas");
  });
});
