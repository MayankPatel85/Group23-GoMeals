import React, { useEffect, useState } from "react";
import axios from "axios";
import { Spinner } from "react-bootstrap";
import { Cookies } from 'react-cookie';
import NavbarComponent from "../components/NavbarComponent";

function CustomerProfile() {
    const [customer, setCustomer] = useState({});
    const [isLoading, setIsLoading] = useState(false);
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

    console.log("cccc " + customer.cust_fname);


    return (
        <div>
            <NavbarComponent />
            {isLoading ? <Spinner /> :
                (
                    // <div className="customer-profile-page">
                    <div className="container customer-profile my-4">
                        <div className="row mb-2">
                            <div className="col-lg-12">
                                <h2 className="d-inline">Hi, </h2>
                                <h2 className="d-inline">{customer.cust_fname + " " + customer.cust_lname}</h2>
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