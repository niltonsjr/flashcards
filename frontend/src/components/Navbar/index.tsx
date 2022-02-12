import { AuthContext } from "AuthContext";
import "bootstrap/js/src/collapse.js";
import { useContext, useEffect } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { getTokenData, isAuthenticated } from "util/auth";
import { removeAuthData } from "util/storage";
import { ReactComponent as Logo } from "./../../assets/images/logo.svg";
import "./styles.css";

const Navbar = () => {
  const { authContextData, setAuthContextData } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated()) {
      setAuthContextData({
        authenticated: true,
        tokenData: getTokenData(),
      });
    } else {
      setAuthContextData({
        authenticated: false,
      });
    }
  }, [setAuthContextData]);

  const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    removeAuthData();
    setAuthContextData({
      authenticated: false,
    });
    navigate("/");
  };

  return (
    <nav className="navbar navbar-expand-md navbar-light main-nav">
      <div className="container-fluid">
        <Link to={authContextData.authenticated ? "/admin/tarjetas" : "/"} className="nav-logo-text">
          <Logo className="nav-logo" />
          <h4>FlashCards</h4>
        </Link>
        <>
          {authContextData.authenticated ? (
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
