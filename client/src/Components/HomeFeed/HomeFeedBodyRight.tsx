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
                <div className={styles.explore}>
                    <p className={styles.exploreHeader}>Explore</p>
                    <div className={styles.exploreGroup}>
                        <p className={styles.exploreGroupHeader}>Recent</p>
                        <div className={styles.exploreItem}>
                            <i className={`${styles.exploreItemIcon} fa-solid fa-hashtag`}></i>
                            <p className={styles.exploreItemText}>softwareengineering</p>
                        </div>
                        <div className={styles.exploreItem}>
                            <i className={`${styles.exploreItemIcon} fa-solid fa-user-group`}></i>
                            <p className={styles.exploreItemText}>Software Engineering</p>
                        </div>
                    </div>
                    <div className={styles.exploreGroup}>
                        <p className={styles.exploreGroupHeader}>For you</p>
                        <div className={styles.exploreItem}>
                            <i className={`${styles.exploreItemIcon} fa-solid fa-hashtag`}></i>
                            <p className={styles.exploreItemText}>softwareengineering</p>
                        </div>
                        <div className={styles.exploreItem}>
                            <i className={`${styles.exploreItemIcon} fa-solid fa-user-group`}></i>
                            <p className={styles.exploreItemText}>Software Engineering</p>
                        </div>
                    </div>
                    <div className={styles.exploreGroup}>
                        <p className={styles.exploreGroupHeader}>Following</p>
                        <div className={styles.exploreItem}>
                            <i className={`${styles.exploreItemIcon} fa-solid fa-hashtag`}></i>
                            <p className={styles.exploreItemText}>softwareengineering</p>
                        </div>
                        <div className={styles.exploreItem}>
                            <i className={`${styles.exploreItemIcon} fa-solid fa-user-group`}></i>
                            <p className={styles.exploreItemText}>Software Engineering</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default HomeFeedBodyRight;
