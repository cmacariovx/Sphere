import { Dispatch } from "@reduxjs/toolkit";
import { logout } from "../Redux/slices/authSlice";

export async function logoutMain(dispatch: Dispatch): Promise<void> {
    try {
        const response = await fetch("/api/auth/logout", {
            method: "GET",
            credentials: "include",
        });

        if (!response.ok) throw new Error("Logout failed.");
    }
    catch (err: any) {
        console.log(err.message);
    }
    finally {
        dispatch(logout());
    }
}
