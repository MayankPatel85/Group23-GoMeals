import { React, useState } from "react";
import { Col, Button, Row, Container, Card, Form } from "react-bootstrap";
import Calendar from "react-calendar";
function SupplierPolling() {
  const [item1, setItem1] = useState({});
  const [item2, setItem2] = useState({});
  const [item3, setItem3] = useState({});
  const [item4, setItem4] = useState({});
  const [item5, setItem5] = useState({});
  const [dateValue, setItem6] = useState(new Date());

  function handleSubmit() {}
  return (
    <div classNmae="d-flex justify-content-center align-items-center">
      <Card className="shadow px-4">
        <Card.Body>
          <div className="mb-3 mt-md-4">
            <h2 className="fw-bold mb-2 text-center text-uppercase ">
              Meal Polling
            </h2>
            <div className="mb-3">
              <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="Item1">
                  <h2>Meal Option 1 :</h2>
                  <Form.Control
                    type="text"
                    placeholder="Enter 1st meal"
                    onChange={(e) => setItem1(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3" controlId="Item2">
                  <h2>Meal Option 2 :</h2>
                  <Form.Control
                    type="text"
                    placeholder="Enter 2nd meal"
                    onChange={(e) => setItem2(e.target.value)}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="Item3">
                  <h2>Meal Option 3 :</h2>
                  <Form.Control
                    type="text"
                    placeholder="Enter 3rd meal"
                    onChange={(e) => setItem3(e.target.value)}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="Item4">
                  <h2>Meal Option 5 :</h2>
                  <Form.Control
                    type="text"
                    placeholder="Enter 4th meal"
                    onChange={(e) => setItem4(e.target.value)}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="Item5">
                  <h2>Meal Option 5 :</h2>
                  <Form.Control
                    type="text"
                    placeholder="Enter 5th meal"
                    onChange={(e) => setItem5(e.target.value)}
                  />
                </Form.Group>
              </Form>
            </div>
          </div>
        </Card.Body>
      </Card>
    </div>
  );
}

export default SupplierPolling;
