import axios from 'axios';

const getUserByUserId = async (userId) => {
    const url = `http://localhost:8080/customer/edit/${userId}`;
    const result = await axios.get(url);
    return result;
};

const updateUser = async (userId, data) => {
    const url = `http://localhost:8080/customer/edit/${userId}/update`;
    const result = await axios.post(url, data);
    return result;
};

export { 
    getUserByUserId,
    updateUser
};

