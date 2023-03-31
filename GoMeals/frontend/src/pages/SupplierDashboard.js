import React, { useEffect, useState } from "react";
import {
  Button,
  Card,
  FormGroup,
  Container,
  Navbar,
  Spinner,
  Table,
  DropdownButton,
  Dropdown,
} from "react-bootstrap";
import axios from "axios";
import { Label, Input } from "reactstrap";
import CustomerList from "./CustomerList";
import { Cookies } from "react-cookie";
import NavbarComponent from "../components/NavbarComponent";

export default function SupplierDashboard() {
  const [alter, alterstate] = useState("create");
  const [mealchart, showmealchart] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [showCustomerList, setShowCustomerList] = useState(false);
  const [customerList, setCustomerList] = useState([]);
  const [subscriptionList, setSubscriptionList] = useState([]);
  const [deliveryData, setDeliveryData] = useState([]);
  const [showDelivery, setshowDelivery] = useState(true);
  const cookies = new Cookies();
  const loggedInUser = cookies.get("loggedInUser");
  console.log("logged" + loggedInUser);
  useEffect(() => {
  
    return () => {
      handleDelivery();
    }
  }, [])
  
  const handleClick = (param) => {
    showmealchart(true);
    setShowCustomerList(false);
    setshowDelivery(false);
    alterstate(param);
  };
  const handleDelivery = () => {
    // setshowDelivery(!showDelivery);
    setShowCustomerList(false);
    showmealchart(false);
    axios
      .get(`http://localhost:8080/delivery/get/supplier/${loggedInUser.supId}`)
      .then((response) => {
        setDeliveryData(response.data);
        console.log(response.data);
      });
  };
  function handleShowCustomers() {
    setIsLoading(true);
    setshowDelivery(false);
    console.log("supId" + JSON.stringify(loggedInUser));
    axios
      .get(`http://localhost:8080/supplier/get/${loggedInUser.supId}`)
      .then((response) => {
        setCustomerList(() => {
          return response.data.customers;
        });
        setSubscriptionList(response.data.subscriptions);
      })
      .catch((e) => {
        alert("Error getting data" + e);
      })
      .finally(() => {
        setIsLoading(false);
      });
    setShowCustomerList((prevValue) => {
      return !prevValue;
    });
  }
  const handleCreate = () => {
    const mealChart = [
      {
        id: {
          day: "monday",
          supId: loggedInUser.supId,
        },
        item1: document.getElementById("monday1").value,
        item2: document.getElementById("monday2").value,
        item3: document.getElementById("monday3").value,
        item4: document.getElementById("monday4").value,
        item5: document.getElementById("monday5").value,
        specialDate: "2022-02-01",
      },
      {
        id: {
          day: "tuesday",
          supId: loggedInUser.supId,
        },
        item1: document.getElementById("tuesday1").value,
        item2: document.getElementById("tuesday2").value,
        item3: document.getElementById("tuesday3").value,
        item4: document.getElementById("tuesday4").value,
        item5: document.getElementById("tuesday5").value,
        specialDate: "2022-02-01",
      },
      {
        id: {
          day: "wednesday",
          supId: loggedInUser.supId,
        },
        item1: document.getElementById("wednesday1").value,
        item2: document.getElementById("wednesday2").value,
        item3: document.getElementById("wednesday3").value,
        item4: document.getElementById("wednesday4").value,
        item5: document.getElementById("wednesday5").value,
        specialDate: "2022-02-01",
      },
      {
        id: {
          day: "thursday",
          supId: loggedInUser.supId,
        },
        item1: document.getElementById("thursday1").value,
        item2: document.getElementById("thursday2").value,
        item3: document.getElementById("thursday3").value,
        item4: document.getElementById("thursday4").value,
        item5: document.getElementById("thursday5").value,
        specialDate: "2022-02-01",
      },
      {
        id: {
          day: "friday",
          supId: loggedInUser.supId,
        },
        item1: document.getElementById("friday1").value,
        item2: document.getElementById("friday2").value,
        item3: document.getElementById("friday3").value,
        item4: document.getElementById("friday4").value,
        item5: document.getElementById("friday5").value,
        specialDate: "2022-02-01",
      },
      {
        id: {
          day: "saturday",
          supId: loggedInUser.supId,
        },
        item1: document.getElementById("saturday1").value,
        item2: document.getElementById("saturday2").value,
        item3: document.getElementById("saturday3").value,
        item4: document.getElementById("saturday4").value,
        item5: document.getElementById("saturday5").value,
        specialDate: "2022-02-01",
      },
      {
        id: {
          day: "sunday",
          supId: loggedInUser.supId,
        },
        item1: document.getElementById("sunday1").value,
        item2: document.getElementById("sunday2").value,
        item3: document.getElementById("sunday3").value,
        item4: document.getElementById("sunday4").value,
        item5: document.getElementById("sunday5").value,
        specialDate: "2022-02-01",
      },
    ];
    // item1:
    if (alter == "create") {
      axios
        .post("http://localhost:8080/meal_chart/create", mealChart)
        .then((response) => {
          console.log(response.data);
          alert("Data stored");
          // navigate("/supplierDashboard");
        })
        .catch((error) => {
          console.log(error);
          alert("Data was not sent");
        });
    } else {
      axios
        .put("http://localhost:8080/meal_chart/update", mealChart)
        .then((response) => {
          console.log(response.data);
          alert("Data stored");
          // navigate("/supplierDashboard");
        })
        .catch((error) => {
          console.log(error);
          alert("Data was not sent");
          console.log(mealChart);
        });
    }
  };
  const DeliveryColumns = [
    { header: "Customer ID", accessor: "custId" },
    { header: "Delivery ID", accessor: "deliveryId" },
    { header: "Delivery Date", accessor: "deliveryDate" },
    { header: "Meal", accessor: "deliveryMeal" },
    {
      header: "Order status",
      accessor: "orderStatus",
      Cell: ({ value, deliveryId }) => (
        <DropdownButton
          title={value}
          onSelect={(eventKey) => {
            handleOrderStatusChange(deliveryId, eventKey);
            value=''
          }}
        >
          <Dropdown.Item eventKey="completed">completed</Dropdown.Item>
        </DropdownButton>
      ),
    },
  ];
  const handleOrderStatusChange = (deliveryId, orderStatus) => {
    axios
      .put(
        `http://localhost:8080/delivery/update-status/?deliveryId=${deliveryId}&orderStatus=${orderStatus}`
      )
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div>
      {/* <NavbarComponent /> */}
      <br />
      <h2>Welcome Supplier</h2>
      <br />
      <Button variant="outline-primary" onClick={() => handleClick("create")}>
        Create Meal Chart
      </Button>{" "}
      <Button variant="outline-primary" onClick={() => handleClick("update")}>
        Update Meal Chart
      </Button>{" "}
      <Button variant="outline-primary" onClick={handleDelivery}>
        Delivery
      </Button>{" "}
      <Button variant="outline-primary" onClick={handleShowCustomers}>
        View Customers
      </Button>
      {mealchart && (
        <div>
          <Card className="mechco">
            <Card.Body>
              <h3>Meal Plan Details</h3>
              <table>
                <tr>
                  <td>Monday:</td>
                  <td>
                    <input type="text" id="monday1" />
                  </td>
                  <td>
                    <input type="text" id="monday2" />
                  </td>
                  <td>
                    <input type="text" id="monday3" />
                  </td>
                  <td>
                    <input type="text" id="monday4" />
                  </td>
                  <td>
                    <input type="text" id="monday5" />
                  </td>
                </tr>
                <br />
                <tr>
                  <td>Tuesday:</td>
                  <td>
                    <input type="text" id="tuesday1" />
                  </td>
                  <td>
                    <input type="text" id="tuesday2" />
                  </td>
                  <td>
                    <input type="text" id="tuesday3" />
                  </td>
                  <td>
                    <input type="text" id="tuesday4" />
                  </td>
                  <td>
                    <input type="text" id="tuesday5" />
                  </td>
                </tr>
                <br />
                <tr>
                  <td>Wednesday:</td>
                  <td>
                    <input type="text" id="wednesday1" />
                  </td>
                  <td>
                    <input type="text" id="wednesday2" />
                  </td>
                  <td>
                    <input type="text" id="wednesday3" />
                  </td>
                  <td>
                    <input type="text" id="wednesday4" />
                  </td>
                  <td>
                    <input type="text" id="wednesday5" />
                  </td>
                </tr>
                <br />
                <tr>
                  <td>Thursday:</td>
                  <td>
                    <input type="text" id="thursday1" />
                  </td>
                  <td>
                    <input type="text" id="thursday2" />
                  </td>
                  <td>
                    <input type="text" id="thursday3" />
                  </td>
                  <td>
                    <input type="text" id="thursday4" />
                  </td>
                  <td>
                    <input type="text" id="thursday5" />
                  </td>
                </tr>
                <br />
                <tr>
                  <td>Friday:</td>
                  <td>
                    <input type="text" id="friday1" />
                  </td>
                  <td>
                    <input type="text" id="friday2" />
                  </td>
                  <td>
                    <input type="text" id="friday3" />
                  </td>
                  <td>
                    <input type="text" id="friday4" />
                  </td>
                  <td>
                    <input type="text" id="friday5" />
                  </td>
                </tr>
                <br />
                <tr>
                  <td>Saturday:</td>
                  <td>
                    <input type="text" id="saturday1" />
                  </td>
                  <td>
                    <input type="text" id="saturday2" />
                  </td>
                  <td>
                    <input type="text" id="saturday3" />
                  </td>
                  <td>
                    <input type="text" id="saturday4" />
                  </td>
                  <td>
                    <input type="text" id="saturday5" />
                  </td>
                </tr>
                <br />
                <tr>
                  <td>Sunday:</td>
                  <td>
                    <input type="text" id="sunday1" />
                  </td>
                  <td>
                    <input type="text" id="sunday2" />
                  </td>
                  <td>
                    <input type="text" id="sunday3" />
                  </td>
                  <td>
                    <input type="text" id="sunday4" />
                  </td>
                  <td>
                    <input type="text" id="sunday5" />
                  </td>
                </tr>
              </table>
              <br />
              <Button variant="outline-primary" onClick={handleCreate}>
                Upload
              </Button>{" "}
            </Card.Body>
          </Card>
        </div>
      )}
      {showCustomerList ? (
        isLoading ? (
          <Container className="my-5 mx-auto">
            <Spinner variant="primary" />
          </Container>
        ) : (
          <CustomerList
            customerList={customerList}
            subscriptionList={subscriptionList}
          />
        )
      ) : null}
      <br />
      <br />
      {showDelivery && (
        <DeliveryTable columns={DeliveryColumns} data={deliveryData} />
      )}
      <Navbar
        bg="primary"
        variant="light"
        className="justify-content-center align-items-center"
      >
        <h3>©Go Meals</h3>
      </Navbar>
    </div>
  );
}

function DeliveryTable(props) {
  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          {props.columns.map((column) => (
            <th key={column.accessor}>{column.header}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {props.data.map((row) => {
          return (
            <tr key={row.cust_id}>
              {props.columns.map((column) => (
                <td key={column.accessor}>
                  {column.Cell ? (
                    <column.Cell
                      value={row[column.accessor]}
                      deliveryId={row.deliveryId}
                    />
                  ) : (
                    row[column.accessor]
                  )}
                </td>
              ))}
            </tr>
          );
          return null;
        })}
      </tbody>
    </Table>
  );
}
