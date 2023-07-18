import styles from './ProfileBody.module.css';
import seattle from '../../Assets/seattle.png';
import profilePicture from '../../Assets/profile.jpg';

function ProfileBody() {
    return (
        <div className={styles.profileBody}>
            <img src={seattle} className={styles.profileBanner} />
            <img src={profilePicture} className={styles.profilePicture} />
            <div className={styles.profileUpper}>
                <div className={styles.profileUpperUpper}>
                    <div className={styles.profileStats}>
                        <pre className={styles.profileStat0}>544  </pre>
                        <p className={styles.profileStat1}>Followers</p>
                        <p className={styles.profileDot}>•</p>
                        <pre className={styles.profileStat0}>35  </pre>
                        <p className={styles.profileStat1}>Following</p>
                        <p className={styles.profileDot}>•</p>
                        <pre className={styles.profileStat0}>12  </pre>
                        <p className={styles.profileStat1}>Posts</p>
                    </div>
                    <div className={styles.profileButtons}>
                        <div className={styles.messageButton}>
                            <i className={`${styles.messageIcon} fa-solid fa-comment`}></i>
                            <p className={styles.messageText}>Message</p>
                        </div>
                        <div className={styles.connectButton}>
                            <i className={`${styles.connectIcon} fa-solid fa-plus`}></i>
                            <p className={styles.connectText}>Connect</p>
                        </div>
                    </div>
                </div>
                <div className={styles.profileUpperLower}>
                    <div className={styles.profileUpperLowerLeft}>
                        <p className={styles.profileName}>Carlos Macario</p>
                        <p className={styles.profileTitle}>Founder of Sphere</p>
                    </div>
                    <div className={styles.profileUpperLowerRight}>

                    </div>
                </div>
            </div>
            <div className={styles.profileLower}>

            </div>
        </div>
    )
}

export default ProfileBody;
