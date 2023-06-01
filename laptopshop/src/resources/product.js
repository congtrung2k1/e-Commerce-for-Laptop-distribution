import axios from 'axios';

const updateNewImage = async (form) => {
    const url = `http://localhost:8080/product/upload`;
    await axios.post(url, form, {headers: {'Content-Type': 'multipart/form-data'}});
};

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

const productCreate = async (form) => {
    const url = `http://localhost:8080/product/create`;
    const result = await axios.post(url, form);
    return result.data;
};

const deleteProduct = async (product_id) => {
    const url = `http://localhost:8080/product/remove/${product_id}`;
    const result = await axios.delete(url);
    return result.data;
};

const updateProduct = async (product_id, form) => {
    const url = `http://localhost:8080/product/edit/${product_id}/update`;
    const result = await axios.post(url, form);
    return result.data;
};

    
export { updateNewImage, getProductForCategory, getProductById, getAllProduct, productCreate, deleteProduct, updateProduct };