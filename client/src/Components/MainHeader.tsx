import { useNavigate } from 'react-router-dom';
import styles from './MainHeader.module.css';

function MainHeader() {
    const navigate = useNavigate();

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
                <div className={styles.headerOption}>
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
        </div>
    )
}

export default MainHeader;
