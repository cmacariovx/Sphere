import styles from './Post.module.css';
import profilePicture from '../../Assets/profile.jpg';
import test1 from '../../Assets/test1.png';
import test2 from '../../Assets/test2.png';
import Comment from './Comment';
import { useState } from 'react';

function Post() {
    const [showComments, setShowComments] = useState(false);
    const [showReport, setShowReport] = useState(false);

    return (
        <div className={styles.post}>
            <div className={styles.header}>
                <div className={styles.headerLeft}>
                    <div className={styles.headerLeftLeft}>
                        <img src={profilePicture} className={styles.profilePicture} />
                    </div>
                    <div className={styles.headerLeftRight}>
                        <p className={styles.headerName}>Carlos Macario</p>
                        <p className={styles.headerTitle}>Founder of Sphere - Software Engineering</p>
                    </div>
                </div>
                <div className={styles.headerRight}>
                    <p className={styles.headerDate}>24 mins</p>
                </div>
            </div>
            <div className={styles.body}>
                <p className={styles.bodyHeader}>Should I upload resume as PDF or Docx?</p>
                <pre className={styles.bodyText}>{`Looking for some pointers on submitting my resume!`}</pre>
                {/* <img src={test1} className={styles.postPicture} /> */}
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
                <div className={styles.footerButton} onClick={() => setShowComments(!showComments)}>
                    <i className={`${styles.footerButtonIcon} fa-solid fa-comment`}></i>
                    <p className={styles.footerButtonText}>21</p>
                </div>
                <div className={styles.footerButton}>
                    <i className={`${styles.footerButtonIcon} fa-solid fa-share`}></i>
                    <p className={styles.footerButtonText}>Share</p>
                </div>
                {!showReport && <div className={styles.footerButton1} onClick={() => setShowReport(!showReport)}>
                    <i className={`${styles.footerButtonIcon} fa-solid fa-ellipsis`}></i>
                </div>}
                {showReport && <div className={styles.footerButton1}>
                    <i className={`${styles.footerButtonIcon} fa-solid fa-flag`}></i>
                    <p className={styles.footerButtonText}>Report</p>
                </div>}
            </div>
            {showComments && <div className={styles.comments}>
                <Comment />
            </div>}
        </div>
    )
}

export default Post;
