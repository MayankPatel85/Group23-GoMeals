import React, { useState } from "react";
import axios from "axios";
import { Col, Button, Row, Container, Card, Form } from "react-bootstrap";
import { NavLink, useNavigate } from "react-router-dom";

function SupplierRegister() {
  const [supName, setName] = useState("");
  const [supAddress, setAddress] = useState("");
  const [supEmail, setEmail] = useState("");
  const [supContactNumber, setContactNumber] = useState("");
  const [govtIssuedId, setGovtIssueId] = useState("");
  const [password, setPassword] = useState("");
  const [mealPrice, setMealPrice] = useState("");
  const [paypalLink, setPaypalLink] = useState("");
  const navigate = useNavigate();
  const handleSubmit = (event) => {
    event.preventDefault();
    const supplier = {
      supName: supName,
      supAddress: supAddress,
      supEmail: supEmail,
      govtIssuedId: govtIssuedId,
      supContactNumber: supContactNumber,
      password: password,
      mealPrice: mealPrice,
      paypalLink: paypalLink
    };
    axios
      .post("http://localhost:8080/supplier/create", supplier)
      .then((response) => {
        console.log(response.data);
        navigate("/login");
        alert("Supplier registration was succesful");
      })
      .catch((error) => {
        console.log(error);
        if (error.response) {
          const { data } = error.response;
          alert(`Registration failed: ${data.message}`);
        } else {
          console.log(supplier);
          alert("Registration failed. Please try again later.");
        }
      });
  };

  return (
    <>
      <div>
        <Container>
          <Row className="vh-100 d-flex justify-content-center align-items-center">
            <Col md={8} lg={6} xs={12}>
              <div className="border border-2 border-primary"></div>
              <Card className="shadow px-4">
                <Card.Body>
                  <div className="mb-3 mt-md-4">
                    <h2 className="fw-bold mb-2 text-center text-uppercase ">
                      Go Meals - Supplier Registration
                    </h2>
                    <div className="mb-3">
                      <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-3" controlId="FName">
                          <Form.Control
                            type="text"
                            placeholder="Enter Name"
                            value={supName}
                            onChange={(e) => setName(e.target.value)}
                          />
                        </Form.Group>



                        <Form.Group className="mb-3" controlId="formBasicEmail">
                          <Form.Control
                            type="email"
                            placeholder="Enter email"
                            value={supEmail}
                            onChange={(e) => setEmail(e.target.value)}
                          />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formAddress">
                          <Form.Control
                            type="text"
                            placeholder="Enter your Address"
                            value={supAddress}
                            onChange={(e) => setAddress(e.target.value)}
                          />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formContact">
                          <Form.Control
                            type="text"
                            placeholder="Enter your Phone"
                            value={supContactNumber}
                            onChange={(e) => setContactNumber(e.target.value)}
                          />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="LName">
                          <Form.Control
                            type="text"
                            placeholder="Enter Government Issued ID"
                            value={govtIssuedId}
                            onChange={(e) => setGovtIssueId(e.target.value)}
                          />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="MealPrice">
                          <Form.Control type="text" placeholder="Enter meal price" value={mealPrice} onChange={(e) => setMealPrice(e.target.value)}></Form.Control>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="PaypalLink">
                          <Form.Control type="text" placeholder="Enter Paypal link" value={paypalLink} onChange={(e) => setPaypalLink(e.target.value)}></Form.Control>
                        </Form.Group>

                        <Form.Group
                          className="mb-3"
                          controlId="formBasicPassword"
                        >
                          <Form.Control
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                          />
                        </Form.Group>
                        <Form.Group
                          className="mb-3"
                          controlId="formBasicPassword2"
                        >
                          <Form.Control
                            type="password"
                            placeholder="Confirm password"
                          />
                        </Form.Group>
                        <Form.Group
                          className="mb-3"
                          controlId="formBasicCheckbox"
                        ></Form.Group>
                        <div className="d-grid">
                          <Button variant="primary" type="submit">
                            Create Supplier Account
                          </Button>
                        </div>
                      </Form>
                      <div className="mt-3">
                        <p className="mb-0  text-center">
                          Already have an account??{" "}
                          {/* <Link to="/login">login</Link> */}
                          <NavLink
                            className=""
                            activeClassName="is-active"
                            to="/login"
                            exact
                          >
                            Login
                          </NavLink>
                        </p>
                      </div>
                    </div>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    </>
  );
}

export default SupplierRegister;
