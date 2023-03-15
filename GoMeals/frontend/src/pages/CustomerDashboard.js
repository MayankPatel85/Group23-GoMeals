import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import NavbarComponent from "../components/NavbarComponent";
import AddOns from "./AddOns";

export default function CustomerDashboard() {
  return (
    <div>
      {/* <NavbarComponent /> */}
      <br />
      <h1>Welcome to GO-Meals</h1>
      <br />
      <br />
      <h3>Vendors in your city</h3>
      <br />
     
     {/* <AddOns /> */}

      {/*<Navbar bg="primary" variant="light" className="justify-content-center align-items-center" >*/}
      {/*  <h3>©Go Meals</h3>*/}
      {/*</Navbar>*/}
    </div>

  );
}
