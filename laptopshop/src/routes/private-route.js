import React from 'react';
import { useLocation, useNavigate, Navigate } from 'react-router-dom';
import { useEffect, useContext } from 'react';
import { SessionContext } from './../hooks/session-context/session-context';
import { routes } from './routes';

const PrivateRoute = ({ children }) => {
    const { isAuthenticated } = useContext(SessionContext) || {};

    const { location } = useLocation();

    const navigate = useNavigate();

    useEffect(() => {
        if(isAuthenticated) return;
        
        return navigate({ path: routes.loginUrl });
    }, [navigate, location, isAuthenticated]);  
    return isAuthenticated ? children : <Navigate to={routes.loginUrl} replace={true}/>;
};

export { PrivateRoute };