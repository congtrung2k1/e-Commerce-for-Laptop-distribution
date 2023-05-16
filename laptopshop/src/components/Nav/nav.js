import React, { useContext } from "react";
import { SessionContext } from "../../hooks/session-context/session-context";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import "./nav.css";
import logo from "../../assets/logo.jpg"

function Nav() {
  const { isAuthenticated } = useContext(SessionContext) || {};

  return (
    <nav>
        <div className="logo-div">
            <img src={logo} className="logo-img" alt="logo" />
            <p>Laptop Shop</p>
        </div>
      
        <div className="sections">
            <Link to="/product">Home</Link>
        </div>
      
    {
        isAuthenticated ? 
        <div className="sign-in">
            <Link to="/order">Your Orders</Link>
            <Link to="/shipment">Your Shipments</Link>    
            <Link to="/user">Your Profife</Link>
        </div> 
        :
        <div className="sign-in">
            <Link to="/signin">Sign in</Link>
        </div>
    }
    </nav>
  );
}

export default Nav;
