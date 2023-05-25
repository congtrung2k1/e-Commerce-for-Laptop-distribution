import axios from 'axios';

const getShipment = async (shipmentId) => {
    const url = `http://localhost:8080/shipment/edit/${shipmentId}`;
    const result = await axios.get(url);
    return result.data;
};

const getAll = async () => {
    const url = `http://localhost:8080/shipment/get`;
    const result = await axios.get(url);
    return result.data;
};

const getAllShipmentUser = async (userId) => {
    const url = `http://localhost:8080/shipment/${userId}`;
    const result = await axios.get(url);
    return result.data;
};

const getShipmentByOrderId = async (order_id) => {
    const url = `http://localhost:8080/shipment/get/${order_id}`;
    const result = await axios.get(url);
    return result.data;
};

const doneShipment = async (shipment_id) => {
    const url = `http://localhost:8080/shipment/edit/${shipment_id}/done`;
    const result = await axios.post(url);
    return result.data;
};

const rejectShipment = async (shipment_id) => {
    const url = `http://localhost:8080/shipment/edit/${shipment_id}/reject`;
    const result = await axios.post(url);
    return result.data;
};
    
export { getShipment, getAll, getAllShipmentUser, getShipmentByOrderId, doneShipment, rejectShipment };
