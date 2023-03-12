import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Register from "./pages/Register";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";

import SupplierRegister from "./pages/SupplierRegister";
import CustomerDashboard from "./pages/CustomerDashboard";
import SupplierLogin from "./pages/SupplierLogin";
import SupplierDashboard from "./pages/SupplierDashboard";
import React from "react";
import CustomerDeliveries from "./pages/CustomerDeliveries";
import CustomerRaiseComplain from "./pages/CustomerRaiseComplain";
import CustomerComplainTracker from "./pages/CustomerComplainTracker";
import SupplierPolling from "./pages/SupplierPolling";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/SupplierRegister" element={<SupplierRegister />} />
          <Route path="/" element={<Register />} />
          <Route path="/dashboard" element={<CustomerDashboard />} />
          <Route path="/supplierLogin" element={<SupplierLogin />} />
          <Route path="/supplierDashboard" element={<SupplierDashboard />} />
          <Route path="/customerOrders" element={<CustomerDeliveries />} />
          <Route
            path="/customerRaiseComplain/:id"
            element={<CustomerRaiseComplain />}
          />
          <Route
            path="/complainTracker"
            element={<CustomerComplainTracker />}
          />

          <Route path="/supplierPolling" element={<SupplierPolling />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
