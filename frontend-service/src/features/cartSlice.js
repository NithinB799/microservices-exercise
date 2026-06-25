import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { addItemToCart, getAllCartItems } from '../services/cartService';

export const fetchCartItems = createAsyncThunk(
    'cart/fetchCartItems',
    async () => {
        return await getAllCartItems();
    }
);

export const addCartItem = createAsyncThunk(
    'cart/addCartItem',
    async (cartItem) => {
        return await addItemToCart(cartItem);
    }
);

const cartSlice = createSlice({
    name: 'cart',
    initialState: {
        cartItems: [],
        loading: false,
        error: null
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchCartItems.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchCartItems.fulfilled, (state, action) => {
                state.loading = false;
                state.cartItems = action.payload;
            })
            .addCase(fetchCartItems.rejected, (state) => {
                state.loading = false;
                state.error = 'Failed to fetch cart items';
            })
            .addCase(addCartItem.fulfilled, (state, action) => {
                state.cartItems.push(action.payload);
            });
    }
});

export default cartSlice.reducer;