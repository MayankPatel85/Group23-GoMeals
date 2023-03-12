import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Register from "./pages/Register";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import CustomerDashboard from "./pages/CustomerDashboard";
import SupplierLogin from "./pages/SupplierLogin";
import SupplierDashboard from "./pages/SupplierDashboard";
import React from "react";
import CustomerDeliveries from "./pages/CustomerDeliveries";
import CustomerComplain from "./pages/CustomerComplain";
function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/" element={<Register />} />
          <Route path="/dashboard" element={<CustomerDashboard />} />
          <Route path="/supplierLogin" element={<SupplierLogin />} />
          <Route path="/supplierDashboard" element={<SupplierDashboard />} />
          <Route path="/customerOrders" element={<CustomerDeliveries />} />
          <Route path="/customerComplain/:id" element={<CustomerComplain />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
