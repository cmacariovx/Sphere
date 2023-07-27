import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { basicUserData, loginPayload, UserAuth } from '../../interfaces';

const initialState: UserAuth = {
    accessToken: null,
    isLoggedIn: false,
    needsAuth: false,
    userData: null,
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login: (state, action: PayloadAction<loginPayload>) => {
            state.accessToken = action.payload.accessToken;
            state.userData = action.payload.userData;
            state.isLoggedIn = true;
            state.needsAuth = false;
            localStorage.setItem("na", '0');
        },
        logout: (state) => {
            state.accessToken = null;
            state.userData = null;
            state.isLoggedIn = false;
            state.needsAuth = true;
            localStorage.setItem("na", '1');
        },
        setNeedsAuth: (state, action: PayloadAction<boolean>) => {
            state.needsAuth = action.payload;
        },
        setUserData: (state, action: PayloadAction<basicUserData>) => {
            state.userData = action.payload;
        }
    }
});

export const { login, logout, setNeedsAuth } = authSlice.actions;
export default authSlice.reducer;
