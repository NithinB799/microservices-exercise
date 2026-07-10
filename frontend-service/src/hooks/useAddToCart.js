import { useDispatch } from 'react-redux';
import { addCartItem } from '../features/cartSlice';
import { fetchPaginatedProducts } from '../features/productSlice';

function useAddToCart() {
    const dispatch = useDispatch();

    const handleAddToCart = async (productId) => {
        const cartItem = {
            cartId: 4,
            productId: productId,
            quantity: 1
        };

        try {
            await dispatch(addCartItem(cartItem)).unwrap();

            await dispatch(
                fetchPaginatedProducts({
                    page: 0,
                    size: 5,
                    sortBy: 'id'
                })
            );

            alert('Product added to cart successfully!');
        } catch (error) {
            alert(
                error?.error ||
                error?.message ||
                error ||
                'Failed to add product to cart'
            );
        }
    };

    return {
        handleAddToCart
    };
}

export default useAddToCart;