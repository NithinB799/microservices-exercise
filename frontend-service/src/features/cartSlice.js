import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import {
    addItemToCart,
    getAllCartItems,
    deleteCartItem,
    decreaseCartItemQuantity
} from '../services/cartService';

export const fetchCartItems = createAsyncThunk(
    'cart/fetchCartItems',
    async (_, { rejectWithValue }) => {
        try {
            return await getAllCartItems();
        } catch (error) {
            return rejectWithValue(
                error.response?.data?.error ||
                'Failed to fetch cart items'
            );
        }
    }
);

export const addCartItem = createAsyncThunk(
    'cart/addCartItem',
    async (cartItem, { rejectWithValue }) => {
        try {
            return await addItemToCart(cartItem);
        } catch (error) {
            return rejectWithValue(
                error.response?.data?.error ||
                'Failed to add product to cart'
            );
        }
    }
);
export const decreaseCartItem = createAsyncThunk(
    'cart/decreaseCartItem',
    async (id, { rejectWithValue }) => {
        try {
            return await decreaseCartItemQuantity(id);
        } catch (error) {
            return rejectWithValue(
                error.response?.data?.error ||
                'Failed to decrease cart item'
            );
        }
    }
);
export const removeCartItem = createAsyncThunk(
    'cart/removeCartItem',
    async (id, { rejectWithValue }) => {
        try {
            return await deleteCartItem(id);
        } catch (error) {
            return rejectWithValue(
                error.response?.data?.error ||
                'Failed to remove cart item'
            );
        }
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

            .addCase(removeCartItem.fulfilled, (state, action) => {
                state.cartItems = state.cartItems.filter(
                    (item) => item.id !== action.payload
                );
            })
            .addCase(decreaseCartItem.fulfilled, (state, action) => {
                if (!action.payload.cartId) {
                    state.cartItems = state.cartItems.filter(
                        (item) => item.id !== action.payload.id
                    );
                } else {
                    const index = state.cartItems.findIndex(
                        (item) => item.id === action.payload.id
                    );

                    if (index !== -1) {
                        state.cartItems[index] = action.payload;
                    }
                }
            })

            .addCase(fetchCartItems.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload || 'Failed to fetch cart items';
            })

            .addCase(addCartItem.fulfilled, (state, action) => {
                const updatedItem = action.payload;

                const index = state.cartItems.findIndex(
                    (item) => item.id === updatedItem.id
                );

                if (index !== -1) {
                    state.cartItems[index] = updatedItem;
                } else {
                    state.cartItems.push(updatedItem);
                }
            })

            .addCase(addCartItem.rejected, (state, action) => {
                state.error = action.payload;
            });
    }
});

export default cartSlice.reducer;