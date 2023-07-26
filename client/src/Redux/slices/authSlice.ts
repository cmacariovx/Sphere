import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { UserAuth } from '../../interfaces';

const initialState: UserAuth = {
    accessToken: null,
    isLoggedIn: false,
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login: (state, action: PayloadAction<string>) => {
            state.accessToken = action.payload;
            state.isLoggedIn = true;
        },
        logout: (state) => {
            state.accessToken = null;
            state.isLoggedIn = false;
        }
    }
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;
