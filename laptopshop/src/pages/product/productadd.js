import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from "react-router-dom";
import { useCookies } from './../../hooks/use-cookie/use-cookie';
import "./product.css";

import {  
    productCreate,
    updateNewImage
} from '../../resources/product';

const ProductCreate = () => {
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
        
    const handleFileChange = (file) => {
      setSelectedFile(file);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('image', selectedFile);
        updateNewImage(formData);
                
        var filterPrice = /^(?:[1-9]\d*|0)?(?:\.\d+)?$/;
        var filterDate  = /^\d{4}-\d{2}-\d{2}$/;

        if (name === "" || price === "" || description === "" || create_date === "" || cate === "" || selectedFile === undefined)
            alert("Please insert full information!");
        else
        if (!filterPrice.test(price))
            alert("Invalid price");
        else
        if (!filterDate.test(create_date))
            alert("Format of Import date id: YYYY-MM-dd (Example: 2023-12-29) !");
        else
        {
            const form = {
                name: name,
                price: price,
                description: description,
                image: `/image/${selectedFile.name}`,
                create_date: create_date,
                category: cate
            };
            productCreate(form).then((data) => {
                if (data !== undefined) {
                    alert("Successfully create product");
                    console.log(data.productId);
                    navigate(`/product/${data.productId}`);
                }
            });
        }
    };
    
    useEffect(() => {
        if (userId !== undefined && userId !== '1') {
            navigate("/product");
        }
    }, []);

    return (
        <div className="edit-user-body">
            <div className="edit-user-form-wrapper">
                <form className="edit-user-form" method="post" onSubmit={handleSubmit}>
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

export default ProductCreate;