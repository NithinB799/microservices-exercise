import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCartItems } from '../features/cartSlice';

function CartPage() {

    const dispatch = useDispatch();

    const { cartItems, loading, error } = useSelector((state) => state.cart);

    useEffect(() => {
        dispatch(fetchCartItems());
    }, [dispatch]);

    return (
        <div>
            <h2>Cart Items</h2>

            {loading && <p>Loading cart items...</p>}

            {error && <p>{error}</p>}

            <table border="1" cellPadding="10">
                <thead>
                <tr>
                    <th>Cart ID</th>
                    <th>Product ID</th>
                    <th>Quantity</th>
                </tr>
                </thead>

                <tbody>
                {cartItems.map((item) => (
                    <tr key={item.id}>
                        <td>{item.cartId}</td>
                        <td>{item.productId}</td>
                        <td>{item.quantity}</td>
                    </tr>
                ))}
                </tbody>

            </table>

        </div>
    );
}

export default CartPage;