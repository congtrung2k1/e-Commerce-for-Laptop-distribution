import axios from 'axios';

const getOrderInformation = async (orderId) => {
    const url = `http://localhost:8080/order/edit/${orderId}`;
    const result = await axios.get(url);
    return result.data;
};

const getProductByOrderDetail = async (orderId) => {
    const url = `http://localhost:8080/order/${orderId}/getproduct`;
    const result = await axios.get(url);
    return result.data;
};

const getOrderDetail = async (orderId) => {
    const url = `http://localhost:8080/order/${orderId}/get`;
    const result = await axios.get(url);
    return result.data;
};

const updateOrder = async (orderId, data) => {
    const url = `http://localhost:8080/order/edit/${orderId}/update`;
    const result = await axios.post(url, data);
    return result.data;
};

const submitToSeller = async (orderId) => {
    const url = `http://localhost:8080/order/${orderId}/submit`;
    const result = await axios.post(url);
    return result.data;
};

const removeOrderDetail = async (idx) => {
    const url = `http://localhost:8080/orderdetail/${idx}/remove`;
    const result = await axios.post(url);
    return result.data;
};


const updateOrderDetail = async (orderId, data) => {
    const url = `http://localhost:8080/orderdetail/${orderId}/add`;
    const result = await axios.post(url, data);
    return result.data;
};

const addToOrder = async (orderId, data) => {
    const url = `http://localhost:8080/orderdetail/${orderId}/add`;
    const result = await axios.post(url, data);
    return result.data;
};

const getUserPendingOrder = async (userId, orderStatus) => {
    const url = `http://localhost:8080/order/${userId}/${orderStatus}`;
    const result = await axios.get(url);
    return result.data;
};

const createPendingOrder = async (userId) => {
    const url = `http://localhost:8080/order/create/${userId}`;
    const result = await axios.post(url);
    return result.data;
};

const approveOrder = async (orderId) => {
    const url = `http://localhost:8080/order/${orderId}/approve`;
    const result = await axios.post(url);
    return result.data;
};

const rejectOrder = async (orderId) => {
    const url = `http://localhost:8080/order/${orderId}/reject`;
    const result = await axios.post(url);
    return result.data;
};

export { 
    getOrderInformation,
    getProductByOrderDetail,
    getOrderDetail,
    updateOrder,
    submitToSeller,
    removeOrderDetail, 
    updateOrderDetail, 
    addToOrder, 
    getUserPendingOrder,
    createPendingOrder,
    approveOrder,
    rejectOrder
};
