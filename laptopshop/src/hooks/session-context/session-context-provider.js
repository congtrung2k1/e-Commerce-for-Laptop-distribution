import React from 'react'
import useAuth from '../../hooks/use-auth/use-auth';
import { useCookies } from './../use-cookie/use-cookie';
import { SessionContext } from './session-context';
import { routes } from '../../routes/routes'

const COOKIE = ["jwt"]

export const SessionContextProvider = ({ children }) => {
    const { cookies, removeCookies } = useCookies()

    const removeAuthCookies = () => {
        removeCookies(...Object.value(COOKIE))
    }

    const userData = {
        userId: cookies['userId'] || '',
        phoneNumber: cookies['phone'] || ''
    }
    const contextData = {
        loginUrl: routes.loginUrl,
        data: userData,
        isAuthenticated: cookies['jwt'] != null,
        removeAuthCookies
    }
    return <SessionContext.Provider value={contextData}>{children}</SessionContext.Provider>
}