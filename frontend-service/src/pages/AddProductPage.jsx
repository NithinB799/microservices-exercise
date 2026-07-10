import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { addProduct } from '../features/productSlice';

function AddProductPage() {
    const dispatch = useDispatch();

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

        try {
            await dispatch(addProduct(product)).unwrap();

            alert('Product created successfully!');

            setProduct({
                name: '',
                price: '',
                stock: ''
            });

        } catch (error) {
            alert(
                error?.error ||
                error?.message ||
                error ||
                'Failed to create product'
            );
        }
    };

    return (
        <section className="page-section">
            <div className="page-heading">
                <div>
                    <h2>Add Product</h2>
                    <p>Create a new product and save it to the product service.</p>
                </div>
            </div>

            <div className="form-card">
                <form onSubmit={handleSubmit} className="product-form">
                    <div className="field">
                        <label>Product Name</label>
                        <input
                            type="text"
                            name="name"
                            value={product.name}
                            onChange={handleChange}
                            placeholder="Example: Keyboard"
                            required
                        />
                    </div>

                    <div className="field">
                        <label>Price</label>
                        <input
                            type="number"
                            name="price"
                            value={product.price}
                            onChange={handleChange}
                            placeholder="Example: 1500"
                            required
                        />
                    </div>

                    <div className="field">
                        <label>Stock</label>
                        <input
                            type="number"
                            name="stock"
                            value={product.stock}
                            onChange={handleChange}
                            placeholder="Example: 20"
                            required
                        />
                    </div>

                    <button type="submit" className="submit-button">
                        Add Product
                    </button>
                </form>
            </div>
        </section>
    );
}

export default AddProductPage;
