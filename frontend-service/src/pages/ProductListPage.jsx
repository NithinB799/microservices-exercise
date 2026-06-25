import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProducts } from '../features/productSlice';
import { addCartItem } from '../features/cartSlice';

function ProductListPage() {
    const dispatch = useDispatch();

    const { products, loading, error } = useSelector((state) => state.products);

    useEffect(() => {
        dispatch(fetchProducts());
    }, [dispatch]);

    const handleAddToCart = async (productId) => {
        const cartItem = {
            cartId: 4,
            productId: productId,
            quantity: 1
        };

        await dispatch(addCartItem(cartItem));

        alert('Product added to cart successfully!');
    };

    return (
        <div>
            <h2>Product List</h2>

            {loading && <p>Loading products...</p>}

            {error && <p>{error}</p>}

            <table border="1" cellPadding="10">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                {products.map((product) => (
                    <tr key={product.id}>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                        <td>{product.price}</td>
                        <td>{product.stock}</td>
                        <td>
                            <button onClick={() => handleAddToCart(product.id)}>
                                Add to Cart
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default ProductListPage;