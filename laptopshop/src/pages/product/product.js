import React, { useEffect, useState } from 'react'
import { useNavigate, Link } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./product.css"

import { 
    getProductForCategory, 
    getProductById, 
    getAllProduct 
} from '../../resources/product';

import {
    getUserPendingOrder, 
    createPendingOrder, 
    addToOrder
} from '../../resources/order';

const Product = () => {
    const navigate = useNavigate();
    const { cookies } = useCookies();
    const userId = cookies.userId;
        
    const [productList, setProductList] = useState([]);
    const [pendingOrder, setPendingOrder] = useState([]);

    const updateProduct = async () => {
        getAllProduct().then((data) => {
            if (data)
                setProductList(data);
        }).catch((error) => console.log(error.message));
    };

    const addPendingOrder = async (productId) => {
        getUserPendingOrder(userId, "pending").then((data) => {
            if (data.length !== 0)
                setPendingOrder(data[0]);
            else 
                createPendingOrder(userId).then((data) => {
                    if (data)
                        setPendingOrder(data);
                });
            if (pendingOrder.orderId !== undefined) {
                const form = {
                    product_id: productId,
                    quantity: 1
                };
                addToOrder(pendingOrder.orderId, form).then(() => {
                    alert("Successfully add item");
                });
            }
        }).catch((error) => console.log(error.message));
    };

    const showProduct = async (product_id) => {
        getProductById(product_id).then((data) => {
            navigate(`/product/${product_id}`);
        }).catch((error) => console.log(error.message));
    };
    
    const getCategory = async (category) => {
        getProductForCategory(category).then((data) => {
            if (data)
                setProductList(data);
        }).catch((error) => console.log(error.message));
    };
    
    useEffect(() => {
        updateProduct();
        getUserPendingOrder(userId, "pending").then((data) => {
            if (data.length !== 0)
                setPendingOrder(data[0]);
        });
    }, []);

    const showCategory = () => {
        const arr = [];
        arr.push(
                <div className="category">
                    <button className="show-product-pay-btn" onClick={() => getCategory("(Renewed)")}>RENEW</button>
                    <button className="show-product-pay-btn" onClick={() => getCategory("Acer")}>ACER</button>
                    <button className="show-product-pay-btn" onClick={() => getCategory("Apple")}>APPLE</button>
                    <button className="show-product-pay-btn" onClick={() => getCategory("Asus")}>ASUS</button>
                    <button className="show-product-pay-btn" onClick={() => getCategory("Honor")}>HONOR</button>
                    <button className="show-product-pay-btn" onClick={() => getCategory("HP")}>HP</button>
                    <button className="show-product-pay-btn" onClick={() => getCategory("Lenovo")}>LENOVO</button>
                    <button className="show-product-pay-btn" onClick={() => getCategory("MSI")}>MSI</button>
                </div>
        );
        return arr;
    };
        
    const showProducts = () => {
        const arr = [];
        for (let i in productList) {
            const item = productList[i];
            arr.push(                    
                <div className="show-product-item">
                    <div>
                        <img className="show-product-info-item" alt={item.name} src={window.location.origin+item.image}/>
                    </div>
                    <div className="show-product-item-info">
                        <p className="show-product-info-item" alt={item.name}>{item.name}</p>
                    </div>
                    <div>
                    <font face = "Verdana"><p className="show-product-info-item">{item.price} $</p></font>
                    </div>
                    <div>
                        <button className="show-product-pay-btn" onClick={() => addPendingOrder(item.productId)}>Add</button>
                        <button className="show-product-pay-btn" onClick={() => showProduct(item.productId)}>View</button>
                    </div>
                </div>
            );
        };
        return arr;
    };

    return (
        <div className="background">
            <div className="show-product-wrapper">
                <div className="show-product-list">
                    {showProducts()}
                </div>
                <div className="category">
                    {showCategory()}
                </div>
            </div>
        </div>
        
    );
};

export default Product;