import styles from './Login.module.css';

function Login(props: any) {
    return (
        <div className={styles.loginBackdrop} onMouseDown={props.close}>
            <div className={styles.login} onMouseDown={(e) => e.stopPropagation()}>
                <p className={styles.loginTitle}>Log into your Sphere account.</p>
                <div className={styles.inputContainer}>
                    <p className={styles.inputLabel}>Email</p>
                    <input type="email" className={styles.input} maxLength={40} />
                </div>
                <div className={styles.inputContainer}>
                    <p className={styles.inputLabel}>Password</p>
                    <input type="password" className={styles.input} maxLength={30}/>
                </div>
                <button className={styles.button}>Log In</button>
                <p className={styles.switch} onClick={props.switch}>Don't have an account? Sign up.</p>
            </div>
        </div>
    )
}

export default Login;
