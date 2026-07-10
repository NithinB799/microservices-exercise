import { useDispatch } from 'react-redux';
import { decreaseCartItem } from '../features/cartSlice';

function useDecreaseCartItem() {
    const dispatch = useDispatch();

    const handleDecreaseCartItem = async (id) => {
        try {
            await dispatch(decreaseCartItem(id)).unwrap();
        } catch (error) {
            alert(error || 'Failed to decrease cart item');
        }
    };

    return {
        handleDecreaseCartItem
    };
}

export default useDecreaseCartItem;