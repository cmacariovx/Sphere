import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import styles from './MainHeader.module.css';
import Signup from './Signup';
import Login from './Login';
import { useSelector } from 'react-redux';
import { RootState } from '../Redux/store';

function MainHeader() {
    const [showLogin, setShowLogin] = useState(false);
    const [showSignup, setShowSignup] = useState(false);
    const navigate = useNavigate();
    const isLoggedIn = useSelector((state: RootState) => state.auth.isLoggedIn);
    const needsAuth = useSelector((state: RootState) => state.auth.needsAuth);

    useEffect(() => {
        if (isLoggedIn) {
            setShowLogin(false);
            setShowSignup(false);
        }

        if (needsAuth) setShowLogin(true);
    }, [isLoggedIn, needsAuth]);

    useEffect(() => {
        if (showLogin || showSignup) document.body.classList.add('preventBgScroll');
        else document.body.classList.remove('preventBgScroll');
    }, [showLogin, showSignup]);

    return (
        <div className={styles.mainHeader}>
            <div className={styles.mainLeft}>
                <p className={styles.mainLeftLogo}>Sphere</p>
                <div className={styles.inputContainer}>
                    <i className={`${styles.searchIcon} fa-solid fa-magnifying-glass`}></i>
                    <input className={styles.input}
                        placeholder='Search'
                        maxLength={50}
                    />
                </div>
            </div>
            <div className={styles.mainRight}>
                <div className={styles.headerOption} onClick={() => navigate('/home')}>
                    <i className={`${styles.headerOptionLogo} fa-solid fa-house`}></i>
                    <p className={styles.headerOptionText}>Home</p>
                </div>
                <div className={styles.headerOption} onClick={() => {if (!isLoggedIn) setShowLogin(true)}}>
                    <i className={`${styles.headerOptionLogo} fa-solid fa-bell`}></i>
                    <p className={styles.headerOptionText}>Notifications</p>
                </div>
                <div className={styles.headerOption}>
                    <i className={`${styles.headerOptionLogo} fa-solid fa-comments`}></i>
                    <p className={styles.headerOptionText}>Messaging</p>
                </div>
                <div className={styles.headerOption} onClick={() => navigate('/profile')}>
                    <i className={`${styles.headerOptionLogo} fa-solid fa-user`}></i>
                    <p className={styles.headerOptionText}>Profile</p>
                </div>
            </div>

            {!isLoggedIn && showLogin &&
                <Login
                    switch={() => {setShowLogin(false); setShowSignup(true);}}
                    close={() => {setShowLogin(false); setShowSignup(false);}}
                />}
            {!isLoggedIn && showSignup &&
                <Signup
                    switch={() => {setShowSignup(false); setShowLogin(true);}}
                    close={() => {setShowSignup(false); setShowLogin(false);}}
                />}
        </div>
    )
}

export default MainHeader;
