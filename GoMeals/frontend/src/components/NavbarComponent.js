import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { Cookies } from "react-cookie";
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
    cookies.remove('loggedInUser');
    navigate("/");
  };

  const getProfileName = () => {
    if (loggedInUser.userType === 'customer') {
      return loggedInUser.cust_fname + " " + loggedInUser.cust_lname;
    } else if (loggedInUser.userType === 'supplier') {
      return loggedInUser.supName;
    }
  }

  function handleProfile() {
    if(loggedInUser.userType === 'customer') {
      navigate("/customerProfile");
    }
  }

  function handleSubscirptionRequests() {
    navigate("/subscriptionRequests");
  }

  return (
    <>
      <Navbar bg="primary" variant="light">
        <Container style={{ display: "flex", justifyContent: "space-between" }}>
          <Navbar.Brand href="#home">Go Meals</Navbar.Brand>
          <div>
            <Nav className="me-auto">
              <Nav.Link href="#home">Home</Nav.Link>
              <Nav.Link href="#features">Profile</Nav.Link>
              <Nav.Link href="#pricing">Customers</Nav.Link>
              {loggedInUser.userType === 'supplier' && <Nav.Link href="/supplierComplain">Complains</Nav.Link>}
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
                    <Dropdown.Item onClick={handleProfile}>Profile</Dropdown.Item>
                    {loggedInUser.userType === 'supplier' && <Dropdown.Item onClick={handleSubscirptionRequests}>Subscription Requests</Dropdown.Item> }
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
}
