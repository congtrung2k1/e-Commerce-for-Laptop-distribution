import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from "../../hooks/use-cookie/use-cookie";

import "./signup.css";

function SignUp () {
    const navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState("");

    const [name, setName] = useState(null);
    const [phone, setPhone] = useState(null);
    const [password, setPassword] = useState(null);
    const [email, setEmail] = useState(null);
    const [address, setAddress] = useState(null);
    const [country, setCountry] = useState(null);

    const handleSignup = async (event) => {
        const form = {
            name:  name,
            password:  password,
            phone:  phone,
            email:  email,
            address:  address,
            country:  country
        };
        event.preventDefault();

        axios.post('http://localhost:8080/signup', form).then(response => {
            if (response.status === 200) {
                navigate("/signin");
            }
        }).catch((error) => console.log(error.message));

    };

    return (
        <div className="sign-up-form-wrapper">
            <form autoComplete='off' className="sign-up-form" method="post" onSubmit={handleSignup}>
                <div className="sign-up-form-description">
                    <p>Laptop Shop Sign Up</p>
                    <hr></hr>
                </div>
                <table>
                        <div className="sign-up-form-section">
                            <tr>
                                <td><label className="sign-up-label" htmlFor='phone'>Name</label></td>
                            </tr>
                            <tr>
                                <td><input type='text' className="sign-up-name-inputs" name="name" id="name" onChange={e => setName(e.target.value)} required /></td>
                            </tr>
                        </div>
                        <div className="sign-up-form-section">
                            <tr>
                                <td><label className="sign-up-label" htmlFor='phone'>Phone</label></td>
                            </tr>
                            <tr>
                                <td><input type='text' name="phone" id="phone" onChange={e => setPhone(e.target.value)} required /></td>
                            </tr>
                        </div>
                        <div className="sign-up-form-section">
                            <tr>
                                <td><label className="sign-up-label" htmlFor='password'>Password</label></td>
                            </tr>
                            <tr>
                                <td><input type='password' name="password" id="password" onChange={e => setPassword(e.target.value)} required /></td>
                            </tr>
                        </div>
                        <div className="sign-up-form-section">
                            <tr>
                                <td><label className="sign-up-label" htmlFor='email'>Email</label></td>
                            </tr>
                            <tr>
                                <td><input type='text' className="sign-up-name-inputs" name="email" id="email" onChange={e => setEmail(e.target.value)} required /></td>
                            </tr>
                        </div>
                        <div className="sign-up-form-section">
                            <tr>
                                <td><label className="sign-up-label" htmlFor='address'>Address</label></td>
                            </tr>
                            <tr>
                                <td><input type='text' className="sign-up-name-inputs" name="address" id="address" onChange={e => setAddress(e.target.value)} required /></td>
                            </tr>
                        </div>
                        <div className="sign-up-form-section">
                            <tr>
                                <td><label className="sign-up-label" htmlFor='country'>Country</label></td>
                            </tr>
                            <tr>
                                <td><input type='text' className="sign-up-name-inputs" name="country" id="country" onChange={e => setCountry(e.target.value)} required /></td>
                            </tr>
                        </div>
                </table>
                <button className="sign-up-btn">Submit</button> 
            </form>
            
            {errorMessage === "" ? "" : 
                <p>{errorMessage}</p>
            }
        </div>
    );
};

export default SignUp;