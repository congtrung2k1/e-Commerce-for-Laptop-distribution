import React from 'react';

import SignIn from '../pages/signin/signin';
import SignUp from '../pages/signup/signup';
import SignOut from '../pages/signin/signout';
import Product from '../pages/product/product';
import ProductCreate from '../pages/product/productadd';
import ProductShow from '../pages/product/productshow';
import ProductUpdate from '../pages/product/productupdate';
import User from '../pages/user/user';
import Order from '../pages/order/order';
import OrderAll from '../pages/order/orderall';
import Shipment from '../pages/shipment/shipment';
import ShipmentAll from '../pages/shipment/shipmentall';

import { BrowserRouter, Routes, Route } from "react-router-dom";

import { PrivateRoute } from './private-route';
import Nav from '../components/Nav/nav';

import { routes } from './routes';

const MainRoutes = () => {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" exact element={<SignIn />} />
        <Route path={routes.loginUrl} exact element={<SignIn />} />
        <Route path={routes.registerUrl} exact element={<SignUp />} />
        <Route path={routes.logout} exact element={<SignOut />} />

        <Route path="/product" exact element={
          <PrivateRoute>
            <Product />
          </PrivateRoute>
        } />
        <Route path="/product/:productId" element={
            <PrivateRoute>
                <ProductShow />
            </PrivateRoute>
        } />
        <Route path="/productadd" element={
            <PrivateRoute>
                <ProductCreate />
            </PrivateRoute>
        } />
        <Route path="/product/update/:productId" element={
            <PrivateRoute>
                <ProductUpdate />
            </PrivateRoute>
        } />

        <Route path="/user" element={<User />} />
        <Route path="/order" element={<OrderAll />} />
        <Route path="/order/:orderId" element={<Order />} />
        <Route path="/shipment" element={<ShipmentAll />} />
        <Route path="/shipment/:shipmentId" element={<Shipment />} />
      </Routes>
    </BrowserRouter>
  );
};

export default MainRoutes;
