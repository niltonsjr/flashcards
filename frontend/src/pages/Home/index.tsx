import { ReactComponent as LargeImage } from "assets/images/home-image-large.svg";
import { ReactComponent as SmallImage } from "assets/images/home-image-small.svg";
import "./styles.css";
import LoginCard from "components/LoginCard";

const Home = () => {
  const mql = window.matchMedia("(max-width: 576px)");
  let mobileView;

  mql.addEventListener("change", (e) => {
    mobileView = e.matches;
  });

  return (
    <div className="home-container">
      <div className="home-left-container">
        <h1>
          Crea tarjetas de memoria <br />
          para ayudarte a retener información <br /> en menos tiempo y accede a
          ellas <br /> desde cualquier lugar.
        </h1>
        {mobileView ? <SmallImage /> : <LargeImage /> }
      </div>
      <div className="home-right-container">
        <LoginCard />
      </div>
    </div>
  );
};

export default Home;
