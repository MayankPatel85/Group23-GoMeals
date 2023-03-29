import React, {useEffect, useState} from "react";
import {Button, Card, Container, Nav, Navbar} from "react-bootstrap";
import chef from "../resources/chef.jpg";
import {useLocation} from "react-router-dom";
import axios from "axios";

function Profile(props){
    const location=useLocation();
    const [supDetails,setSupDetails]=useState("");
    // const currentDate=new date();
    const handleClick=()=>{
        const notify={
            "message": `${location.state.cname} requested subscription`,
            "eventType": "review",
            "supplierId": location.state.id
        }
        axios.post("http://localhost:8080/supplier-notification/create",notify)
            .then(alert("Notification sent to the supplier"))
        // axios.post("http://localhost:8080/subscription/add")
    }
    useEffect(()=>{
        axios.get(`http://localhost:8080/supplier/get/${location.state.id}`)
            .then((response) => {
                setSupDetails(response.data)
            })
    });
    return(
        <div>
            <Navbar bg="primary" variant="light">
                <Container>
                    <Navbar.Brand href="#home">Go Meals</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="#home">Home</Nav.Link>
                        <Nav.Link href="#features">Profile</Nav.Link>
                        <Nav.Link href="#pricing">Customers</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <h3>Supplier details</h3>
            <h4>customer id {console.log(location.state.cname)}</h4>
            <Card style={{ width: '18rem' }}>
                <Card.Img variant="top" src={chef}/>
                <Card.Body>
                    <Card.Title></Card.Title>
                    <Card.Text>
                       Name : {supDetails.supName}<br/>
                       Address :  {supDetails.supAddress}<br/>
                       Contact Number : {supDetails.supContactNumber}<br/>
                       Email Id : {supDetails.supEmail}<br/>
                       Licence number : {supDetails.govtIssuedId}<br/>
                       Paypal link : {supDetails.paypalLink}<br/>
                    </Card.Text>
                    <Button variant="primary" onClick={handleClick}>Notify</Button>
                </Card.Body>
            </Card>
        </div>
    );
}
export default Profile;