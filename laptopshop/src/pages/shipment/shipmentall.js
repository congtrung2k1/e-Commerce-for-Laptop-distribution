import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./shipment.css"
import {
    getShipment,
    getAll,
    getAllShipmentUser,
} from "../../resources/shipment";

const Shipment = () => {
    const navigate = useNavigate();    
    const { cookies } = useCookies();
    const userId = cookies.userId;
    const { shipmentId } = useParams();

    const [shipment, setShipment] = useState([]);
    const [shipmentList, setShipmentList] = useState([]);
    
    const [root, setRoot] = useState((data) => {
        return data;
    });
        
    const getShipmentRoot = async () => {
        getAll().then((data) => {
            console.log(data);
            if (data) 
                setShipmentList(data);
        });
    };
    
    const getShipmentUser = async () => {
        getAllShipmentUser(userId).then((data) => {
            console.log(data);
            if (data) 
                setShipmentList(data);
        });
    };
    
    useEffect(() => {
        if (userId !== undefined)
            if (userId === '1'){
                setRoot(true);
                getShipmentRoot();
            } else {
                setRoot(false);
                getShipmentUser(userId);
            }
    }, []);
    
    const noneShipmentId = () => {
        console.log(root, shipmentList);
        if (root === undefined || shipmentList === undefined) 
            return (<div></div>);
        
        const arr = [];
        
        arr.push(
                <div className="edit-shipment-form-section">
                    <div className="edit-shipment-td">
                        <h1>Shipping:</h1>
                    </div>
                </div>            
        );

        for (let i in shipmentList) {
            const item = shipmentList[i];
            if (item.shipmentStatus === "pending")
                arr.push(
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            <a href={`/shipment/${item.shipmentId}`}>Order Number {item.orderId}</a>
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

        for (let i in shipmentList) {
            const item = shipmentList[i];
            if (item.shipmentStatus === "done")
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

        for (let i in shipmentList) {
            const item = shipmentList[i];
            if (item.shipmentStatus === "reject")
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
                    {noneShipmentId()}
                </div>
            </div>
      </div>
    );
};

export default Shipment;
