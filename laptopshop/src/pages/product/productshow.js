import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./product.css"

import { 
    getProductById,
    deleteProduct
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
    const [root, setRoot] = useState((data) => {
       return data; 
    });
    
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
    
    const updateProduct = async () => {
        navigate(`/product/update/${productId}`);
    };
    
    const handleRemove = async () => {
        deleteProduct(productId);
        alert("Successfully remove");
        navigate("/product");
    };
        
    useEffect(() => {
        if (userId === '1') setRoot(true);
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
                        <button className="show-product-pay-btn" onClick={addPendingOrder}>Add</button>
                        <p className="show-product-info-item"><font face = "Verdana" color="red">{product.price} $</font></p>
                        <div className="show-order-item-info">
                            <p className=".show-product-info-item" alt={product.name}>{product.name}</p>
                        </div>
                    </div>
                    <div className="show-product-div">
                        <b>Best Sellers Rank:</b> {data[0]}
                    </div>
                    <div className="show-product-div">
                        <b>Item Dimensions L x W x H:</b> {data[1]}
                    </div>
                    <div className="show-product-div">
                        <b>Customer Rating:</b> {data[2]}
                    </div>
                    <div className="edit-shipment-form-section">
                        <div>
                            <button className="show-shipment-pay-btn" onClick={updateProduct} style={{ display: root ? 'block' : 'none' }}>Update Product</button>
                        </div>
                        <div>
                            <button className="show-shipment-pay-btn" onClick={handleRemove} style={{ display: root ? 'block' : 'none' }}>Remove Product</button>
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

