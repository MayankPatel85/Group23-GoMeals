import React, { useState, useEffect, useRef } from 'react'
import { Button, Card, FormGroup, Container, Navbar, Spinner, Modal, Form } from 'react-bootstrap';
import axios from "axios";
import { Label, Input } from 'reactstrap';
import CustomerList from './CustomerList';
import { Cookies } from 'react-cookie';
import NavbarComponent from '../components/NavbarComponent';

export default function SupplierDashboard() {

    const [mealchart, showmealchart] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [showCustomerList, setShowCustomerList] = useState(false);
    const [customerList, setCustomerList] = useState([]);
    const [subscriptionList, setSubscriptionList] = useState([]);
    const cookies = new Cookies();
    const loggedInUser = cookies.get('loggedInUser');
    const [currentSupplier, setCurrentSupplier] = useState({});
    const [editProfile, setEditProfile] = useState(false);
    const [updateSupplierData, setUpdateSupplierData] = useState(false);
    const [editedSupplierDetail, setEditedSupplierDetail] = useState({
        supEmail: "",
        supContactNumber: "",
        supAddress: "",
        supPaypalLink: ""
    });

    const handleClick = () => {
        showmealchart(true);
    };
    const handleCreate = () => {
        const mealChart = [{
            id: {
                day: "monday",
                supId: 2
            },
            item1: document.getElementById("monday1").value,
            item2: document.getElementById("monday2").value,
            item3: document.getElementById("monday3").value,
            item4: document.getElementById("monday4").value,
            item5: document.getElementById("monday5").value,
            specialDate: "2022-02-01"

        },
        {
            id: {
                day: "tuesday",
                supId: 2
            },
            item1: document.getElementById("tuesday1").value,
            item2: document.getElementById("tuesday2").value,
            item3: document.getElementById("tuesday3").value,
            item4: document.getElementById("tuesday4").value,
            item5: document.getElementById("tuesday5").value,
            specialDate: "2022-02-01"

        },
        {
            id: {
                day: "wednesday",
                supId: 2
            },
            item1: document.getElementById("wednesday1").value,
            item2: document.getElementById("wednesday2").value,
            item3: document.getElementById("wednesday3").value,
            item4: document.getElementById("wednesday4").value,
            item5: document.getElementById("wednesday5").value,
            specialDate: "2022-02-01"

        },
        {
            id: {
                day: "thursday",
                supId: 2
            },
            item1: document.getElementById("thursday1").value,
            item2: document.getElementById("thursday2").value,
            item3: document.getElementById("thursday3").value,
            item4: document.getElementById("thursday4").value,
            item5: document.getElementById("thursday5").value,
            specialDate: "2022-02-01"

        },
        {
            id: {
                day: "friday",
                supId: 2
            },
            item1: document.getElementById("friday1").value,
            item2: document.getElementById("friday2").value,
            item3: document.getElementById("friday3").value,
            item4: document.getElementById("friday4").value,
            item5: document.getElementById("friday5").value,
            specialDate: "2022-02-01"

        },
        {
            id: {
                day: "saturday",
                supId: 2
            },
            item1: document.getElementById("saturday1").value,
            item2: document.getElementById("saturday2").value,
            item3: document.getElementById("saturday3").value,
            item4: document.getElementById("saturday4").value,
            item5: document.getElementById("saturday5").value,
            specialDate: "2022-02-01"

        },
        {
            id: {
                day: "sunday",
                supId: 2
            },
            item1: document.getElementById("sunday1").value,
            item2: document.getElementById("sunday2").value,
            item3: document.getElementById("sunday3").value,
            item4: document.getElementById("sunday4").value,
            item5: document.getElementById("sunday5").value,
            specialDate: "2022-02-01"

        },
        ]
        // item1:
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
    };

    const handleEditProfile = () => {
        setEditProfile(true);
        setEditedSupplierDetail({
            "supEmail": currentSupplier.supEmail,
            "supContactNumber": currentSupplier.supContactNumber,
            "supAddress": currentSupplier.supAddress,
            "supPaypalLink": currentSupplier.paypalLink
        });
    };

    const handleEditingProfile = (event) => {
        const { name, value } = event.target;
        setEditedSupplierDetail((prevValue) => ({
            ...prevValue,
            [name]: value,
        }));
    };

    useEffect(() => {
        setIsLoading(true);
        axios
            .get(`http://localhost:8080/supplier/get/${loggedInUser.supId}`)
            .then((response) => {
                setCurrentSupplier(response.data);
            })
            .catch((e) => {
                alert("Error getting data" + e)
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, [loggedInUser.supId]);

    useEffect(() => {
        if (updateSupplierData) {
            axios
                .put("http://localhost:8080/supplier/update", currentSupplier)
                .catch((e) => {
                    alert("Error getting data" + e)
                })
                .finally(() => {
                    setIsLoading(false);
                    setEditProfile(false);
                });
        }
    }, [updateSupplierData]);

    function handleShowCustomers() {
        setIsLoading(true);
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
                alert("Error getting data" + e)
            })
            .finally(() => {
                setIsLoading(false);
            });
        setShowCustomerList(prevValue => {
            return !prevValue;
        });
    };

    const updateSupplierProfile = () => {
        if (validateProfileInputs()) {
            //setIsLoading(true);
            console.log("before setting", JSON.stringify(editedSupplierDetail))
            setCurrentSupplier((prevValue) => ({
                ...prevValue,
                supEmail: editedSupplierDetail.supEmail,
                supContactNumber: editedSupplierDetail.supContactNumber,
                supAddress: editedSupplierDetail.supAddress,
                supPaypalLink: editedSupplierDetail.supPaypalLink
            }));
            setUpdateSupplierData(true);
        }
    };

    const validateProfileInputs = () => {
        const regexForNumber = /^[0-9\b]+$/;
        if (editedSupplierDetail.supEmail === "" || editedSupplierDetail.supContactNumber === "" || editedSupplierDetail.supAddress === "" || editedSupplierDetail.supPaypalLink === "") {
            alert("Fields should not be empty.");
            return false;
        } else if (editedSupplierDetail.supContactNumber.length !== 10 || !regexForNumber.test(editedSupplierDetail.supContactNumber)) {
            alert("Please provide a valid contact number.");
            return false;
        } else if (!editedSupplierDetail.supPaypalLink.includes("paypal.com")) {
            alert("Please provide valid Paypal link.");
            return false;
        }
        return true;
    }

    return (
        <div>
            <NavbarComponent />
            {isLoading ? <Spinner />
                :
                <div>
                    <Modal show={editProfile} onHide={() => setEditProfile(false)} centered>
                        <Modal.Header closeButton>
                            <Modal.Title>Edit your profile</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <Form>
                                <Form.Group className="mb-3" controlId="email">
                                    <Form.Label>Email address</Form.Label>
                                    <Form.Control
                                        type="email"
                                        placeholder="name@example.com"
                                        name="supEmail"
                                        value={editedSupplierDetail.supEmail}
                                        onChange={handleEditingProfile}
                                        autoFocus
                                    />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="contactNo">
                                    <Form.Label>Contact Number</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Your contact number"
                                        name="supContactNumber"
                                        value={editedSupplierDetail.supContactNumber}
                                        onChange={handleEditingProfile}
                                    />
                                </Form.Group>
                                <Form.Group
                                    className="mb-3"
                                    controlId="address"
                                >
                                    <Form.Label>Address</Form.Label>
                                    <Form.Control as="textarea" rows={2} name="supAddress" value={editedSupplierDetail.supAddress} onChange={handleEditingProfile} />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="paypalId">
                                    <Form.Label>Paypal Link</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Your contact number"
                                        name="supPaypalLink"
                                        value={editedSupplierDetail.supPaypalLink}
                                        onChange={handleEditingProfile}
                                    />
                                </Form.Group>
                            </Form>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={() => setEditProfile(false)}>
                                Close
                            </Button>
                            <Button variant="primary" onClick={updateSupplierProfile}>
                                Save Changes
                            </Button>
                        </Modal.Footer>
                    </Modal>
                    <div className="container customer-profile my-4">
                        <div className="col-lg-12">
                            <div className="d-flex justify-content-between align-items-center">
                                <div>
                                    <h2 className="d-inline">Hi, </h2>
                                    <h2 className="d-inline">{currentSupplier.supName}</h2>
                                </div>
                                <Button variant="dark" onClick={handleEditProfile} >Edit Profile</Button>
                            </div>
                        </div>
                        <div className="row mb-2 profile-item">
                            <div className="col-lg-12">
                                <h4 className="d-inline">Email</h4>
                                <hr></hr>
                            </div>
                            <div className="col-lg-12">
                                <h4 className="d-inline">{currentSupplier.supEmail}</h4>
                            </div>
                        </div>
                        <div className="row mb-2 profile-item">
                            <div className="col-lg-12">
                                <h4 className="d-inline">Contact number</h4>
                                <hr></hr>
                            </div>
                            <div className="col-lg-12">
                                <h4 className="d-inline">{currentSupplier.supContactNumber}</h4>
                            </div>
                        </div>
                        <div className="row mb-2 profile-item">
                            <div className="col-lg-12">
                                <h4 className="d-inline">Address</h4>
                                <hr></hr>
                            </div>
                            <div className="col-lg-12">
                                <h4 className="d-inline">{currentSupplier.supAddress}</h4>
                            </div>
                        </div>
                        <div className="row mb-2 profile-item">
                            <div className="col-lg-12">
                                <h4 className="d-inline">Paypal Link</h4>
                                <hr></hr>
                            </div>
                            <div className="col-lg-12">
                                <h4 className="d-inline">{currentSupplier.paypalLink}</h4>
                            </div>
                        </div>
                    </div>
                    <Button variant="outline-primary" onClick={handleClick}>Create Meal Chart</Button>{' '}
                    <Button variant="outline-primary" onClick={handleShowCustomers}>View Customers</Button>
                    {mealchart &&
                        <div><Card className="mechco">
                            <Card.Body>
                                <h3>Meal Plan Details</h3>
                                <table>
                                    <tr>
                                        <td>Monday:</td>
                                        <td><input type="text" id="monday1" /></td>
                                        <td><input type="text" id="monday2" /></td>
                                        <td><input type="text" id="monday3" /></td>
                                        <td><input type="text" id="monday4" /></td>
                                        <td><input type="text" id="monday5" /></td>
                                    </tr>
                                    <br />
                                    <tr>
                                        <td>Tuesday:</td>
                                        <td><input type="text" id="tuesday1" /></td>
                                        <td><input type="text" id="tuesday2" /></td>
                                        <td><input type="text" id="tuesday3" /></td>
                                        <td><input type="text" id="tuesday4" /></td>
                                        <td><input type="text" id="tuesday5" /></td>
                                    </tr>
                                    <br />
                                    <tr>
                                        <td>Wednesday:</td>
                                        <td><input type="text" id="wednesday1" /></td>
                                        <td><input type="text" id="wednesday2" /></td>
                                        <td><input type="text" id="wednesday3" /></td>
                                        <td><input type="text" id="wednesday4" /></td>
                                        <td><input type="text" id="wednesday5" /></td>
                                    </tr>
                                    <br />
                                    <tr>
                                        <td>Thursday:</td>
                                        <td><input type="text" id="thursday1" /></td>
                                        <td><input type="text" id="thursday2" /></td>
                                        <td><input type="text" id="thursday3" /></td>
                                        <td><input type="text" id="thursday4" /></td>
                                        <td><input type="text" id="thursday5" /></td>
                                    </tr>
                                    <br />
                                    <tr>
                                        <td>Friday:</td>
                                        <td><input type="text" id="friday1" /></td>
                                        <td><input type="text" id="friday2" /></td>
                                        <td><input type="text" id="friday3" /></td>
                                        <td><input type="text" id="friday4" /></td>
                                        <td><input type="text" id="friday5" /></td>
                                    </tr>
                                    <br />
                                    <tr>
                                        <td>Saturday:</td>
                                        <td><input type="text" id="saturday1" /></td>
                                        <td><input type="text" id="saturday2" /></td>
                                        <td><input type="text" id="saturday3" /></td>
                                        <td><input type="text" id="saturday4" /></td>
                                        <td><input type="text" id="saturday5" /></td>
                                    </tr>
                                    <br />
                                    <tr>
                                        <td>Sunday:</td>
                                        <td><input type="text" id="sunday1" /></td>
                                        <td><input type="text" id="sunday2" /></td>
                                        <td><input type="text" id="sunday3" /></td>
                                        <td><input type="text" id="sunday4" /></td>
                                        <td><input type="text" id="sunday5" /></td>
                                    </tr>
                                </table>
                                <br />
                                <Button variant="outline-primary" onClick={handleCreate}>Upload</Button>{' '}
                            </Card.Body>
                        </Card>
                        </div>
                    }
                    {showCustomerList ?
                        (isLoading ? <Container className="my-5 mx-auto"><Spinner variant="primary" /></Container> : <CustomerList customerList={customerList} subscriptionList={subscriptionList} />) : null
                    }
                    <br />
                    <br />
                    <Navbar bg="primary" variant="light" className="justify-content-center align-items-center">
                        <h3>©Go Meals</h3>
                    </Navbar>
                </div>
            }
        </div>
    );
}