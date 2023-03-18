import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import NavbarComponent from "../components/NavbarComponent";
import HeroComponent from "../components/HeroComponent";
export default function CustomerDashboard() {
  return (
    <div>
      <NavbarComponent />
      <HeroComponent
        header="Welcome to Go Meals"
        body="Delivering deliciousness – one tiffin at a time!"
      />
      {/* <br />
      <h1>Welcome to GO-Meals</h1>
      <br />
      <br />
      <h3>Vendors in your city</h3> */}

      {/*<Navbar bg="primary" variant="light" className="justify-content-center align-items-center" >*/}
      {/*  <h3>©Go Meals</h3>*/}
      {/*</Navbar>*/}
    </div>
  );
}
