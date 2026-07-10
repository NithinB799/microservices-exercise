import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCartItems } from '../features/cartSlice';
import { fetchProducts } from '../features/productSlice';

function useCart() {
    const dispatch = useDispatch();

    const { cartItems, loading, error } = useSelector((state) => state.cart);
    const { products } = useSelector((state) => state.products);

    useEffect(() => {
        dispatch(fetchCartItems());
        dispatch(fetchProducts());
    }, [dispatch]);

    const cartItemsWithProductNames = cartItems.map((item) => {
        const product = products.find(
            (product) => product.id === item.productId
        );

        return {
            ...item,
            productName: product ? product.name : 'Unknown Product'
        };
    });

    return {
        cartItems: cartItemsWithProductNames,
        loading,
        error
    };
}

export default useCart;