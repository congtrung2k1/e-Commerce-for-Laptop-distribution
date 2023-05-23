import axios from 'axios';

const getAllProduct = async () => {
    const url = `http://localhost:8080/product`;
    const result = await axios.get(url);
    return result.data;
};

const getProductById = async (product_id) => {
    const url = `http://localhost:8080/product/${product_id}`;
    const result = await axios.get(url);
    return result.data;
};

const getProductForCategory = async (category) => {
    const url = `http://localhost:8080/product/category/${category}`;
    const result = await axios.get(url);
    return result.data;
};
    
export { getProductForCategory, getProductById, getAllProduct };