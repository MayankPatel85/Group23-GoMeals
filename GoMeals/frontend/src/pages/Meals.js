import React, { useEffect, useState } from "react";
import {
  Accordion,
  Button,
  Col,
  Container,
  Row,
  Form,
  InputGroup,
  FormControl,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import NumberInput from "../components/NumberInput";
import Swal from "sweetalert2";
import { Cookies } from "react-cookie";
import axios from "axios";

const MealAccordion = () => {
  const cookies = new Cookies();
  const loggedInUser = cookies.get("loggedInUser");
  const [expanded, setExpanded] = useState([]);
  const [deliveries, setDeliveries] = useState([]);
  const [isDeleting, setIsDeleting] = useState(false);
  const navigate = useNavigate();
  const [activeKey, setActiveKey] = useState(null); // Declare and define the activeKey state variable and the setActiveKey function
  const [showAddOnsForm, setShowAddOnsForm] = useState(false);
  const [addOnsText, setAddOnsText] = useState("");
  const [isDeliveryAddons, setIsDeliveryAddons] = useState([]);

  const customerId = loggedInUser.cust_id;
  useEffect(() => {
    if (customerId) {
      console.log("customer id:" + customerId);
      axios
        .get(`http://localhost:8080/delivery/get/customer/${customerId}`)
        .then((response) => {
          console.log("printing respose");
          console.log(response.data);
          const sortedDeliveries = [...response.data].sort((a, b) => {
            if (a.deliveryDate < b.deliveryDate) {
              return -1;
            } else if (a.deliveryDate > b.deliveryDate) {
              return 1;
            } else {
              return 0;
            }
          });
          setDeliveries(sortedDeliveries);
          console.log(sortedDeliveries);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [customerId]);

  const [showModal, setShowModal] = useState(false);

  const handleDelete = () => {
    // TODO: Implement the delete functionality
    // ...
    setShowModal(false);
  };

  const handleCancel = () => {
    setShowModal(false);
  };

  const handleClick = () => {
    setShowModal(true);
  };

  const handleAddOnsFormSubmit = (e) => {
    e.preventDefault();
    // handle form submission here
    setShowAddOnsForm(false);
  };

  const renderAddOnsForm = () => {
    return (
      <Form onSubmit={handleAddOnsFormSubmit}>
        <InputGroup className="mb-3">
          {isDeliveryAddons.map((addon) => {
            return (
              <NumberInput
                name="quantity2"
                min={1}
                max={100}
                step={1}
                initialValue={addon.quantity}
              />
            );
          })}

          <Button variant="outline-primary" type="submit" className="mt-3">
            Submit
          </Button>
        </InputGroup>
      </Form>
    );
  };

  const handleAddOnsClick = (deliveryId) => {
    console.log("delivery ID" + deliveryId);
    setShowAddOnsForm(!showAddOnsForm);
    getDeliveryAddons(deliveryId);
  };

  const groupedMeals = deliveries.reduce((acc, meal) => {
    const key =
      meal.deliveryDate === new Date().toISOString().slice(0, 10)
        ? "Today"
        : meal.deliveryDate;
    // console.log(new Date().toISOString().slice(0, 10))
    if (!acc[key]) {
      acc[key] = [];
    }
    acc[key].push(meal);
    return acc;
  }, {});

  const renderMealCards = (groupedMeals) => {
    return Object.keys(groupedMeals).map((date) => {
      return (
        <div key={date}>
          <h3>{date}</h3>
          <Accordion>
            {groupedMeals[date].map((meal) => (
              <Accordion.Item
                key={meal.deliveryId}
                eventKey={meal.deliveryId}
                onSelect={() => {
                  if (expanded.includes(meal.deliveryId)) {
                    setExpanded(
                      expanded.filter((id) => id !== meal.deliveryId)
                    );
                  } else {
                    setExpanded([...expanded, meal.deliveryId]);
                  }
                }}
              >
                <Accordion.Header>
                  <div>
                    <h5>{meal.title}</h5>
                    <p>Delivery Status: {meal.orderStatus}</p>
                  </div>
                </Accordion.Header>
                <Accordion.Body>
                  <div>
                    <p>Items:</p>
                    <ul>
                      {meal.items.map((item) => (
                        <li key={item}>{item}</li>
                      ))}
                    </ul>
                  </div>
                  <Button
                    variant="primary"
                    onClick={() => handleAddOnsClick(meal.deliveryId)}
                  >
                    Add Add-Ons
                  </Button>
                </Accordion.Body>
              </Accordion.Item>
            ))}
          </Accordion>
        </div>
      );
    });
  };
  function deleteMealById(deliveryId) {
    const endpoint = `http://localhost:8080/delivery/delete/${deliveryId}`;
    axios
      .delete(endpoint)
      .then((response) => {
        console.log(
          `Meal with delivery ID ${deliveryId} deleted successfully.`
        );
        setIsDeleting(true);
        // handle success response here
      })
      .catch((error) => {
        console.error(
          `Error deleting meal with delivery ID ${deliveryId}: ${error}`
        );
        setIsDeleting(false);

        // handle error response here
      });
  }
  function getDeliveryAddons(deliveryId) {
    const endpoint = `http://localhost:8080/deliveryAddons/get/${deliveryId}`;
    axios
      .get(endpoint)
      .then((response) => {
        console.log(response.data);

        const updatedDeliveries = deliveries.map((delivery) => {
          const matchingAddons = response.data.filter(
            (addon) => addon.deliveryId === delivery.deliveryId
          );
          return {
            ...delivery,
            addons: matchingAddons,
          };
        });
        setDeliveries(updatedDeliveries);

        // setIsDeliveryAddons(response.data);
        // setDeliveries(...deliveries,response.data)
        // handle success response here
      })
      .catch((error) => {
        console.error(`Error: ${error}`);
        // handle error response here
      });
  }
  const handleDeleteClick = (deliveryId) => {
    Swal.fire({
      title: "Are you sure?",
      text: `Do you want to delete this meal?`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: "Yes, delete it!",
      confirmButtonColor: "#dc3545",
      cancelButtonText: "No, keep it",
    }).then((result) => {
      if (result.isConfirmed) {
        // Calling API to delete the meal here
        console.log(deliveryId);
        deleteMealById(deliveryId);
        setIsDeleting(true);
      }
    });
  };
  return (
    <div style={{ backgroundColor: "#f5f5f5", height: "100vh" }}>
      <Container>
        <Row className="pt-5">
          <Col>
            <h1 className="text-start">Meals</h1>
          </Col>
        </Row>
        {Object.keys(groupedMeals).map((date) => {
          return (
            <div key={date}>
              <Row>
                <Col className="text-start mt-3">
                  <h3>{date}</h3>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Accordion activeKey={activeKey}>
                    {groupedMeals[date].map((meal) => (
                      <Accordion.Item
                        key={meal.deliveryId}
                        eventKey={meal.deliveryId.toString()}
                        onClick={() => setActiveKey(meal.deliveryId.toString())}
                      >
                        <Accordion.Header>
                          <div>
                            <h5>{meal.title}</h5>
                            <p>Delivery Status: {meal.orderStatus}</p>
                          </div>
                        </Accordion.Header>
                        <Accordion.Body>
                          <div className="d-flex">
                            <p>Items: </p>
                            <div className="ms-1 d-flex">
                              {/* {meal.deliveryMeal.map((item, index) => (
                                <React.Fragment key={item}>
                                  {index !== 0 && ", "}{" "}
                                  <p>{item}</p>
                                </React.Fragment>
                              ))} */}

                              {meal.deliveryMeal}
                            </div>
                          </div>

                          <div className="d-flex">
                            <Button
                              variant="primary"
                              onClick={() => handleAddOnsClick(meal.deliveryId)}
                            >
                              Add Add-Ons
                            </Button>

                            <Button
                              variant="danger"
                              onClick={() => handleDeleteClick(meal.deliveryId)}
                              className="ms-4"
                            >
                              Cancel Meals
                            </Button>
                          </div>
                          {/* {showAddOnsForm && renderAddOnsForm()} */}

                          {showAddOnsForm && (
                            <Form onSubmit={handleAddOnsFormSubmit}>
                              <InputGroup className="mb-3">
                                {!meal.addons
                                  ? null
                                  : meal.addons.map((addon) => {
                                      return (
                                        <NumberInput
                                          name="quantity2"
                                          min={1}
                                          max={100}
                                          step={1}
                                          initialValue={addon.quantity}
                                        />
                                      );
                                    })}
                                <Button
                                  variant="outline-primary"
                                  type="submit"
                                  className="mt-3"
                                >
                                  Submit
                                </Button>
                              </InputGroup>
                            </Form>
                          )}
                        </Accordion.Body>
                      </Accordion.Item>
                    ))}
                  </Accordion>
                </Col>
              </Row>
            </div>
          );
        })}
      </Container>
    </div>
  );
};

export default MealAccordion;
