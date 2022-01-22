import "./styles.css";
import "bootstrap/js/src/collapse.js";
import { ReactComponent as Logo } from "./../../assets/images/logo.svg";
import { NavLink } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-md navbar-light main-nav">
      <div className="container-fluid">
        <a href="/" className="nav-logo-text">
          <Logo className="nav-logo" />
          <h4>FlashCards</h4>
        </a>
        <button
          className="navbar-toggler custom-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#flashcards-navbar"
          aria-controls="flashcards-navbar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div
          className="collapse navbar-collapse main-menu"
          id="flashcards-navbar"
        >
          <ul className="navbar-nav offset-md-2 ">
            <li>
              <NavLink to="admin/tarjetas">Administrar</NavLink>
            </li>
            <li>
              <NavLink to="estudiar">Estudiar</NavLink>
            </li>
          </ul>
          <div>
            <button typeof="submit" className="logout-button">
              Salir
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
