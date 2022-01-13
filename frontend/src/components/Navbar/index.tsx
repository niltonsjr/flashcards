import "./styles.css";

const Navbar = () => {
  return (
    <nav className="bg-primary">
      <div>
        <a href="link">
          <h4>FlashCards</h4>
        </a>
        <div>
          <ul>
            <li>
              <a href="link">Administrar</a>
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
