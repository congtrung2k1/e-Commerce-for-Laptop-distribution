import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import logo from "../../assets/login_bg.jpg";

import "./signin.css";

function SignIn () {
    const navigate = useNavigate();

    const { setCookie } = useCookies();

    const [phone, setPhone] = useState(null);
    const [password, setPassword] = useState(null);
    const [userId, setUserId] = useState(null);
    
    const handleSignin = (event) => {
        event.preventDefault();
        const form = {
            username: phone,
            password: password
        };

        axios.post('http://localhost:8080/authenticate', form).then(response => {
            if(response.data.token) {
                setUserId(response.data.userId);
                setCookie("jwt", response.data.token);
                setCookie("userId", response.data.userId);
                setCookie("phone", response.data.phone);
                navigate("/user", {isAuthenticated: true});
            }
            else {
                alert("Internal server error!");
            }
        }).catch(error => {
            console.log(error.message);
            alert("Login FAILED! Please check again!");
        });
    };

    return (
        <div className="sign-in-form-wrapper">
            <form method="post" className="sign-in-form" onSubmit={handleSignin}>
                <div className="sign-in-form-description">
                    <p>Laptop Shop Sign In</p>
                </div>
                
                <hr></hr>
                
                <table>
                    <tbody>
                        <div className="sign-in-credentials">
                            <tr><td><label className="sign-in-label" htmlFor='phone'>Phone</label></td></tr>
                            <tr><td><input type='text' name="phone" id="phone" onChange={e => setPhone(e.target.value)} required/></td></tr>
                        </div>
                        <div className="sign-in-credentials">
                            <tr><label className="sign-in-label" htmlFor='password'>Password</label></tr>
                            <tr><input type='password' name="password" id="password" onChange={e => setPassword(e.target.value)} required/></tr>
                        </div>
                    </tbody>
                </table>
                <button className="sign-in-btn">Sign In</button>
                
                <hr></hr>
                
                <p>
                    <Link to="/signup">Register</Link>
                </p> 
            </form>
        </div>
    );
};

export default SignIn;