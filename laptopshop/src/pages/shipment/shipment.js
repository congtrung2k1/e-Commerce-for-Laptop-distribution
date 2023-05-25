import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./shipment.css"
import {
    getShipment,
    getAllShipmentUser,
    getShipmentByOrderId, 
    doneShipment, 
    rejectShipment
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
    const [pend, setPend] = useState((data) => {
       return data; 
    });
    const [own, setOwn] = useState((data) => {
       return data; 
    });
    
    const getShipmentInfor = async () => {
        getShipment(shipmentId).then((data) => {
            if (data) {    
                setShipment(data);
                if (data.shipmentStatus === "pending") setPend(true); else setPend(false);
                if (userId === '1') {
                    setRoot(true);
                    setOwn(true);
                }
                else {
                    setRoot(false);
                    setOwn(false);
                }
            }
        }).catch(error => console.log(error.message));
    };
            
    const doneHandle = async () => {
        doneShipment(shipmentId).then((data) => {
            console.log(data);
            if (data) {
                navigate(0);
                navigate(`/shipment/${shipmentId}`);
            }
        }).catch(error => console.log(error.message));
    };
    
    const rejectHandle = async () => {
        rejectShipment(shipmentId).then((data) => {
            if (data) {
                navigate(0);
                navigate(`/shipment/${shipmentId}`);
            }
        }).catch(error => console.log(error.message));
    };

    useEffect(() => {
        getShipmentInfor();
        getAllShipmentUser(userId).then((data) => {
            if (data !== undefined) {
                setShipmentList(data);
                if (userId !== '1' && userId !== undefined) {
                    let flag = false;
                    for (let i in data) {
                        const item = data[i];
                        if (item.shipmentId.toString() === shipmentId) {
                            setOwn(true);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        alert("You do not own this one!");
                        navigate("/shipment");
                    }
                }
            }
        });
    }, [userId]);
    
    const hasShipmentId = () => {
        if (root === undefined || pend === undefined || own === undefined || own === false) 
            return (<div></div>);
        
        return (
                <div className="edit-shipment-form">
                    <div className="edit-shipment-form-description">
                        <p className="show-shipment-item-title">Your shipment ID is: {shipmentId}</p>
                    </div>
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            This shipment belong to order number: {shipment.orderId}
                        </div>
                    </div>
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            Modified day: {shipment.shipmentDate}
                        </div>
                    </div>
                    <div className="edit-shipment-form-section">
                        <div className="edit-shipment-td">
                            Your shipment status: {shipment.shipmentStatus}
                        </div>
                    </div>
                    <div>
                        <input className="edit-shipment-submit-btn" type="submit" value="DONE" onClick={doneHandle} style={{ display: (root && pend) ? 'block' : 'none' }} />
                        <input className="edit-shipment-submit-btn" type="submit" value="REJECT" onClick={rejectHandle} style={{ display: (root && pend) ? 'block' : 'none' }} />
                    </div>
                </div>
        );
    };
    
    return (
        <div className="edit-shipment-body">
            <div className="edit-shipment-form-wrapper">
                {hasShipmentId()}
            </div>
      </div>
    );
};

export default Shipment;