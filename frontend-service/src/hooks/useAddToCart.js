import { useDispatch } from 'react-redux';
import { addCartItem } from '../features/cartSlice';

function useAddToCart() {
    const dispatch = useDispatch();

    const handleAddToCart = async (productId) => {
        const cartItem = {
            cartId: 4,
            productId: productId,
            quantity: 1
        };

        await dispatch(addCartItem(cartItem));

        alert('Product added to cart successfully!');
    };

    return {
        handleAddToCart
    };
}

export default useAddToCart;