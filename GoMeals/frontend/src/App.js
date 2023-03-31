import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Register from "./pages/Register";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";

import SupplierRegister from "./pages/SupplierRegister"
import CustomerDashboard from "./pages/CustomerDashboard";
import SupplierLogin from "./pages/SupplierLogin";
import SupplierDashboard from "./pages/SupplierDashboard";
import React from "react";
import CustomerProfile from "./pages/CustomerProfile";
import SupplierComplain from "./pages/SupplierComplain";
import SupplierProfile from "./pages/SupplierProfile";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/supplierRegister" element={<SupplierRegister />} />
          <Route path="/" element={<Register />} />
          <Route path="/dashboard" element={<CustomerDashboard />} />
          <Route path="/supplierLogin" element={<SupplierLogin />} />
          <Route path="/supplierDashboard" element={<SupplierDashboard />} />
          <Route path="/customerProfile" element={<CustomerProfile />} />
          <Route path="/supplierProfile" element={<SupplierProfile/>}/>
          <Route path="/supplierComplain" element={<SupplierComplain />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
