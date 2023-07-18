import styles from './Post.module.css';
import profilePicture from '../../Assets/profile.jpg';

function Post() {
    return (
        <div className={styles.post}>
            <div className={styles.header}>
                <div className={styles.headerLeft}>
                    <div className={styles.headerLeftLeft}>
                        <img src={profilePicture} className={styles.profilePicture} />
                    </div>
                    <div className={styles.headerLeftRight}>
                        <p className={styles.headerName}>Carlos Macario</p>
                        <p className={styles.headerTitle}>Founder of Sphere</p>
                    </div>
                </div>
                <div className={styles.headerRight}>
                    <p className={styles.headerDate}>24 mins</p>
                </div>
            </div>
            <div className={styles.body}>
                <p className={styles.bodyHeader}>Should I upload resume as PDF or Docx?</p>
                <pre className={styles.bodyText}>{`Looking for some pointers on submitting my resume!`}</pre>
                {/* <img src={profilePicture} className={styles.postPicture} /> */}
            </div>
            <div className={styles.footer}>
                <div className={styles.footerButton0}>
                    <div className={styles.iconButton0}>
                        <i className={`${styles.footerButtonIcon0} fa-solid fa-caret-up`}></i>
                    </div>
                    <p className={styles.footerButtonText0}>123</p>
                    <div className={styles.iconButton1}>
                        <i className={`${styles.footerButtonIcon0} fa-solid fa-caret-down`}></i>
                    </div>
                </div>
                <div className={styles.footerButton}>
                    <i className={`${styles.footerButtonIcon} fa-solid fa-comment`}></i>
                    <p className={styles.footerButtonText}>21</p>
                </div>
            </div>
        </div>
    )
}

export default Post;
