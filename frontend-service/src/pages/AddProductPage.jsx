import { useState } from 'react';
import { createProduct } from '../services/productService';

function AddProductPage() {

    const [product, setProduct] = useState({
        name: '',
        price: '',
        stock: ''
    });

    const handleChange = (event) => {
        setProduct({
            ...product,
            [event.target.name]: event.target.value
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        await createProduct(product);

        alert("Product created successfully!");

        setProduct({
            name: '',
            price: '',
            stock: ''
        });
    };

    return (
        <div>
            <h2>Add Product</h2>

            <form onSubmit={handleSubmit}>

                <div>
                    <label>Name :</label>
                    <input
                        type="text"
                        name="name"
                        value={product.name}
                        onChange={handleChange}
                        required
                    />
                </div>

                <br />

                <div>
                    <label>Price :</label>
                    <input
                        type="number"
                        name="price"
                        value={product.price}
                        onChange={handleChange}
                        required
                    />
                </div>

                <br />

                <div>
                    <label>Stock :</label>
                    <input
                        type="number"
                        name="stock"
                        value={product.stock}
                        onChange={handleChange}
                        required
                    />
                </div>

                <br />

                <button type="submit">
                    Add Product
                </button>

            </form>
        </div>
    );
}

export default AddProductPage;