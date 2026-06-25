import axios from 'axios';

const PRODUCT_API_BASE_URL = 'http://localhost:8081/products';

export const getAllProducts = async () => {
    const response = await axios.get(PRODUCT_API_BASE_URL);
    return response.data;
};

export const createProduct = async (product) => {
    const response = await axios.post(PRODUCT_API_BASE_URL, product);
    return response.data;
};