import React, { useEffect, useState } from "react";
import axios from "axios";
import { Spinner, Modal, Form, Button } from "react-bootstrap";
import { Cookies } from 'react-cookie';
import NavbarComponent from "../components/NavbarComponent";

function CustomerProfile() {
    const [customer, setCustomer] = useState({});
    const [isLoading, setIsLoading] = useState(false);
    const [editProfile, setEditProfile] = useState(false);
    const [editedCustomerDetail, setEditedCustomerDetail] = useState({
        email: "",
        contactNumber: "",
        address: ""
    });
    const cookies = new Cookies();
    const loggedInUser = cookies.get('loggedInUser');

    useEffect(() => {
        setIsLoading(true);
        axios
            .get(`http://localhost:8080/customer/get/${loggedInUser.cust_id}`)
            .then((response) => {
                response.data.subscriptions.forEach((subscription) => {
                    response.data.suppliers.forEach((supplier) => {
                        if (supplier.supId === subscription.supplierId) {
                            subscription.supplierName = supplier.supName;
                            subscription.supplierContactNo = supplier.supContactNumber;
                        }
                    });
                });
                console.log("res  " + response.data.subscriptions);
                setCustomer(response.data);
            })
            .catch((e) => {
                alert("Error getting data" + e)
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, [loggedInUser.cust_id]);

    useEffect(() => {
        axios
        .put("http://localhost:8080/customer/update", customer)
        .catch((e) => {
            alert("Error getting data" + e)
        })
        .finally(() => {
            setIsLoading(false);
            setEditProfile(false);
        });
    }, [customer]);
    

    console.log("cccc " + customer.cust_fname);

    const handleEditProfile = () => {
        setEditProfile(true);
        setEditedCustomerDetail({
            "email": customer.cust_email,
            "contactNumber": customer.cust_contact_number,
            "address": customer.cust_address
        });
    }

    const handleEditingProfile = (event) => {
        const {name, value} = event.target;
        console.log("name " + name + value);
        setEditedCustomerDetail((prevValue) => ({
            ...prevValue,
            [name]: value,
        }));
    }
        console.log("EDITED " + JSON.stringify(editedCustomerDetail));

    const updateCustomerProfile = () => {
        if(editedCustomerDetail.email === "" || editedCustomerDetail.contactNumber === "" || editedCustomerDetail.address === "") {
            alert("Fields should not be empty");
            return;
        }
        setIsLoading(true);
        console.log("before setting",JSON.stringify(editedCustomerDetail))
        setCustomer((prevValue) => ({
            ...prevValue,
            cust_email: editedCustomerDetail.email,
            cust_contact_number: editedCustomerDetail.contactNumber,
            cust_address: editedCustomerDetail.address,
        }));
        // axios
        //     .put("http://localhost:8080/customer/update", customer)
        //     .catch((e) => {
        //         alert("Error getting data" + e)
        //     })
        //     .finally(() => {
        //         setIsLoading(false);
        //         setEditProfile(false);
        //     });
    }


    return (
        <div>
            <NavbarComponent />
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
                                name="email"
                                value={editedCustomerDetail.email}
                                onChange={handleEditingProfile}
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="contactNo">
                            <Form.Label>Contact Number</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Your contact number"
                                name="contactNumber"
                                value={editedCustomerDetail.contactNumber}
                                onChange={handleEditingProfile}
                            />
                        </Form.Group>
                        <Form.Group
                            className="mb-3"
                            controlId="address"
                        >
                            <Form.Label>Address</Form.Label>
                            <Form.Control as="textarea" rows={2} name="address" value={editedCustomerDetail.address}  onChange={handleEditingProfile}/>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setEditProfile(false)}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={updateCustomerProfile}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
            {isLoading ? <Spinner /> :
                (
                    // <div className="customer-profile-page">
                    <div className="container customer-profile my-4">
                        <div className="col-lg-12">
                            <div className="d-flex justify-content-between align-items-center">
                                <div>
                                    <h2 className="d-inline">Hi, </h2>
                                    <h2 className="d-inline">{customer.cust_fname + " " + customer.cust_lname}</h2>
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
                                <h4 className="d-inline">{customer.cust_email}</h4>
                            </div>
                        </div>
                        <div className="row mb-2 profile-item">
                            <div className="col-lg-12">
                                <h4 className="d-inline">Contact number</h4>
                                <hr></hr>
                            </div>
                            <div className="col-lg-12">
                                <h4 className="d-inline">{customer.cust_contact_number}</h4>
                            </div>
                        </div>
                        <div className="row mb-2 profile-item">
                            <div className="col-lg-12">
                                <h4 className="d-inline">Address</h4>
                                <hr></hr>
                            </div>
                            <div className="col-lg-12">
                                <h4 className="d-inline">{customer.cust_address}</h4>
                            </div>
                        </div>
                        <div className="row mb-2 profile-item">
                            <div className="col-lg-12">
                                <h4 className="d-inline">Subscription</h4>
                                <hr></hr>
                            </div>
                            <div className="col-lg-8">
                                {customer.subscriptions?.map((subscription, index) => {
                                    return (
                                        <div key={subscription.sub_id}>
                                            <div className="row mb-2 profile-item">
                                                <div className="row">
                                                    <div className="col-lg-4 col-sm-6">
                                                        {/* <h5>Subscription {index + 1}</h5> */}
                                                        <h5 className="d-inline">Supplier's name:</h5>
                                                    </div>
                                                    <div className="col-lg-4 col-sm-6">
                                                        <h5 className="d-inline">{subscription.supplierName}</h5>
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-lg-4 col-sm-6">
                                                        <h5 className="d-inline">Subscription date:</h5>
                                                    </div>
                                                    <div className="col-lg-4 col-sm-6">
                                                        <h5 className="d-inline">{subscription.sub_date}</h5>
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-lg-4 col-sm-6">
                                                        <h5 className="d-inline">Meals remaining:</h5>
                                                    </div>
                                                    <div className="col-lg-4 col-sm-6">
                                                        <h5 className="d-inline">{subscription.meals_remaining}</h5>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    );
                                })}
                            </div>
                        </div>
                    </div>
                    // </div>
                )}
        </div>
    );
}

export default CustomerProfile;