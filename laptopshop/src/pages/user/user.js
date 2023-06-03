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
                name: user.name,
                phone: user.phone,
                password: user.password,
                email: user.email,
                address: user.address,
                country: user.country
            };
            if (name !== user.name) {
                form.name = name;
            }
            if (email !== user.email) {
                form.email = email;
            }
            if (address !== user.address) {
                form.address = address;
            }
            if (country !== user.country) {
                form.country = country;
            }

            if (password !== "")
                if (password === user.password) {
                    if (newPassword !== "" && newPasswordConfirm !== "") {
                        if (newPassword === newPasswordConfirm) {
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
    }

    return (
        <div className="edit-user-body">
            <div className="edit-user-form-wrapper">
                <form className="edit-user-form" onSubmit={handleSubmit}>
                    <h3 className="edit-user-form-description">Your info</h3>
                    <table className="edit-user-table">
                      <tbody>
                        <div className="edit-user-form-section">
                          <tr><td className="edit-user-td"><label for="phone">Phone</label></td></tr>
                          <tr><td className="edit-user-td"><input type='text' className="edit-user-name" name="phone" value={phone} readOnly={true}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="name">Name</label></td></tr>
                            <tr><td className="edit-user-td"><input type='text' className="edit-user-item" name="name" value={name} readOnly={false} onChange={(e) => setName(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="email">Email</label></td></tr>
                            <tr><td className="edit-user-td"><input type='text' className="edit-user-item" name="email" value={email} readOnly={false} onChange={(e) => setEmail(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="address">Address</label></td></tr>
                            <tr><td className="edit-user-td"><input type='text' className="edit-user-item" name="address" value={address} readOnly={false} onChange={(e) => setAddress(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="country">Country</label></td></tr>
                            <tr><td className="edit-user-td"><input type='text' className="edit-user-item" name="country" value={country} readOnly={false} onChange={(e) => setCountry(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="password">Old Password</label></td></tr>
                            <tr><td className="edit-user-td"><input type='password' className="edit-user-item" name="password" value={password} readOnly={false} onChange={(e) => setPassword(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="newPassword">New Password</label></td></tr>
                            <tr><td className="edit-user-td"><input type='password' className="edit-user-item" name="newPassword" value={newPassword} readOnly={false} onChange={(e) => setNewPassword(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="newPasswordConfirm">New Password Confirm</label></td></tr>
                            <tr><td className="edit-user-td"><input type='password' className="edit-user-item" name="newPasswordConfirm" value={newPasswordConfirm} readOnly={false} onChange={(e) => setNewPasswordConfirm(e.target.value)}/></td></tr>
                        </div>
                      </tbody>
                    </table>
                    <input className="edit-user-submit-btn" type="submit" value="Submit"/>
                </form>
            </div>
      </div>
    );
}