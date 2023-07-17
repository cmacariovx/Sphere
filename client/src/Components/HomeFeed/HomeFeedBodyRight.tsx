import styles from './HomeFeedBodyRight.module.css';

function HomeFeedBodyRight() {
    return (
        <div className={styles.homeFeedBodyRight}>
            <div className={styles.profileBanner}></div>
            <div className={styles.profilePicture}></div>

            <div className={styles.profileBody}>
                <div className={styles.profileInfo}>
                    <p className={styles.profileName}>Carlos Macario</p>
                    <p className={styles.profileTitle}>Founder of Sphere</p>
                </div>
            </div>
        </div>
    )
}

export default HomeFeedBodyRight;
