import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { createProduct, getAllProducts } from '../services/productService';

export const fetchProducts = createAsyncThunk(
    'products/fetchProducts',
    async () => {
        return await getAllProducts();
    }
);

export const addProduct = createAsyncThunk(
    'products/addProduct',
    async (product) => {
        return await createProduct(product);
    }
);

const productSlice = createSlice({
    name: 'products',
    initialState: {
        products: [],
        loading: false,
        error: null
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchProducts.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchProducts.fulfilled, (state, action) => {
                state.loading = false;
                state.products = action.payload;
            })
            .addCase(fetchProducts.rejected, (state) => {
                state.loading = false;
                state.error = 'Failed to fetch products';
            })
            .addCase(addProduct.fulfilled, (state, action) => {
                state.products.push(action.payload);
            });
    }
});

export default productSlice.reducer;