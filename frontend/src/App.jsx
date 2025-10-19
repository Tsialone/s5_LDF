import { Routes, Route } from "react-router-dom";
import Navbar from "./components/NavBar";
import Home from "./pages/Home";
import About from "./pages/About";
import Contact from "./pages/Contact";
import Employe from "./pages/Employe";

function App() {
  return (
    <div className="app-dark">
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/employes" element={<Employe />} />

      </Routes>
    </div>
  );
}

export default App;

