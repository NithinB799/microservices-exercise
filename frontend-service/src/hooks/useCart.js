import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCartItems } from '../features/cartSlice';

function useCart() {
    const dispatch = useDispatch();

    const { cartItems, loading, error } = useSelector((state) => state.cart);

    useEffect(() => {
        dispatch(fetchCartItems());
    }, [dispatch]);

    return {
        cartItems,
        loading,
        error
    };
}

export default useCart;