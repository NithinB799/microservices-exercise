import useCart from '../hooks/useCart';
import LoadingSpinner from '../components/LoadingSpinner';
import ErrorMessage from '../components/ErrorMessage';

function CartPage() {
    const { cartItems, loading, error } = useCart();

    return (
        <div>
            <h2>Cart Items</h2>

            {loading && <LoadingSpinner />}

            {error && <ErrorMessage message={error} />}

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