import { useEffect, useState } from 'react';
import { getAllProducts } from '../services/productService';

function ProductListPage() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        loadProducts();
    }, []);

    const loadProducts = async () => {
        const data = await getAllProducts();
        setProducts(data);
    };

    return (
        <div>
            <h2>Product List</h2>

            <table border="1" cellPadding="10">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Stock</th>
                </tr>
                </thead>

                <tbody>
                {products.map((product) => (
                    <tr key={product.id}>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                        <td>{product.price}</td>
                        <td>{product.stock}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default ProductListPage;