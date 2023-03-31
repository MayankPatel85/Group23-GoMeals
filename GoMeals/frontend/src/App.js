import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Register from "./pages/Register";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import { useLocation, useNavigate } from "react-router-dom";
import SupplierRegister from "./pages/SupplierRegister"
import CustomerDashboard from "./pages/CustomerDashboard";
import SupplierLogin from "./pages/SupplierLogin";
import SupplierDashboard from "./pages/SupplierDashboard";
import React, { useState } from "react";
import MealAccordion from "./pages/Meals";
import NavbarComponent from "./components/NavbarComponent";
import AddOns from "./pages/AddOns";
import CustomerProfile from "./pages/CustomerProfile";
import SupplierComplain from "./pages/SupplierComplain";
<<<<<<< HEAD
import Hero from "./pages/Hero";
=======
import SupplierProfile from "./pages/SupplierProfile";
>>>>>>> 010c7c44d5387e3d9428b5813f06b6ec1b552af0

function App() {
  // const navigate = useNavigate();
  const [showNavbar, setShowNavbar] = useState(true);

   // Redirect to login page if user is not authenticated
  //  const isAuthenticated = true; // Replace with your authentication logic
  //  if (!isAuthenticated && location.pathname !== "/login") {
  //    navigate("/login");
  //  }
  return (
    <div className="App">
      <Router>
        <NavbarComponent />
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/supplierRegister" element={<SupplierRegister />} />
          <Route path="/" element={<Hero />} />
          <Route path="/dashboard" element={<CustomerDashboard />} />
          <Route path="/supplierLogin" element={<SupplierLogin />} />
          <Route path="/supplierDashboard" element={<SupplierDashboard />} />
          <Route path="/meals" element={<MealAccordion />} exact/>
          {/* <Route path="/addons" element={<AddOns />} exact/> */}
          <Route path="/customerProfile" element={<CustomerProfile />} />
          <Route path="/supplierProfile" element={<SupplierProfile/>}/>
          <Route path="/supplierComplain" element={<SupplierComplain />}></Route>
          
        </Routes>
      </Router>
    </div>
  );
}

export default App;
