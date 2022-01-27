import { ReactComponent as LargeImage } from "assets/images/home-image-large.svg";
import { AuthContext } from "AuthContext";
import LoginCard from "components/LoginCard";
import { useContext } from "react";
import "./styles.css";

const Home = () => {
  const { authContextData } = useContext(AuthContext);

  return (
    <div className="home-container">
      <div className="home-left-container">
        <h1>
          Crea tarjetas de memoria <br />
          para ayudarte a retener información <br /> en menos tiempo y accede a
          ellas <br /> desde cualquier lugar.
        </h1>
        <LargeImage />
      </div>
      {!authContextData.authenticated ? (
        <div className="home-right-container">
          <LoginCard />
        </div>
      ) : (
        <>Crear algo para renderizar en lugar de Login card</>
      )}
    </div>
  );
};

export default Home;
