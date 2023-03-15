import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { Cookies } from "react-cookie";
import { useLocation } from "react-router-dom";

import {
  Container,
  Dropdown,
  DropdownButton,
  Nav,
  Navbar,
} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBell,
  faUserCircle,
  faSignOutAlt,
} from "@fortawesome/free-solid-svg-icons";
import "../styles/Navbar.css";
export default function NavbarComponent() {
  const cookies = new Cookies();
  const navigate = useNavigate();

  const loggedInUser = cookies.get("loggedInUser");
  console.log(loggedInUser);
  const [showNotifications, setShowNotifications] = useState(false);

  const toggleNotifications = () => {
    setShowNotifications(!showNotifications);
  };

  const logout = () => {
    cookies.remove("loggedInUser");
    navigate("/");
  };
  var customerUser = ""
  var supplierUser = ""
  if(loggedInUser){
   customerUser = loggedInUser.userType === "customer";
   supplierUser = loggedInUser.userType === "supplier";
  }

  const getProfileName = () => {
    //boolean to see which type of user has logged in

    if (customerUser) {
      return loggedInUser.cust_fname + " " + loggedInUser.cust_lname;
    } else if (supplierUser) {
      return loggedInUser.supName;
    }
  };
  const location = useLocation();
  const hideNavBar = location.pathname === "/login" || location.pathname === "/register" || location.pathname === "/";

  if (hideNavBar) {
    return null;
  }else {
  return (
    <>
      <Navbar bg="primary" variant="light">
        <Container style={{ display: "flex", justifyContent: "space-between" }}>
          <Navbar.Brand href="#home">Go Meals</Navbar.Brand>
          <div>
            <Nav className="me-auto">
              <Nav.Link href="/">Home</Nav.Link>
              <Nav.Link href="#features">Profile</Nav.Link>
              {supplierUser && <Nav.Link href="#pricing">Customers</Nav.Link>}
              {customerUser && <Nav.Link href="/meals">Meals</Nav.Link>}
              <Nav.Link onClick={toggleNotifications}>
                <FontAwesomeIcon icon={faBell} />

                {showNotifications && (
                  <div className="notifications">
                    <p>Notification 1</p>
                    <p>Notification 2</p>
                    <p>Notification 3</p>
                  </div>
                )}
              </Nav.Link>

              <div className="navbar-icons">
                <Dropdown>
                  <Dropdown.Toggle variant="primary" id="dropdown-basic">
                    {getProfileName()}
                  </Dropdown.Toggle>

                  <Dropdown.Menu>
                    <Dropdown.Item href="#/action-2">Profile</Dropdown.Item>
                    <Dropdown.Item onClick={logout}>Logout</Dropdown.Item>
                  </Dropdown.Menu>
                </Dropdown>
              </div>
            </Nav>
          </div>
        </Container>
      </Navbar>
    </>
  );
}}
