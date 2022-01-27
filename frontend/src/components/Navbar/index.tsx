import { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import {
  getTokenData,
  isAuthenticated,
  removeAuthData,
  TokenData,
} from "util/requests";
import { ReactComponent as Logo } from "./../../assets/images/logo.svg";
import "bootstrap/js/src/collapse.js";
import "./styles.css";

type AuthData = {
  authenticated: boolean;
  tokenData?: TokenData;
};

const Navbar = () => {
  const [authData, setAuthData] = useState<AuthData>({ authenticated: false });
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated()) {
      setAuthData({
        authenticated: true,
        tokenData: getTokenData(),
      });
    } else {
      setAuthData({
        authenticated: false,
      });
    }
  }, []);

  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    removeAuthData();
    setAuthData({
      authenticated: false,
    });
    navigate("/");
  };

  return (
    <nav className="navbar navbar-expand-md navbar-light main-nav">
      <div className="container-fluid">
        <a href="/" className="nav-logo-text">
          <Logo className="nav-logo" />
          <h4>FlashCards</h4>
        </a>
        <>
          {authData.authenticated ? (
            <>
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
                  <button
                    typeof="submit"
                    className="logout-button"
                    onClick={handleClick}
                  >
                    Salir
                  </button>
                </div>
              </div>
            </>
          ) : (
            <></>
          )}
        </>
      </div>
    </nav>
  );
};

export default Navbar;
