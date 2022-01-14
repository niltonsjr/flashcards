import "./styles.css";
import "bootstrap/js/src/collapse.js";
import { ReactComponent as Logo } from "./../../assets/images/logo.svg";

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-md navbar-light main-nav">
      <div className="container-fluid">
        <a href="link" className="nav-logo-text">
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

        <div className="collapse navbar-collapse" id="flashcards-navbar">
          <ul className="navbar-nav offset-md-2 main-menu">
            <li>
              <a href="link" className="active">
                Administrar
              </a>
            </li>
            <li>
              <a href="link">Estudiar</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
