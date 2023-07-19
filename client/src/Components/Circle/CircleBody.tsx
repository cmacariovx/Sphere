import styles from './CircleBody.module.css';
import seattle from '../../Assets/seattle.png';
import circlePicture from '../../Assets/profile.jpg';
import Post from '../HomeFeed/Post';
import { Fragment, useState } from 'react';

function CircleBody() {
    const [selectedActivity, setSelectedActivity] = useState(0);
    const [showTalks, setShowTalks] = useState(true);
    const [showRules, setShowRules] = useState(true);

    return (
        <Fragment>
            <img src={seattle} className={styles.circleBanner} />
            <div className={styles.circleBody}>
                <img src={circlePicture} className={styles.circlePicture} />
                <div className={styles.circleUpper}>
                    <div className={styles.circleUpperUpper}>
                        <div className={styles.circleStats}>
                            <pre className={styles.circleStat0}>5.4k  </pre>
                            <p className={styles.circleStat1}>Members</p>
                            <p className={styles.circleDot}>•</p>
                            <pre className={styles.circleStat0}>12  </pre>
                            <p className={styles.circleStat1}>Posts</p>
                        </div>
                        <div className={styles.circleButtons}>
                            <div className={styles.messageButton}>
                                <i className={`${styles.messageIcon} fa-solid fa-comment`}></i>
                                <p className={styles.messageText}>Message</p>
                            </div>
                            <div className={styles.connectButton}>
                                <i className={`${styles.connectIcon} fa-solid fa-plus`}></i>
                                <p className={styles.connectText}>Join</p>
                            </div>
                        </div>
                    </div>
                    <div className={styles.circleUpperLower}>
                        <div className={styles.circleUpperLowerLeft}>
                            <div className={styles.circleNameContainer}>
                                <p className={styles.circleName}>Software Engineering</p>
                                <div className={styles.verifiedContainer}>
                                    <i className={`${styles.verifiedBadge} fa-solid fa-certificate`}></i>
                                    <i className={`${styles.verifiedCheck} fa-solid fa-check`}></i>
                                </div>
                                <i className={`${styles.circlesLock} fa-solid fa-lock`}></i>
                            </div>
                            <pre className={styles.circleTitle}>Welcome to SWE!</pre>
                            <div className={styles.mutualFriend}>
                                <img src={circlePicture} className={styles.mutualFriendPicture} />
                                <p className={styles.mutualFriendName}>5 Friends: Kevin Valdez, Carlos Macario, and 3 others.</p>
                            </div>
                        </div>
                        <div className={styles.circleUpperLowerRight}>
                            <div className={styles.lowerRightStat}>
                                <i className={`${styles.lowerRightStatIcon1} fa-solid fa-caret-up`}></i>
                                <p className={styles.lowerRightStatText}>3,061 Upvotes</p>
                            </div>
                            {/* <div className={styles.lowerRightStat}>
                                <i className={`${styles.lowerRightStatIcon2} fa-solid fa-check`}></i>
                                <p className={styles.lowerRightStatText}>174 Best Answers</p>
                            </div> */}
                        </div>
                    </div>
                </div>
                <div className={styles.circleLower}>
                    <div className={styles.circleLowerLeft}>
                        <div className={styles.circleAbout}>
                            <div className={styles.circleAboutHeader}>
                                <p className={styles.aboutTitle}>About</p>
                                <div className={styles.aboutLinkContainer}>
                                    <p className={styles.aboutLink}>GitHub</p>
                                    <i className={`${styles.aboutIcon} fa-solid fa-arrow-up-right-from-square`}></i>
                                </div>
                            </div>
                            <div className={styles.circleAboutBody}>
                                <pre className={styles.circleAboutText}>Carlos is a Full Stack Software Engineer with a demonstrated portfolio of 5 performant and scalable applications, utilizing both front-end and back-end technologies, along with data structures and algorithms to optimize performance and user experience.</pre>
                            </div>
                        </div>
                        <div className={styles.circlePosts}>
                            <p className={styles.activityText}>Activity</p>
                            <div className={styles.circlePostsOptions}>
                                <p className={selectedActivity == 0 ? styles.circlePostsOption1 : styles.circlePostsOption} onClick={() => {if (selectedActivity != 0) setSelectedActivity(0)}}>Posts</p>
                                <p className={selectedActivity == 1 ? styles.circlePostsOption1 : styles.circlePostsOption} onClick={() => {if (selectedActivity != 1) setSelectedActivity(1)}}>Members</p>
                            </div>
                            <div className={styles.circlePostsBody}>
                                <Post />
                                <Post />
                                <Post />
                                <Post />
                            </div>
                        </div>
                    </div>
                    <div className={styles.circleLowerRight}>
                        <div className={styles.circles}>
                            <div className={styles.circlesToggle} onClick={() => setShowTalks(!showTalks)}>
                                <p className={styles.circlesTitle}>Mainly Talks About</p>
                                <i className={`${styles.circlesIcon} fa-solid ${showTalks ? 'fa-chevron-down' : 'fa-chevron-up'}`}></i>
                            </div>
                            {showTalks && <div className={styles.circlesList}>
                                <div className={styles.circlesListItem}>
                                    <p className={styles.circlesListItemText}>#softwareengineering</p>
                                </div>
                                <div className={styles.circlesListItem}>
                                    <p className={styles.circlesListItemText}>#resumes</p>
                                </div>
                                <div className={styles.circlesListItem}>
                                    <p className={styles.circlesListItemText}>#bayarea</p>
                                </div>
                            </div>}
                        </div>
                        <div className={styles.circles}>
                            <div className={styles.circlesToggle} onClick={() => setShowRules(!showRules)}>
                                <p className={styles.circlesTitle}>Rules</p>
                                <i className={`${styles.circlesIcon} fa-solid ${showRules ? 'fa-chevron-down' : 'fa-chevron-up'}`}></i>
                            </div>
                            {showRules && <div className={styles.circlesList}>
                                <div className={styles.circlesListItem}>
                                    <p className={styles.circlesListItemText}>1. Software Engineering</p>
                                </div>
                            </div>}
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}

export default CircleBody;
