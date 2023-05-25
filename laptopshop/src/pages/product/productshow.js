import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./product.css"

import { 
    getProductById
} from '../../resources/product';

import {
    getUserPendingOrder,
    createPendingOrder, 
    addToOrder
} from '../../resources/order';

const ProductShow = () => {
    const navigate = useNavigate();    
    const { cookies } = useCookies();
    const userId = cookies.userId;
    const { productId } = useParams();

    const [product, setProduct] = useState([]);
    const [pendingOrder, setPendingOrder] = useState([]);
    
    const getProduct = async () => {
        getProductById(productId).then((data) => {
            if (data !== undefined) {
                setProduct(data);
            }
        });
    };
    
    const addPendingOrder = async () => {
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
        
    useEffect(() => {
        if (product === undefined || product.length === 0) getProduct();
    }, [product]);
    
    const handleShow = () => {
        if (product === undefined || product.length === 0) return (<div></div>);
        let data = product.description.split(" | ");
        const arr = [];      
        arr.push(
                <div className="edit-shipment-form">
                    <div className="edit-order-form-section">
                        <img className="show-product-image-show" alt={product.name} src={window.location.origin+product.image}/>
                        <button className="show-product-pay-btn" onClick={() => addPendingOrder(product.productId)}>Add</button>
                        <p className="show-product-info-item"><font face = "Verdana" color="red">{product.price} $</font></p>
                        <div>
                            <div className="show-order-item-info">
                                <p className="show-order-info-item" alt={product.name}>{product.name}</p>
                            </div>
                        </div>
                    </div>
                    
                    <div className="edit-shipment-form-section">
                        <div>
                            Best Sellers Rank: {data[0]}
                        </div>
                    </div>
                    <div className="edit-shipment-form-section">
                        <div>
                            Item Dimensions L x W x H: {data[1]}
                        </div>
                    </div>
                    <div className="edit-shipment-form-section">
                        <div>
                            Customer Rating: {data[2]}
                        </div>
                    </div>
            </div>
        );
        return arr;
    };
    
    return (
        <div className="edit-shipment-body">
            <div className="edit-user-form-wrapper">
                {handleShow()}
            </div>
      </div>
    );
};

export default ProductShow;

