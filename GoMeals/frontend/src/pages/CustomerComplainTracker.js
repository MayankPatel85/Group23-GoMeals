import { React, useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { Cookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import ComplainCard from "./CustomerComplainCard";

function CustomerComplainTracker() {
  const cookies = new Cookies();
  const [complainList, setComplainList] = useState([]);
  const loggedInUser = cookies.get("loggedInUser");
  const [deliveries, setDeliveries] = useState([]);

  const navigate = useNavigate();
  useEffect(() => {
    fetch(
      "http://localhost:8080/complain/get/all-customer/" + loggedInUser.cust_id
    )
      .then((res) => res.json())
      .then((listOfComplains) => {
        setComplainList(listOfComplains);
        console.log(listOfComplains);
        fetch(
          "http://localhost:8080/delivery/get/customer/" + loggedInUser.cust_id
        )
          .then((res) => res.json())
          .then((deliveryData) => {
            setDeliveries(deliveryData);
            console.log(deliveryData);
          });
      });
  }, []);

  return (
    <div>
      <Container>
        {complainList.map((complain) =>
          deliveries.map((delivery) =>
            complain.deliveryId == delivery.deliveryId ? (
              <ComplainCard complain={complain} delivery={delivery} />
            ) : (
              <></>
            )
          )
        )}
      </Container>
    </div>
  );
}

export default CustomerComplainTracker;
