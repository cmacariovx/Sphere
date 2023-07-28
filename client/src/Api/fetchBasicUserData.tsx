import { Dispatch } from "@reduxjs/toolkit";
import { login } from "../Redux/slices/authSlice";
import { basicUserDataResponse, errorInfo, loginPayload } from "../interfaces";
import { SetStateAction } from "react";
import { logoutMain } from "./logout";

export async function fetchBasicUserData(dispatch: Dispatch, setError: React.Dispatch<SetStateAction<errorInfo | null>>): Promise<void> {
    try {
        const response = await fetch("/api/user/fetchBasicData", {
            method: "GET",
            credentials: "include",
        });

        if (!response.ok) throw new Error("Your session has expired, please log back in to continue.");

        const data: basicUserDataResponse = await response.json();

        const loginPayload: loginPayload = {
            accessToken: data.newAccessToken,
            userData: data.basicUserData,
        }

        dispatch(login(loginPayload));
    }
    catch (err: any) {
        const error: errorInfo = {
            message: err.message,
            statusCode: 400,
            action: () => logoutMain(dispatch),
        }
        setError(error);
    }
}
