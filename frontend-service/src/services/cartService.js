import axios from 'axios';

const CART_API_BASE_URL = 'http://localhost:8082/cart/items';

export const addItemToCart = async (cartItem) => {
    const response = await axios.post(CART_API_BASE_URL, cartItem);
    return response.data;
};

export const getAllCartItems = async () => {
    const response = await axios.get(CART_API_BASE_URL);
    return response.data;
};

export const deleteCartItem = async (id) => {
    await axios.delete(`${CART_API_BASE_URL}/${id}`);
    return id;
};

export const decreaseCartItemQuantity = async (id) => {
    const response = await axios.put(`${CART_API_BASE_URL}/${id}/decrease`);
    return response.data || { id };
};