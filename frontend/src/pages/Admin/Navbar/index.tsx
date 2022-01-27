import { NavLink } from "react-router-dom";
import { hasAnyRoles } from "util/requests";
import "./styles.css";

const Navbar = () => {
  return (
    <nav className="admin-nav-container">
      <ul>
        <li>
          <NavLink to="/admin/tarjetas" className="admin-nav-item">
            <p>Mis tarjetas</p>
          </NavLink>
        </li>
        <li>
          <NavLink to="/admin/categorias" className="admin-nav-item">
            <p>Mis categorias</p>
          </NavLink>
        </li>
        <li>
          <NavLink to="/admin/misdatos" className="admin-nav-item">
            <p>Mis datos</p>
          </NavLink>
        </li>
        {hasAnyRoles(["ROLE_ADMINISTRADOR"]) && (
          <li>
            <NavLink to="/admin/usuarios" className="admin-nav-item">
              <p>Gestión de usuarios</p>
            </NavLink>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
