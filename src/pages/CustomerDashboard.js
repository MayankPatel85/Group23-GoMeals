import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { NavLink } from "react-router-dom";

export default function CustomerDashboard() {
  return (
    <div>
      <Navbar bg="primary" variant="light">
        <Container>
          <Navbar.Brand href="#home">Go Meals</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <Nav.Link href="#features">Profile</Nav.Link>
            <Nav.Link href="#pricing">Customers</Nav.Link>
            <Nav.Link href="/customerOrders">Orders</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <br />
      <h1>Welcome to GO-Meals</h1>
      <br />
      <br />
      <h3>Vendors in your city</h3>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />

      {/*<Navbar bg="primary" variant="light" className="justify-content-center align-items-center" >*/}
      {/*  <h3>©Go Meals</h3>*/}
      {/*</Navbar>*/}
    </div>
  );
}
