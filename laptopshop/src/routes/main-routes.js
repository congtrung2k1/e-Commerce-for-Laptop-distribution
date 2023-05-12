import React from 'react';

import SignIn from '../pages/signin/signin';
import SignUp from '../pages/signup/signup';
import Product from '../pages/product/product';
import User from '../pages/user/user';
import Order from '../pages/order/order';
import Shipment from '../pages/shipment/shipment';
import Edit from '../pages/edit/edit';

import { BrowserRouter, Routes, Route } from "react-router-dom";

import PrivateRoute from './private-route';
import Nav from '../components/Nav/nav';

function MainRoutes() {
  return (
    <BrowserRouter>
      <Nav />
      <div>
        <Routes>
            <Route path="/signin" exact element={<SignIn />} />
            <Route path="/signup" exact element={<SignUp />} />
            <Route path="/" exact element={<PrivateRoute Component={Product} />} />
            <Route path="/product" element={<Product />} />
            <Route path="/user" element={<User />} />
            <Route path="/order" element={<Order />} />
            <Route path="/shipment" element={<Shipment />} />
            <Route path="/edit" element={<Edit />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default MainRoutes
