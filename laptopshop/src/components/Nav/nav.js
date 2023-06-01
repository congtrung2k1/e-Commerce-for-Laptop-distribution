import React, { useContext } from "react";
import { SessionContext } from "../../hooks/session-context/session-context";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';

import "./nav.css";
import logo from "../../assets/logo.jpg"
import { routes } from "../../routes/routes";

const  Nav = () => {
  const { isAuthenticated } = useContext(SessionContext) || {};
  const { cookies } = useCookies();

  return (
    <nav>
      <div className="logo-div">
        <img src={logo} className="logo-img" alt="logo" />
        <p>Laptop Shop</p>
      </div>
      
      <div className="sections">
        <Link to="/product">Product</Link>
        <Link to="/order">Your Orders</Link>
        <Link to="/shipment">Your Shipments</Link>    
        {   
            (isAuthenticated && (cookies.userId === '1')) ?
                <Link to={"/productadd"}>Import Product</Link>
            :
                <div></div>
        }
      </div>
            
    {
        isAuthenticated ? 
        <div>
            <div className="sign-in">
                <Link to={routes.user}>Your Profife</Link>
            </div>
            <div className="sign-in">
                <Link to={routes.logout}>Sign Out</Link>
            </div>
        </div> 
        :
        <div className="sign-in">
          <Link to={routes.loginUrl}>Sign in</Link>
        </div>
    }
    </nav>
  );
};

export default Nav;
