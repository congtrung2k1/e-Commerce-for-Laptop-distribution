import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./user.css"
import {
    getUserByUserId,
    updateUser
} from "../../resources/user";

export default function User() {
    const navigate = useNavigate();    
    const { cookies } = useCookies();
    const userId = cookies.userId;
    
    const [user, setUser] = useState();
    const [name, setName] = useState();
    const [phone, setPhone] = useState();
    const [email, setEmail] = useState(null);
    const [address, setAddress] = useState(null);
    const [country, setCountry] = useState(null);
    
    const [password, setPassword] = useState();
    const [newPassword, setNewPassword] = useState();
    const [newPasswordConfirm, setNewPasswordConfirm] = useState();
    
    const getUser = async () => {
        getUserByUserId(userId).then((response) => {
            if (response.data) {
                setUser(response.data);
                setName(response.data.name);
                setPhone(response.data.phone);
                setEmail(response.data.email);
                setAddress(response.data.address);
                setCountry(response.data.country);
                setPassword("");
            }
        }).catch(error => console.log(error.message));
    }

    useEffect(() => {
        getUser();
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = {
            name: user.name,
            phone: user.phone,
            password: user.password,
            email: user.email,
            address: user.address,
            country: user.country
        };
        if (name !== "" && name !== user.name) {
            form.name = name;
        }
        if (email !== "" && email !== user.email) {
            form.email = email;
        }
        if (address !== "" && address !== user.address) {
            form.address = address;
        }
        if (country !== "" && country !== user.country) {
            form.country = country;
        }
        
        if (password !== "")
            if (password === user.password) {
                if (newPassword !== "" && newPasswordConfirm !== "") {
                    if (newPassword === newPasswordConfirm) {
                        console.log(password);
                        console.log(newPassword);
                        form.password = newPassword;
                    }
                    else{
                        alert('Failed! re-password is wrong');
                        return;
                    }
                }
                else {
                    alert('Failed! Please enter the new password!');
                    return;
                }
            }
            else {
                alert('Failed! Wrong password!');
                return;
            }
        
        updateUser(userId, form).then((response) => {
            if (response.data.errorMessage === undefined) {
                alert("Successfully change profile");
                navigate(0);
                navigate("/user");
            }
            else {
                alert(response.data.errorMessage);
            }
        }).catch((error) => console.log(error.message));
    }

    return (
        <div className="edit-info-body">
            <div className="edit-info-form-wrapper">
                <form className="edit-info-form" onSubmit={handleSubmit}>
                    <h3 className="edit-info-form-description">Your info</h3>
                    <table className="edit-info-table">
                      <tbody>
                        <div className="edit-info-form-section">
                          <tr><td className="edit-info-td"><label for="phone">Phone</label></td></tr>
                          <tr><td className="edit-info-td"><input type='text' className="edit-info-name" name="phone" value={phone} readOnly={true}/></td></tr>
                        </div>
                        <div className="edit-info-form-section">
                            <tr><td className="edit-info-td"><label for="name">Name</label></td></tr>
                            <tr><td className="edit-info-td"><input type='text' className="edit-info-item" name="name" value={name} readOnly={false} onChange={(e) => setName(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-info-form-section">
                            <tr><td className="edit-info-td"><label for="email">Email</label></td></tr>
                            <tr><td className="edit-info-td"><input type='text' className="edit-info-item" name="email" value={email} readOnly={false} onChange={(e) => setEmail(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-info-form-section">
                            <tr><td className="edit-info-td"><label for="address">Address</label></td></tr>
                            <tr><td className="edit-info-td"><input type='text' className="edit-info-item" name="address" value={address} readOnly={false} onChange={(e) => setAddress(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-info-form-section">
                            <tr><td className="edit-info-td"><label for="country">Country</label></td></tr>
                            <tr><td className="edit-info-td"><input type='text' className="edit-info-item" name="country" value={country} readOnly={false} onChange={(e) => setCountry(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-info-form-section">
                            <tr><td className="edit-info-td"><label for="password">Old Password</label></td></tr>
                            <tr><td className="edit-info-td"><input type='password' className="edit-info-item" name="password" value={password} readOnly={false} onChange={(e) => setPassword(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-info-form-section">
                            <tr><td className="edit-info-td"><label for="newPassword">New Password</label></td></tr>
                            <tr><td className="edit-info-td"><input type='password' className="edit-info-item" name="newPassword" value={newPassword} readOnly={false} onChange={(e) => setNewPassword(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-info-form-section">
                            <tr><td className="edit-info-td"><label for="newPasswordConfirm">New Password Confirm</label></td></tr>
                            <tr><td className="edit-info-td"><input type='password' className="edit-info-item" name="newPasswordConfirm" value={newPasswordConfirm} readOnly={false} onChange={(e) => setNewPasswordConfirm(e.target.value)}/></td></tr>
                        </div>
                      </tbody>
                    </table>
                    <input className="edit-info-submit-btn" type="submit" value="Submit"/>
                </form>
            </div>
      </div>
    );
}