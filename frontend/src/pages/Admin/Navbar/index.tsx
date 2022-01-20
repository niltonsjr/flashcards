import "./styles.css";

const Navbar = () => {
  return (
    <nav className="admin-nav-container">
      <ul>
        <li>
          <a href="link" className="admin-nav-item active">
            <p>Mis tarjetas</p>
          </a>
        </li>
        <li>
          <a href="link" className="admin-nav-item">
            <p>Mis categorias</p>
          </a>
        </li>
        <li>
          <a href="link" className="admin-nav-item">
            <p>Mis datos</p>
          </a>
        </li>
        <li>
          <a href="link" className="admin-nav-item">
            <p>Gestión de usuarios</p>
          </a>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
