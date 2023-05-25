import React, { useEffect, useState } from 'react'
import { useNavigate, Link, useParams } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./order.css"
import {
    getOrderInformation,
    getProductByOrderDetail,
    getOrderDetail,
    getAllUserOrder,
    updateOrder,
    submitToSeller,
    removeOrderDetail,
    updateOrderDetail,
    addToOrder,
    getUserPendingOrder,
    createPendingOrder,
    approveOrder,
    rejectOrder
} from '../../resources/order';

import {
    getUserByUserId
} from '../../resources/user';

const Order = () => {
    const navigate = useNavigate();
    const {cookies} = useCookies();

    const userId = cookies.userId;
    const {orderId} = useParams();
    
    const [user, setUser] = useState([]);
    const [root, setRoot] = useState((data) => {
        return data;
    });
    const [pend, setPend] = useState((data) => {
        return data;
    });
    const [subm, setSubm] = useState((data) => {
        return data;
    });

    const getUser = async () => {
        // If root, show all order
        getUserByUserId(userId).then((response) => {
            if (response.data) {
                setUser(response.data);
                if (response.data.roleId === 0) setRoot(true); else setRoot(false);
            }
        });
    };
    
    const [orderInfor, setOrderInfor] = useState([]);
    const [orderList, setOrderList] = useState([]);
    const [orderDetailList, setOrderDetailList] = useState((data) => {
        return data;
    });
    const [productList, setProductList] = useState((data) => {
        return data;
    });
    
    const [own, setOwn] = useState((data) => {
        return data;
    });

    const [description, setDescription] = useState([]);
    const [shippingAddr, setShippingAddr] = useState([]);
    const [orderStatus, setOrderStatus] = useState([]);
    const [discount, setDiscount] = useState([]);
    
    const getOrderInfor = async (orderId) => {
        getOrderInformation(orderId).then((data) => {
            if (data) {
                setOrderInfor(data);
                setDescription(data.description);
                setShippingAddr(data.shippingAddr);
                setOrderStatus(data.orderStatus);
                setDiscount(data.discount);
                if (data.orderStatus === "submit") setSubm(true); else setSubm(false);
                if (data.orderStatus === "pending") setPend(true); else setPend(false);
            }
        }).catch((error) => console.log(error.message));
    };

    const getOrderDetailOfOrder = async (orderId) => {
        getOrderDetail(orderId).then((data) => {
            if (data)
                setOrderDetailList(data);
        }).catch((error) => console.log(error.message));

        getProductByOrderDetail(orderId).then((data) => {
            if (data) {
                setProductList(data);
            }
        }).catch((error) => console.log(error.message));
    };

    const incProduct = async (orderId, productId) => {
        const form = {
            product_id: productId,
            quantity: 1
        };
        updateOrderDetail(orderId, form);
        navigate(0);
    };

    const decProduct = async (orderId, productId, item) => {
        const form = {
            product_id: productId,
            quantity: -1
        };
        if (item.quantity - 1 > 0)
            updateOrderDetail(orderId, form);
        else
            removeOrderDetail(item.idx);
        navigate(0);
    };

    const handleSave = async () => {
        const form = {
            description: description,
            shippingAddr: shippingAddr,
            order_status: orderStatus,
            discount: discount
        };
        updateOrder(orderInfor.orderId, form);
    };

    const handleSubmit = async () => {
        const form = {
            description: description,
            shippingAddr: shippingAddr,
            order_status: "submit",
            discount: discount
        };
        updateOrder(orderInfor.orderId, form);
    };

    const handleApprove = async () => {
        approveOrder(orderId).then((response) => {
           if (response.data) {
               alert(response.data.shipmentId);
               navigate(`/shipment/${response.data.shipmentId}`);
           }
        });
    };

    const handleReject = async () => {
        rejectOrder(orderId).then((response) => {
           if (response.data) {
                navigate(0);
                navigate(`/order`);
           }
        });
    };

    useEffect(() => {
        getUser();        
        getAllUserOrder(userId).then((data) => {
            if (data !== undefined) {
                setOrderList(data);
                if (userId !== '1' && userId !== undefined) {
                    let flag = false;
                    for (let i in data) {
                        const item = data[i];
                        if (item.orderId.toString() === orderId) {
                            setOwn(true);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        alert("You do not own this one!");
                        navigate("/order");
                    }
                }
            }
        })
        
        getOrderInfor(orderId);
        getOrderDetailOfOrder(orderId);

    }, [orderId]);

    const showProduct = () => {
        if (own !== true) return (<div></div>);
        const arr = [];
        if (productList === undefined) return arr;
        for (let i in orderDetailList) {
            const item = orderDetailList[i];
            const product = productList[i];
            arr.push(
                    <div className="show-order-item">
                        <div className="show-order-item-general-info">
                            <img className="show-order-item" src={window.location.origin + product.image}/>
                            <p className="show-order-item-title">{product.name}</p>
                            <div className="center-ver">
                                <button className="show-order-pay-btn" onClick={() => decProduct(item.orderId, item.productId, item)}> - </button>
                            </div>
                            <div className="center-ver">
                                <p className="show-order-item-info">{item.quantity}</p>
                            </div>
                            <div className="center-ver">
                                <button className="show-order-pay-btn" onClick={() => incProduct(item.orderId, item.productId)}> + </button>
                            </div>
                            <div className="center-ver">
                                <p color="red">$ {item.price}</p>
                            </div>
                        </div>
                    </div>
                    );
        };
        return arr;
    };

    const showOrder = () => {
        if (own !== true) return (<div></div>);

        let roNote;
        let roAddr;
        let roStat;
        let roDisc;
        
        if (root) {
            roNote = true;
            roAddr = true;
            
            if (!subm && !pend) {
                roStat = true;
                roDisc = true;
            } else {
                roStat = false;
                roDisc = false;
            }
        }
        else {
            roStat = true;
            roDisc = true;
            if (pend) {
                roNote = false;
                roAddr = false;
            }
            else {
                roNote = true;
                roAddr = true;
            }
        }
        
        return (
                <form className="edit-info-form">
                    <table className="edit-info-table">
                        <tbody>
                            <div className="edit-info-form-section">
                                <tr><td className="edit-info-td">Note for seller</td></tr>
                                <tr><td className="edit-info-td"><label for="description"><textarea  name="description" cols="40" rows="5" className="edit-info-item" value={description} readOnly={roNote} onChange={(e) => setDescription(e.target.value)} /></label></td></tr>
                            </div>
                            <div className="edit-info-form-section">
                                <tr><td className="edit-info-td"><label for="shippingAddr">Address</label></td></tr>
                                <tr><td className="edit-info-td"><textarea  name="shippingAddr" cols="40" rows="5" className="edit-info-item" value={shippingAddr} readOnly={roAddr} onChange={(e) => setShippingAddr(e.target.value)} /></td></tr>
                            </div>
                            <div className="edit-info-form-section">
                                <tr><td className="edit-info-td"><label for="order_status">Status</label></td></tr>
                                <tr><td className="edit-info-td"><input type='text' className="edit-info-item" name="order_status" value={orderStatus} readOnly={roStat} onChange={(e) => setOrderStatus(e.target.value)}/></td></tr>

                            </div>
                            <div className="edit-info-form-section">
                                <tr><td className="edit-info-td"><label for="discount">Discount</label></td></tr>
                                <tr><td className="edit-info-td"><input type='text' className="edit-info-item" name="discount" value={discount} readOnly={roDisc} onChange={(e) => setDiscount(e.target.value)}/></td></tr>
                            </div>
                            <div>
                                <p className="show-order-info-item"><font face = "Verdana" color = "red"><b>{orderInfor.amount} $</b></font></p>
                            </div>
                            <div>
                                <input className="edit-info-edit-btn" type="submit" value="SUBMIT" onClick={handleSubmit} style={{ display: (root || !pend) ? 'none' : 'block' }} />
                                <input className="edit-info-edit-btn" type="submit" value="SAVE INFO" onClick={handleSave} style={{ display: (!root && !pend) ? 'none' : 'block' }} />
                                <input className="edit-info-edit-btn" type="submit" value="APPROVE" onClick={handleApprove} style={{ display: (root && subm) ? 'block' : 'none' }} />
                                <input className="edit-info-edit-btn" type="submit" value="REJECT" onClick={handleReject} style={{ display: (root && subm) ? 'block' : 'none' }} />
                            </div>
                        </tbody>
                    </table>
                </form>
        );
    };

    return (
            <div className="backgroundorder">
                <div className="show-order-wrapper">
                    <div className="show-order-list">
                        {showProduct()}
                    </div>
                    <div className="info">
                        {showOrder()}
                    </div>
                </div>
            </div>
    );
};

export default Order;