import { render, screen } from "@testing-library/react";
import CustomRouter from "components/CustomRouter";
import { Route, Routes } from "react-router-dom";
import history from "util/history";
import Home from "..";

describe("Home tests", () => {
  test("should render Home page", () => {
    render(
      <CustomRouter history={history}>
        <Routes>
          <Route path="/*" element={<Home />} />
        </Routes>
      </CustomRouter>
    );

    expect(screen.getByText("Nuestros cerebros crean recuerdos a través de la repetición")).toBeInTheDocument();
    expect(screen.getByTestId("left-text")).toBeInTheDocument();
  });
});
