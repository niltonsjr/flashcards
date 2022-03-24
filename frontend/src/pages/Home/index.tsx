import { ReactComponent as LargeImage } from "assets/images/home-image-large.svg";
import "./styles.css";

const Home = () => {
  return (
    <div className="home-container">
      <div className="home-left-container">
        <h1>Nuestros cerebros crean recuerdos a través de la repetición.</h1>
        <div className="home-left-text">
          <p>
            Cuanto más veces repitamos algo, más probable es de que lo
            recordemos.
            <br />
            Las Flashcards son una excelente herramienta para ayudarte a retener
            información y acelerar tu aprendizaje. <br />
            Regístrate para crear tus propias Flashcards y utilízalas para
            estudiar desde cualquier lugar.
          </p>
        </div>

        <LargeImage />
      </div>
    </div>
  );
};

export default Home;
