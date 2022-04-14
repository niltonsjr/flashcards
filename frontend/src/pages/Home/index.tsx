import { Link } from "react-router-dom";
import "./styles.css";

const Home = () => {
  return (
    <div className="home-container">
      <div className="home-left-container">
        <h1>Nuestros cerebros crean recuerdos a través de la repetición</h1>
        <div className="home-left-text" data-testid="left-text">
          <p>
            Cuanto más veces repitamos algo, más probable es de que lo
            recordemos.
            <br />
            Las Flashcards son una excelente herramienta para ayudarte a retener
            información y acelerar tu aprendizaje. <br />
            <Link to="/auth/register" className="conditions-link-register">
              Regístrate
            </Link> o <Link to="/auth/login" className="conditions-link-register">
              inicia sesión
            </Link> para crear tus propias Flashcards y utilízalas para estudiar desde
            cualquier lugar.
          </p>
        </div>
      </div>
    </div>
  );
};

export default Home;
