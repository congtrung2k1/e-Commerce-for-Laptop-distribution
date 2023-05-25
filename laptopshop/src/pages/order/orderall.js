import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./order.css"
import {
    getAllRootOrder,
    getAllUserOrder
} from "../../resources/order";

const OrderAll = () => {
    const navigate = useNavigate();    
    const { cookies } = useCookies();
    const userId = cookies.userId;

    const [orderList, setOrderList] = useState([]);
    const [root, setRoot] = useState((data) => {
        return data;
    });
        
    const getOrderRoot = async () => {
        getAllRootOrder().then((data) => {
            console.log(data);
            if (data) 
                setOrderList(data);
        });
    };
    
    const getOrderUser = async () => {
        getAllUserOrder(userId).then((data) => {
            console.log(data);
            if (data) 
                setOrderList(data);
        });
    };
    
    useEffect(() => {
        if (userId !== undefined)
            if (userId === '1'){
                setRoot(true);
                getOrderRoot();
            } else {
                setRoot(false);
                getOrderUser(userId);
            }
    }, []);
    
    const noneOrderId = () => {
        console.log(root, orderList);
        if (root === undefined || orderList === undefined) 
            return (<div></div>);
        
        const arr = [];
        
        arr.push(
                <div className="edit-shipment-form-section">
                    <div className="edit-shipment-td">
                        <h1>Current Order:</h1>
                    </div>
                </div>            
        );
        for (let i in orderList) {
            const item = orderList[i];
            if (item.orderStatus === "pending") {
                arr.push(
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            <a href={`/order/${item.orderId}`}>Order Number {item.orderId}</a>
                        </div>
                    </div> 
                );
                break;
            }
        }
        
        arr.push(
                <div className="edit-shipment-form-section">
                    <div className="edit-shipment-td">
                        <h1>Submitted:</h1>
                    </div>
                </div>            
        );
        for (let i in orderList) {
            const item = orderList[i];
            if (item.orderStatus === "submit")
                arr.push(
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            <a href={`/order/${item.orderId}`}>Order Number {item.orderId}</a>
                        </div>
                    </div> 
                );
        }
        
        arr.push(
                <div className="edit-shipment-form-section">
                    <div className="edit-shipment-td">
                        <h1>Shipping:</h1>
                    </div>
                </div>            
        );
        for (let i in orderList) {
            const item = orderList[i];
            if (item.orderStatus === "shipping")
                arr.push(
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            <a href={`/order/${item.orderId}`}>Order Number {item.orderId}</a>
                            <span>   ________  </span>
                            <a href={`/shipment/${item.shipmentId}`}>Shipment Number {item.shipmentId}</a>
                        </div>
                    </div> 
                );
        }
        
        arr.push(
                <div className="edit-shipment-form-section">
                    <div className="edit-shipment-td">
                        <h1>Done:</h1>
                    </div>
                </div>            
        );
        for (let i in orderList) {
            const item = orderList[i];
            if (item.orderStatus === "done")
                arr.push(
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            <a href={`/order/${item.orderId}`}>Order Number {item.orderId}</a>
                            <span>   ________  </span>
                            <a href={`/shipment/${item.shipmentId}`}>Shipment Number {item.shipmentId}</a>
                        </div>
                    </div> 
                );
        }
        
        arr.push(
                <div className="edit-shipment-form-section">
                    <div className="edit-shipment-td">
                        <h1>Rejected:</h1>
                    </div>
                </div>            
        );
        for (let i in orderList) {
            const item = orderList[i];
            if (item.orderStatus === "reject")
                arr.push(
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            <a href={`/order/${item.orderId}`}>Order Number {item.orderId}</a>
                            <span>   ________  </span>
                            <a href={`/shipment/${item.shipmentId}`}>Shipment Number {item.shipmentId}</a>
                        </div>
                    </div> 
                );
        }
        
        return arr;
    };
    
    return (
        <div className="edit-shipment-body">
            <div className="edit-user-form-wrapper">
                <div className="edit-shipment-form">
                    {noneOrderId()}
                </div>
            </div>
      </div>
    );
};

export default OrderAll;
