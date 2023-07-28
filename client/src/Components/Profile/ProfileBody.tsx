import styles from './ProfileBody.module.css';
import seattle from '../../Assets/seattle.png';
import profilePicture from '../../Assets/profile.jpg';
import Post from '../HomeFeed/Post';
import { Fragment, useEffect, useState } from 'react';
import { MainUserData } from '../../interfaces';

function ProfileBody(props: {userData: MainUserData | null}) {
    const [selectedActivity, setSelectedActivity] = useState(0);
    const [showTalks, setShowTalks] = useState(true);
    const [showCircles, setShowCircles] = useState(true);

    const [postsCount, setPostsCount] = useState(0);
    const [userName, setUserName] = useState("--");
    const [verified, setVerified] = useState(false);
    const [title, setTitle] = useState("");
    const [mainInterest, setMainInterest] = useState("");
    const [upvotesCount, setUpvotesCount] = useState(0);
    const [about, setAbout] = useState("");
    const [circleIds, setCircleIds] = useState<string[]>([]);
    const [postIds, setPostIds] = useState<string[]>([]);
    const [commentIds, setCommentIds] = useState<string[]>([]);
    const [upvotedIds, setUpvotedIds] = useState<string[]>([]);

    useEffect(() => {
        if (props.userData != null) {
            setPostsCount(props.userData.activity.posts.postIds.length);
            setUserName(capWord(props.userData.firstName) + " " + capWord(props.userData.lastName));
            setVerified(props.userData.verification.verified);
            setTitle(props.userData.title);
            setMainInterest(props.userData.interests.mainInterest);
            setUpvotesCount(props.userData.activity.posts.totalUpvotes);
            setAbout(props.userData.about);
            setCircleIds(props.userData.interests.circleIds);
            setPostIds(props.userData.activity.posts.postIds);
            setCommentIds(props.userData.activity.commentIds);
            setUpvotedIds(props.userData.activity.upvotedIds);
        }
    }, [props.userData]);

    function capWord(word: string) {
        return word.charAt(0).toUpperCase() + word.slice(1);
    }

    return (
        <Fragment>
            <img src={seattle} className={styles.profileBanner} />
            <div className={styles.profileBody}>
                <img src={profilePicture} className={styles.profilePicture} />
                <div className={styles.profileUpper}>
                    <div className={styles.profileUpperUpper}>
                        <div className={styles.profileStats}>
                            <p className={styles.profileStat0}>{"Neo4j"}</p>
                            <p className={styles.profileStat1}>Followers</p>
                            <p className={styles.profileDot}>•</p>
                            <p className={styles.profileStat0}>{"Neo4j"}</p>
                            <p className={styles.profileStat1}>Friends</p>
                            <p className={styles.profileDot}>•</p>
                            <p className={styles.profileStat0}>{postsCount}</p>
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
                                <p className={styles.profileName}>{userName}</p>
                                {verified && <div className={styles.verifiedContainer}>
                                    <i className={`${styles.verifiedBadge} fa-solid fa-certificate`}></i>
                                    <i className={`${styles.verifiedCheck} fa-solid fa-check`}></i>
                                </div>}
                            </div>
                            <pre className={styles.profileTitle}>{title + "  •  " + mainInterest}</pre>
                            <div className={styles.mutualFriend}>
                                <img src={profilePicture} className={styles.mutualFriendPicture} />
                                <p className={styles.mutualFriendName}>5 Mutual Friends: Kevin Valdez, Carlos Macario, and 3 others.</p>
                            </div>
                        </div>
                        <div className={styles.profileUpperLowerRight}>
                            <div className={styles.lowerRightStat}>
                                <i className={`${styles.lowerRightStatIcon1} fa-solid fa-caret-up`}></i>
                                <p className={styles.lowerRightStatText}>{upvotesCount + " Upvotes"}</p>
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
                                <pre className={styles.profileAboutText}>{about}</pre>
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
