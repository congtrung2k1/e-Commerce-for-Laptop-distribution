import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';

const SignOut = () => {
    const navigate = useNavigate();
    const { cookies, removeCookie } = useCookies();

    useEffect(() => {
        removeCookie(cookies);
        navigate("/", { isAuthenticated: false });
        navigate(0);
    });
};

export default SignOut;
