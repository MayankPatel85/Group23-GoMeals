import React, { useState, useEffect, useRef } from "react";
import { Container } from "react-bootstrap";
import { Col, Button, Row } from "react-bootstrap";
import { useParams } from "react-router";
import Form from "react-bootstrap/Form";

function CustomerComplain() {
  const [delivery, setDelivery] = useState({});
  const [cust_comment, setCustomerComment] = useState("");
  const { id } = useParams();

  useEffect(() => {
    fetch("http://localhost:8080/delivery/get/" + id)
      .then((res) => res.json())
      .then((data) => {
        setDelivery(data);
        console.log(data);
      });
  }, []);

  let getCurrentDate = () => {
    const today = new Date();
    const day = String(today.getDate()).padStart(2, "0");
    const month = String(today.getMonth() + 1).padStart(2, "0"); // January is 0!
    const year = today.getFullYear();
    const currentDate = `${day}-${month}-${year}`;

    return currentDate;
  };

  let raiseComplain = () => {
    console.log("Raise Complain Called!");
    let complainObject = {
      complainId: null,
      date: getCurrentDate(),
      delivery_id: delivery.delivery_id,
      customerComment: cust_comment,
      status: "Initiated",
      customerId: 1,
      supplierId: delivery.supId,
    };
    let complain = JSON.stringify(complainObject);
    document.write(complain);
    console.log(complain + "complain is");
    fetch("http://localhost:8080/complain/create", {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: complain,
    });
    console.log("Working" + id);
  };

  return (
    <div>
      <Container>
        <Row className="vh-100 d-flex justify-content-center align-items-center">
          <Col md={8} lg={6} xs={12}>
            <Form onSubmit={raiseComplain}>
              <Form.Label>Date</Form.Label>
              <Form.Control
                type="text"
                placeholder={delivery.deliveryDate}
                aria-label="Order"
                disabled
                readOnly
              />
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlInput1"
              >
                <Form.Label>Meal</Form.Label>
                <Form.Control
                  type="text"
                  placeholder={delivery.deliveryMeal}
                  disabled
                  readOnly
                />
              </Form.Group>
              <Form.Group
                className="mb-3"
                controlId="exampleForm.ControlTextarea1"
              >
                <Form.Label>Enter Complain</Form.Label>
                <Form.Control
                  as="textarea"
                  rows={3}
                  value={cust_comment}
                  onChange={(e) => setCustomerComment(e.target.value)}
                />
              </Form.Group>
              <Button variant="primary" type="submit">
                Complain
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default CustomerComplain;
