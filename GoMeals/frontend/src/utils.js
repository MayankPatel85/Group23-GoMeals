import axios from "axios";

export const addCustomerNotification = (customerNotification) => {
    console.log(customerNotification);
    axios
        .post("http://localhost:8080/customer-notification/create", customerNotification)
}

export const addSupplierNotification = (supplierNotification) => {
    axios
        .post("http://localhost:8080/supplier-notification/create", supplierNotification)
}

// export const API_HEADER = "http://csci5308vm23.research.cs.dal.ca:8080/";
export const API_HEADER = "http://localhost:8080/"