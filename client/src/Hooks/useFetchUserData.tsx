import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../Redux/store";
import { login, logout } from "../Redux/slices/authSlice";

export function useFetchUserData() {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const dispatch = useDispatch();
    const isLoggedIn = useSelector((state: RootState) => state.auth.isLoggedIn);

    useEffect(() => {
        if (isLoggedIn) return;

        async function fetchUserData() {
            try {
                setIsLoading(true);
                const response = await fetch(process.env.REACT_APP_BACKEND_URL + "user/fetchBasicData", {
                    method: "POST",
                    credentials: "include",
                })

                if (!response.ok) throw new Error("Refresh token is invalid.")

                const data = await response.json();
                dispatch(login(data.access_token));
            }
            catch (err: any) {
                setError(err);
                dispatch(logout());
            }
            finally {
                setIsLoading(false);
            }
        }

        fetchUserData();
    }, [isLoggedIn])

    return { isLoading, error };
}
