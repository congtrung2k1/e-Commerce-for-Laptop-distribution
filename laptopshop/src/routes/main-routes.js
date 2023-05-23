import React from 'react';

import SignIn from '../pages/signin/signin';
import SignUp from '../pages/signup/signup';
import Product from '../pages/product/product';
import User from '../pages/user/user';
import Order from '../pages/order/order';
import Shipment from '../pages/shipment/shipment';
import Edit from '../pages/edit/edit';

import { BrowserRouter, Routes, Route } from "react-router-dom";

import { PrivateRoute } from './private-route';
import Nav from '../components/Nav/nav';

import { routes } from './routes';

const MainRoutes = () => {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path={routes.loginUrl} exact element={<SignIn />} />
        <Route path={routes.registerUrl} exact element={<SignUp />} />

        <Route path="/product" exact element={
          <PrivateRoute>
            <Product />
          </PrivateRoute>
        } />
        <Route path="/user" element={<User />} />
        <Route path="/order" element={<Order />} />
        <Route path="/order/:orderId" element={<Order />} />
        <Route path="/shipment" element={<Shipment />} />
        <Route path="/edit" element={<Edit />} />
      </Routes>
    </BrowserRouter>
  );
};

export default MainRoutes;
