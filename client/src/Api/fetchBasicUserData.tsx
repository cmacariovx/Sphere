import { Dispatch } from "@reduxjs/toolkit";
import { login } from "../Redux/slices/authSlice";
import { basicUserDataResponse, loginPayload } from "../interfaces";
import { logoutMain } from "./logout";

export async function fetchBasicUserData(dispatch: Dispatch, isLoggedIn: boolean): Promise<void> {
    if (isLoggedIn) return;

    try {
        const response = await fetch("/api/user/fetchBasicData", {
            method: "GET",
            credentials: "include",
        });

        if (!response.ok) throw new Error("Refresh token is invalid.");

        const data: basicUserDataResponse = await response.json();

        const loginPayload: loginPayload = {
            accessToken: data.newAccessToken,
            userData: data.basicUserData,
        }

        dispatch(login(loginPayload));
    }
    catch (err: any) {
        await logoutMain(dispatch);
    }
}
