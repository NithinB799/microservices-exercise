import useCart from '../hooks/useCart';
import useRemoveCartItem from '../hooks/useRemoveCartItem';
import useDecreaseCartItem from '../hooks/useDecreaseCartItem';
import useAddToCart from '../hooks/useAddToCart';
import LoadingSpinner from '../components/LoadingSpinner';
import ErrorMessage from '../components/ErrorMessage';

function CartPage() {
    const { cartItems, loading, error } = useCart();
    const { handleRemoveCartItem } = useRemoveCartItem();
    const { handleDecreaseCartItem } = useDecreaseCartItem();
    const { handleAddToCart } = useAddToCart();

    return (
        <section className="page-section">
            <div className="page-heading">
                <div>
                    <h2>Cart Items</h2>
                    <p>Review all products currently added to the cart.</p>
                </div>
            </div>

            {loading && <LoadingSpinner />}

            {error && <ErrorMessage message={error} />}

            <div className="card">
                <div className="table-wrapper">
                    <table>
                        <thead>
                        <tr>
                            <th>Cart ID</th>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Update</th>
                            <th>Action</th>
                        </tr>
                        </thead>

                        <tbody>
                        {cartItems.length === 0 ? (
                            <tr>
                                <td colSpan="6" className="empty-state">
                                    Your cart is empty.
                                </td>
                            </tr>
                        ) : (
                            cartItems.map((item) => (
                                <tr key={item.id}>
                                    <td>{item.cartId}</td>
                                    <td>{item.productId}</td>
                                    <td>
                                        <strong>{item.productName}</strong>
                                    </td>
                                    <td>{item.quantity}</td>
                                    <td>
                                        <div className="quantity-actions">
                                            <button
                                                className="small-button"
                                                onClick={() => handleDecreaseCartItem(item.id)}
                                            >
                                                -
                                            </button>

                                            <button
                                                className="small-button"
                                                onClick={() => handleAddToCart(item.productId)}
                                            >
                                                +
                                            </button>
                                        </div>
                                    </td>
                                    <td>
                                        <button
                                            className="danger-button"
                                            onClick={() => handleRemoveCartItem(item.id)}
                                        >
                                            Remove
                                        </button>
                                    </td>
                                </tr>
                            ))
                        )}
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    );
}

export default CartPage;