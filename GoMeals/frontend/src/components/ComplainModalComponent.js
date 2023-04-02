import { React } from "react";
import "../styles/ComplainModal.css";
import { Button } from "react-bootstrap";
const ComplainModalComponent = (props) => {
  if (!props.show) {
    return null;
  }
  return (
    <div className="modal" onClick={props.onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <div className="modal-header">
          <h4 className="modal-title">
            Complain Id: {props.complain.complainId}
          </h4>
          <h4>Raised on : {props.complain.date}</h4>
        </div>
        <div className="modal-body m-2 text-start">
          <h5>Status: {props.complain.status}</h5>
          <h5>Meal Name: {props.delivery.deliveryMeal}</h5>
          <h5>Delivery Date: {props.delivery.deliveryDate}</h5>
          <h5>Your Comment: {props.complain.customerComment}</h5>
          {props.complain.supplierComment == "" ? (
            <h5>Supplier has not commented yet</h5>
          ) : (
            <h5>Suppliers Comment: {props.complain.supplierComment}</h5>
          )}
        </div>
        <div className="modal-footer">
          <Button variant="danger" onClick={props.onClose}>
            Close
          </Button>
        </div>
      </div>
    </div>
  );
};

export default ComplainModalComponent;
