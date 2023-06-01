import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import ImageUploading from "react-images-uploading";
import "./product.css";

import {  
    getProductById,
    updateProduct,
    updateNewImage
} from '../../resources/product';

const ProductUpdate = () => {
    const navigate = useNavigate();    
    const { cookies } = useCookies();
    const userId = cookies.userId;
    const { productId } = useParams();
    
    const [product, setProduct] = useState([]);
    const [name, setName] = useState([]);
    const [price, setPrice] = useState([]);
    const [description, setDescription] = useState([]);
    const [image, setImage] = useState([]);
    const [selectedFile, setSelectedFile] = useState(null);
    const [create_date, setCreateDate] = useState([]);
    const [cate, setCate] = useState([]);
    
    const getProduct = async () => {
        getProductById(productId).then((data) => {
            if (data !== undefined) {
                setProduct(data);
                setName(data.name);
                setPrice(data.price);
                setDescription(data.description);
                setImage(data.image);
                setCreateDate(data.createDate);
                setCate(data.category);
            }
        });
    };
    
    const handleFileChange = (file) => {
      setSelectedFile(file);
    };
    
    const uploadImage = async () => {
        const formData = new FormData();
        formData.append('image', selectedFile);
        updateNewImage(formData);
        alert("Image update successfully !!!");
        setImage(`/image/${selectedFile.name}`);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = {
            name: product.name,
            price: product.price,
            description: product.description,
            image: product.image,
            create_date: product.create_date,
            category: product.cate
        };
        if (name !== "" && name !== product.name) {
            form.name = name;
        }
        if (price !== "" && price !== product.email) {
            form.price = price;
        }
        if (description !== "" && description !== product.description) {
            form.description = description;
        }
        if (image !== "" && image !== product.image) {
            form.image = image;
        }
        if (create_date !== "" && create_date !== product.create_date) {
            form.create_date = create_date;
        }
        if (cate !== "" && cate !== product.cate) {
            form.cate = cate;
        }
        
        alert("Successfully update product");
        updateProduct(productId, form);
    };
    
    useEffect(() => {
        if (userId !== undefined && userId !== '1') {
            navigate("/product");
        }
        if (product === undefined || product.length === 0) getProduct();
    }, [product]);

    return (
        <div className="edit-user-body">
            <div className="edit-user-form-wrapper">
                <form className="edit-user-form" onSubmit={handleSubmit}>
                    <h3 className="edit-user-form-description">Product Information</h3>
                    <table className="edit-user-table">
                      <tbody>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="name">Name</label></td></tr>
                            <tr><td className="edit-user-td"><textarea cols="100%" rows="5" className="usertextarea" name="name" value={name} onChange={(e) => setName(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="image">Image</label></td></tr>
                            <tr>
                                <td className="edit-user-td">
                                    <input type='file' accept="image/jpeg" className="edit-user-item" name="image" onChange={(e) => handleFileChange(e.target.files[0])}/>
                                    <button onClick={uploadImage}>Upload</button>
                                </td>
                            </tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="price">Price</label></td></tr>
                            <tr><td className="edit-user-td"><input type='text' className="edit-user-item" name="price" value={price} onChange={(e) => setPrice(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="description">Description</label></td></tr>
                            <tr><td className="edit-user-td"><textarea cols="100%" rows="5" className="usertextarea" name="description" value={description} onChange={(e) => setDescription(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="create_date">Import Date</label></td></tr>
                            <tr><td className="edit-user-td"><input type='text' className="edit-user-item" name="create_date" value={create_date} onChange={(e) => setCreateDate(e.target.value)}/></td></tr>
                        </div>
                        <div className="edit-user-form-section">
                            <tr><td className="edit-user-td"><label for="cate">Provider</label></td></tr>
                            <tr><td className="edit-user-td"><input type='cate' className="edit-user-item" name="cate" value={cate} onChange={(e) => setCate(e.target.value)}/></td></tr>
                        </div>
                      </tbody>
                    </table>
                    <input className="edit-user-submit-btn" type="submit" value="Submit"/>
                </form>
            </div>
      </div>
    );
};

export default ProductUpdate;