import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';

import "./signin.css";
import { postAuthentication } from "../../resources/authenticate";

function SignIn () {
    const navigate = useNavigate();

    const { setCookie } = useCookies();

    const [phone, setPhone] = useState(null);
    const [password, setPassword] = useState(null);
    const [userId, setUserId] = useState(null);
    
    const handleSignin = async (event) => {
        event.preventDefault();
        try {
            const data = await postAuthentication(phone, password);

            if (data.token) {
                setUserId(data.userId);
                setCookie("jwt", data.token);
                setCookie("userId", data.userId);
                setCookie("phone", data.phone);
                navigate("/product", { isAuthenticated: true });
                navigate(0);
            }
        }
        catch(e) {
            console.log(e);
            alert("Something wrong, Check your account again!");    
        }
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