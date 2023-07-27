import { useRef, useState } from 'react';
import styles from './Signup.module.css';
import { useDispatch } from 'react-redux';
import { login } from '../Redux/slices/authSlice';
import { authResponse, loginPayload } from '../interfaces';

function Signup(props: any) {
    const [isAgreed, setIsAgreed] = useState(false);
    const [isVisible, setIsVisible] = useState(false);
    const [errors, setErrors] = useState<{[key: string]: string} | null>(null);
    const [isLoading, setIsLoading] = useState(false);

    const firstNameRef = useRef<HTMLInputElement>(null);
    const lastNameRef = useRef<HTMLInputElement>(null);
    const emailRef = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);

    const dispatch = useDispatch();

    function isPasswordSecure(password: string) {
        return /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/.test(password);
    };

    function isEmailValid(email: string) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }

    function isNameValid(name: string) {
        return /^[A-Za-z]{2,20}$/.test(name);
    }

    async function handleSubmit() {
        let errorSet = false;
        setErrors(null);

        const newErrors: {[key: string]: string} = {};

        if (!firstNameRef.current || !isNameValid(firstNameRef.current.value)) {
            newErrors.firstName = 'First name must be between 2 and 20 characters and only contain letters.';
            errorSet = true;
        }

        if (!lastNameRef.current || !isNameValid(lastNameRef.current.value)) {
            newErrors.lastName = 'Last name must be between 2 and 20 characters and only contain letters.';
            errorSet = true;
        }

        if (!emailRef.current || !isEmailValid(emailRef.current.value)) {
            newErrors.email = 'Invalid email.';
            errorSet = true;
        }

        if (!passwordRef.current || !isPasswordSecure(passwordRef.current.value)) {
            newErrors.password = 'Password must contain at least one uppercase and one lowercase character, one digit, and be at least 8 characters long.';
            errorSet = true;
        }

        if (!isAgreed) newErrors.agreed = "You must be 16 years of age or older to create an account."

        setErrors(newErrors);

        if (errorSet) return;

        try {
            const response = await fetch("/api/auth/signup", {
                method: "POST",
                body: JSON.stringify({
                    firstName: firstNameRef.current!.value.toLowerCase().trim(),
                    lastName: lastNameRef.current!.value.toLowerCase().trim(),
                    email: emailRef.current!.value.toLowerCase(),
                    password: passwordRef.current!.value,
                }),
                headers: {
                    'Content-Type': 'application/json',
                }
            })

            if (!response.ok) throw new Error("Signup was not successful, please try again.");

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
        <div className={styles.signupBackdrop} onMouseDown={props.close}>
            <div className={styles.signup} onMouseDown={(e) => e.stopPropagation()}>
                <p className={styles.signupTitle}>Create a Sphere account, simply.</p>
                <div className={styles.inputContainer}>
                    <p className={styles.inputLabel}>First name</p>
                    <input ref={firstNameRef} className={styles.input} maxLength={20} />
                    {errors?.firstName && <p className={styles.inputError}>{errors!.firstName}</p>}
                </div>
                <div className={styles.inputContainer}>
                    <p className={styles.inputLabel}>Last name</p>
                    <input ref={lastNameRef} className={styles.input} maxLength={20} />
                    {errors?.lastName && <p className={styles.inputError}>{errors!.lastName}</p>}
                </div>
                <div className={styles.inputContainer}>
                    <p className={styles.inputLabel}>Email</p>
                    <input type="email" ref={emailRef} className={styles.input} maxLength={40} />
                    {errors?.email && <p className={styles.inputError}>{errors!.email}</p>}
                </div>
                <div className={styles.inputContainer}>
                    <p className={styles.inputLabel}>Password</p>
                    <div className={styles.passwordInput}>
                        <input type={isVisible ? 'text' : 'password'} ref={passwordRef} className={styles.input} maxLength={30}/>
                        <i className={`${styles.passwordIcon} fa-solid ${isVisible ? 'fa-eye' : 'fa-eye-slash'}`} onClick={() => setIsVisible(!isVisible)}></i>
                    </div>
                    {errors?.password && <p className={styles.inputError}>{errors!.password}</p>}
                </div>
                <div className={styles.inputContainer1}>
                    <div className={styles.checkRow}>
                        <input className={styles.input1} type="checkbox" onChange={(e) => setIsAgreed(e.target.checked)}/>
                        <p className={styles.inputLabel}>I am 16 years of age or older and agree to the -terms and conditions- of Sphere.</p>
                    </div>
                    {errors?.agreed && <p className={styles.inputError}>{errors!.agreed}</p>}
                </div>
                <button className={styles.button} onClick={handleSubmit}>Create</button>
                <p className={styles.switch} onClick={props.switch}>Already have an account? Log in.</p>
            </div>
        </div>
    )
}

export default Signup;
