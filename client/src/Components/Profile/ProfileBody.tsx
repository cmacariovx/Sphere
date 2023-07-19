import styles from './ProfileBody.module.css';
import seattle from '../../Assets/seattle.png';
import profilePicture from '../../Assets/profile.jpg';
import Post from '../HomeFeed/Post';
import { Fragment, useState } from 'react';

function ProfileBody() {
    const [selectedActivity, setSelectedActivity] = useState(0);
    const [showTalks, setShowTalks] = useState(true);
    const [showCircles, setShowCircles] = useState(true);

    return (
        <Fragment>
            <img src={seattle} className={styles.profileBanner} />
            <div className={styles.profileBody}>
                <img src={profilePicture} className={styles.profilePicture} />
                <div className={styles.profileUpper}>
                    <div className={styles.profileUpperUpper}>
                        <div className={styles.profileStats}>
                            <pre className={styles.profileStat0}>544  </pre>
                            <p className={styles.profileStat1}>Followers</p>
                            <p className={styles.profileDot}>•</p>
                            <pre className={styles.profileStat0}>35  </pre>
                            <p className={styles.profileStat1}>Friends</p>
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
                            <div className={styles.circleNameContainer}>
                                <p className={styles.profileName}>Carlos Macario</p>
                                <div className={styles.verifiedContainer}>
                                    <i className={`${styles.verifiedBadge} fa-solid fa-certificate`}></i>
                                    <i className={`${styles.verifiedCheck} fa-solid fa-check`}></i>
                                </div>
                            </div>
                            <pre className={styles.profileTitle}>Founder of Sphere  •  Software Engineering</pre>
                            <div className={styles.mutualFriend}>
                                <img src={profilePicture} className={styles.mutualFriendPicture} />
                                <p className={styles.mutualFriendName}>5 Mutual Friends: Kevin Valdez, Carlos Macario, and 3 others.</p>
                            </div>
                        </div>
                        <div className={styles.profileUpperLowerRight}>
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
                <div className={styles.profileLower}>
                    <div className={styles.profileLowerLeft}>
                        <div className={styles.profileAbout}>
                            <div className={styles.profileAboutHeader}>
                                <p className={styles.aboutTitle}>About</p>
                                <div className={styles.aboutLinkContainer}>
                                    <p className={styles.aboutLink}>GitHub</p>
                                    <i className={`${styles.aboutIcon} fa-solid fa-arrow-up-right-from-square`}></i>
                                </div>
                            </div>
                            <div className={styles.profileAboutBody}>
                                <pre className={styles.profileAboutText}>Carlos is a Full Stack Software Engineer with a demonstrated portfolio of 5 performant and scalable applications, utilizing both front-end and back-end technologies, along with data structures and algorithms to optimize performance and user experience.</pre>
                            </div>
                        </div>
                        <div className={styles.profilePosts}>
                            <p className={styles.activityText}>Activity</p>
                            <div className={styles.profilePostsOptions}>
                                <p className={selectedActivity == 0 ? styles.profilePostsOption1 : styles.profilePostsOption} onClick={() => {if (selectedActivity != 0) setSelectedActivity(0)}}>Posts</p>
                                <p className={selectedActivity == 1 ? styles.profilePostsOption1 : styles.profilePostsOption} onClick={() => {if (selectedActivity != 1) setSelectedActivity(1)}}>Comments</p>
                                <p className={selectedActivity == 2 ? styles.profilePostsOption1 : styles.profilePostsOption} onClick={() => {if (selectedActivity != 2) setSelectedActivity(2)}}>Upvoted</p>
                            </div>
                            <div className={styles.profilePostsBody}>
                                <Post />
                                <Post />
                                <Post />
                                <Post />
                            </div>
                        </div>
                    </div>
                    <div className={styles.profileLowerRight}>
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
                            <div className={styles.circlesToggle} onClick={() => setShowCircles(!showCircles)}>
                                <p className={styles.circlesTitle}>Circles</p>
                                <i className={`${styles.circlesIcon} fa-solid ${showCircles ? 'fa-chevron-down' : 'fa-chevron-up'}`}></i>
                            </div>
                            {showCircles && <div className={styles.circlesList}>
                                <div className={styles.circlesListItem}>
                                    <img src={profilePicture} className={styles.circlesListItemPicture} />
                                    <p className={styles.circlesListItemText}>Software Engineering</p>
                                </div>
                                <div className={styles.circlesListItem}>
                                    <img src={profilePicture} className={styles.circlesListItemPicture} />
                                    <p className={styles.circlesListItemText}>Carlos' Private Group</p>
                                    <i className={`${styles.circlesLock} fa-solid fa-lock`}></i>
                                </div>
                                <div className={styles.circlesListItem}>
                                    <img src={profilePicture} className={styles.circlesListItemPicture} />
                                    <p className={styles.circlesListItemText}>Technology</p>
                                </div>
                            </div>}
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}

export default ProfileBody;
