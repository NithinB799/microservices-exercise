import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { createProduct, getAllProducts, getPaginatedProducts } from '../services/productService';

export const fetchProducts = createAsyncThunk(
    'products/fetchProducts',
    async () => {
        return await getAllProducts();
    }
);

export const fetchPaginatedProducts = createAsyncThunk(
    'products/fetchPaginatedProducts',
    async ({ page, size, sortBy }) => {
        return await getPaginatedProducts(page, size, sortBy);
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
        totalPages: 0,
        currentPage: 0,
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
            .addCase(fetchPaginatedProducts.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchPaginatedProducts.fulfilled, (state, action) => {
                state.loading = false;
                state.products = action.payload.content || [];
                state.totalPages = action.payload.totalPages || 0;
                state.currentPage = action.payload.number || 0;
            })
            .addCase(fetchPaginatedProducts.rejected, (state) => {
                state.loading = false;
                state.error = 'Failed to fetch paginated products';
            })
            .addCase(addProduct.fulfilled, (state, action) => {
                state.products.push(action.payload);
            });
    }
});

export default productSlice.reducer;