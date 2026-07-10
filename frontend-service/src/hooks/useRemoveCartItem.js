import { useDispatch } from 'react-redux';
import { removeCartItem } from '../features/cartSlice';

function useRemoveCartItem() {
    const dispatch = useDispatch();

    const handleRemoveCartItem = async (id) => {
        try {
            await dispatch(removeCartItem(id)).unwrap();
            alert('Cart item removed successfully!');
        } catch (error) {
            alert(error || 'Failed to remove cart item');
        }
    };

    return {
        handleRemoveCartItem
    };
}

export default useRemoveCartItem;