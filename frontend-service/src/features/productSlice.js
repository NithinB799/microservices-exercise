import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import {
    createProduct,
    getAllProducts,
    getPaginatedProducts
} from '../services/productService';


export const fetchProducts = createAsyncThunk(
    'products/fetchProducts',
    async (_, { rejectWithValue }) => {
        try {
            return await getAllProducts();
        } catch (error) {
            return rejectWithValue(
                error.response?.data?.error ||
                'Failed to fetch products'
            );
        }
    }
);


export const fetchPaginatedProducts = createAsyncThunk(
    'products/fetchPaginatedProducts',
    async ({ page, size, sortBy }, { rejectWithValue }) => {
        try {
            return await getPaginatedProducts(page, size, sortBy);
        } catch (error) {
            return rejectWithValue(
                error.response?.data?.error ||
                'Failed to fetch paginated products'
            );
        }
    }
);


export const addProduct = createAsyncThunk(
    'products/addProduct',
    async (product, { rejectWithValue }) => {
        try {
            return await createProduct(product);
        } catch (error) {
            return rejectWithValue(
                error.response?.data?.error ||
                'Failed to create product'
            );
        }
    }
);


const productSlice = createSlice({
    name: 'products',

    initialState: {
        products: [],
        totalPages: 0,
        currentPage: 0,
        loading: false,
        error: null
    },

    reducers: {},

    extraReducers: (builder) => {
        builder

            // Fetch all products
            .addCase(fetchProducts.pending, (state) => {
                state.loading = true;
                state.error = null;
            })

            .addCase(fetchProducts.fulfilled, (state, action) => {
                state.loading = false;
                state.products = action.payload;
            })

            .addCase(fetchProducts.rejected, (state, action) => {
                state.loading = false;
                state.error =
                    action.payload || 'Failed to fetch products';
            })


            // Fetch paginated products
            .addCase(fetchPaginatedProducts.pending, (state) => {
                state.loading = true;
                state.error = null;
            })

            .addCase(fetchPaginatedProducts.fulfilled, (state, action) => {
                state.loading = false;

                state.products = Array.isArray(action.payload)
                    ? action.payload
                    : action.payload.content || [];

                state.totalPages =
                    action.payload.totalPages || 1;

                state.currentPage =
                    action.payload.number || 0;
            })

            .addCase(fetchPaginatedProducts.rejected, (state, action) => {
                state.loading = false;
                state.error =
                    action.payload ||
                    'Failed to fetch paginated products';
            })


            // Add product
            .addCase(addProduct.pending, (state) => {
                state.loading = true;
                state.error = null;
            })

            .addCase(addProduct.fulfilled, (state, action) => {
                state.loading = false;
                state.products.push(action.payload);
            })

            .addCase(addProduct.rejected, (state, action) => {
                state.loading = false;
                state.error =
                    action.payload || 'Failed to create product';
            });
    }
});


export default productSlice.reducer;