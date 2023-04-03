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
import foodcart from "../resources/shopping-cart.png";
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

  const getProfileName = () => {
    if (loggedInUser.userType === "customer") {
      return loggedInUser.cust_fname + " " + loggedInUser.cust_lname;
    } else if (loggedInUser.userType === "supplier") {
      return loggedInUser.supName;
    }
  };

  function handleProfile() {
    if (loggedInUser.userType === "customer") {
      navigate("/customerProfile");
    }
  }

  function handlePollVote() {
    navigate("/customerPollVote");
  }

  function handlePaymentHistory() {
    navigate("/customerPaymentHistory");
  }

  function handleOrders() {
    navigate("/customerOrders");
  }

  function handleComplain() {
    navigate("/complainTracker");
  }

  function handleSupplierComplain() {
    navigate("/supplierComplain");
  }

  function handleSupplierPolling() {
    navigate("/supplierPolling");
  }

  function handleSupplierPollingDetails() {
    navigate("/supplierPollingDetails");
  }
  return (
    <>
      <Navbar bg="primary" variant="light">
        <Container style={{ display: "flex", justifyContent: "space-between" }}>
          <Navbar.Brand
            href={
              loggedInUser.userType === "supplier"
                ? "/supplierDashboard"
                : "/dashboard"
            }
          >
            <img src={foodcart} id="foodCart" width="30px" height="30px"></img>
            Go Meals
          </Navbar.Brand>
          <div>
            <Nav className="me-auto">
              <Nav.Link
                href={
                  loggedInUser.userType === "supplier"
                    ? "/supplierDashboard"
                    : "/dashboard"
                }
              >
                Home
              </Nav.Link>
              <Nav.Link href="#features">Profile</Nav.Link>

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
                    <Dropdown.Item onClick={handleProfile}>
                      Profile
                    </Dropdown.Item>
                    {loggedInUser.userType === "supplier" ? (
                      <>
                        <Dropdown.Item>Customers</Dropdown.Item>
                        <Dropdown.Item onClick={handleSupplierComplain}>
                          Complains
                        </Dropdown.Item>
                        <Dropdown.Item onClick={handleSupplierPolling}>
                          Polling
                        </Dropdown.Item>
                        <Dropdown.Item onClick={handleSupplierPollingDetails}>
                          Polling Details
                        </Dropdown.Item>
                      </>
                    ) : (
                      <>
                        <Dropdown.Item onClick={handleComplain}>
                          Complain
                        </Dropdown.Item>
                        <Dropdown.Item onClick={handleOrders}>
                          Orders
                        </Dropdown.Item>
                        <Dropdown.Item onClick={handlePaymentHistory}>
                          Payment History
                        </Dropdown.Item>
                        <Dropdown.Item onClick={handlePollVote}>
                          Meal Poll
                        </Dropdown.Item>
                      </>
                    )}
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
