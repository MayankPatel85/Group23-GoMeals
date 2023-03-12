import React, { useState, useEffect } from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import pizza from "../resources/pizza.jpg";
import { NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { Container } from "react-bootstrap";
import { Cookies } from "react-cookie";

const CustomerDeliveries = ({ history }) => {
  const cookies = new Cookies();
  const [customerOrders, setCustomerOrders] = useState([]);
  const [complainDeliveryId, setComplainDeliveryId] = useState(0);
  const navigate = useNavigate();
  const loggedInUser = cookies.get("loggedInUser");
  useEffect(() => {
    fetch("http://localhost:8080/delivery/get/customer/" + loggedInUser.cust_id)
      .then((res) => res.json())
      .then((val) => setCustomerOrders(val))
      .then(() => {
        console.log(customerOrders);
      });
  }, []);

  const handleClick = (deliveryId) => {
    setComplainDeliveryId(deliveryId);
    navigate(`/customerRaiseComplain/${deliveryId}`);
  };

  return (
    <div className="orderCard">
      <Container>
        {customerOrders.length == 0 ? (
          <div>No Orders available</div>
        ) : (
          <div>
            {customerOrders.map((customerOrder) => (
              <Card style={{ width: "18rem" }} key={customerOrder.deliveryId}>
                <Card.Img variant="top" src={pizza} />
                <Card.Body>
                  <Card.Title>{customerOrder.deliveryMeal}</Card.Title>
                  <Card.Text>
                    Delivery Id : {customerOrder.deliveryId}
                    <br />
                    Delivery Date : {customerOrder.deliveryDate}
                    <br />
                    Delivery Status : {customerOrder.orderStatus}
                  </Card.Text>
                  <Button onClick={() => handleClick(customerOrder.deliveryId)}>
                    Raise Complain
                  </Button>
                </Card.Body>
              </Card>
            ))}
          </div>
        )}
      </Container>
    </div>
  );
};

export default CustomerDeliveries;
