import React from "react";
import "../styles/FooterComponent.css";
class FooterComponent extends React.Component {
  render() {
    return (
      <footer className="footer">
        <div className="footer-content">
          <h3>Contact Us</h3>
          <p>123 Main St</p>
          <p>Anytown, USA 12345</p>
          <p>Phone: 555-555-5555</p>
          <p>Email: info@company.com</p>
        </div>
        <div className="footer-content">
          <h3>Follow Us</h3>
          <ul className="social-media">
            <li>
              <a href="#">
                <i className="fab fa-facebook"></i>
              </a>
            </li>
            <li>
              <a href="#">
                <i className="fab fa-twitter"></i>
              </a>
            </li>
            <li>
              <a href="#">
                <i className="fab fa-instagram"></i>
              </a>
            </li>
          </ul>
        </div>
      </footer>
    );
  }
}

export default FooterComponent;
