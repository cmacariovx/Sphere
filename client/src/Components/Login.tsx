import { useRef, useState } from 'react';
import styles from './Login.module.css';
import { useDispatch } from 'react-redux';
import { login } from '../Redux/slices/authSlice';
import { authResponse, loginPayload } from '../interfaces';

function Login(props: any) {
    const [error, setError] = useState<string | null>(null);
    const [isLoading, setIsLoading] = useState(false);

    const emailRef = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);

    const dispatch = useDispatch();

    function isEmailValid(email: string) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }

    async function handleSubmit() {
        let errorSet = false;
        setError(null);

        if (!emailRef.current || !passwordRef.current || passwordRef.current.value.trim().length == 0) return;
        if (!isEmailValid(emailRef.current.value)) {
            setError("Invalid email.");
            errorSet = true;
        }

        if (errorSet) return;

        try {
            const response = await fetch("/api/auth/login", {
                method: "POST",
                body: JSON.stringify({
                    email: emailRef.current!.value.toLowerCase(),
                    password: passwordRef.current!.value,
                }),
                headers: {
                    'Content-Type': 'application/json',
                }
            })

            // bad credentials most likely
            if (!response.ok) throw new Error("Login was not successful, please try again.");

            const data: authResponse = await response.json();

            const loginPayload: loginPayload = {
                accessToken: data.accessToken,
                userData: data.userData,
            }

            dispatch(login(loginPayload));
        }
        catch (err) {
            console.log(err);
        }
        finally {
            setIsLoading(false);
        }
    }
    return (
        <div className={styles.loginBackdrop} onMouseDown={props.close}>
            <div className={styles.login} onMouseDown={(e) => e.stopPropagation()}>
                <p className={styles.loginTitle}>Log into your Sphere account.</p>
                <div className={styles.inputContainer}>
                    <p className={styles.inputLabel}>Email</p>
                    <input ref={emailRef} type="email" className={styles.input} maxLength={40} />
                </div>
                <div className={styles.inputContainer}>
                    <p className={styles.inputLabel}>Password</p>
                    <input ref={passwordRef} type="password" className={styles.input} maxLength={30}/>
                </div>
                <button className={styles.button} onClick={handleSubmit}>Log In</button>
                <p className={styles.switch} onClick={props.switch}>Don't have an account? Sign up.</p>
            </div>
        </div>
    )
}

export default Login;
