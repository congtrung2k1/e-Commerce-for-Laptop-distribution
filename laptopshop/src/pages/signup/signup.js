import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

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
        event.preventDefault();

        var filterMail = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
        var filterPhone = /^0([0-9]{9})+$/;
        
        if (!filterMail.test(email)) {
            alert('Invalid Mail format');
        }
        else 
        if (!filterPhone.test(phone)) {
            alert('Invalid phone format');
        }
        else
        if(name === '' || name === '' || address === '' || country === '') {
            alert("Something missing");
        }
        else {
            const form = {
                name:  name,
                password:  password,
                phone:  phone,
                email:  email,
                address:  address,
                country:  country
            };
            axios.post('http://localhost:8080/signup', form).then(response => {
                if (response.data.errorMessage === undefined) {
                    alert("Successfully sign up");
                    navigate("/signin");
                }
                else {
                    alert(response.data.errorMessage);
                }
            }).catch((error) => console.log(error.message));
        }
    };

    return (
        <div className="sign-in-form-wrapper">
            <form autoComplete='off' className="sign-in-form" method="post" onSubmit={handleSignup}>
                <div className="sign-in-form-description">
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
                <button className="sign-in-btn">Submit</button> 
            </form>
            
            {errorMessage === "" ? "" : 
                <p>{errorMessage}</p>
            }
        </div>
    );
};

export default SignUp;