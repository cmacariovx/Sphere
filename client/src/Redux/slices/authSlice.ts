import { createSlice, PayloadAction } from '@reduxjs/toolkit';

const initialState: UserAuth = {
    access_token: null,
    isLoggedIn: false,
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login: (state, action: PayloadAction<UserAuth>) => {
            state.access_token = action.payload.access_token;
            state.isLoggedIn = true;
        },
        logout: (state) => {
            state.access_token = null;
            state.isLoggedIn = false;
        }
    }
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;
