import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';

import { postAuthentication } from "../../resources/authenticate";

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
