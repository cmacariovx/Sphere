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

        // handle 400 logout

        async function fetchUserData() {
            try {
                setIsLoading(true);
                const response = await fetch("/api/user/fetchBasicData", {
                    method: "GET",
                    credentials: "include",
                })

                if (!response.ok) throw new Error("Refresh token is invalid.")

                const data = await response.json();
                dispatch(login(data.newAccessToken));
            }
            catch (err: any) {
                setError(err.message);
            }
            finally {
                setIsLoading(false);
            }
        }

        fetchUserData();
    }, [isLoggedIn])

    return { isLoading, error };
}
