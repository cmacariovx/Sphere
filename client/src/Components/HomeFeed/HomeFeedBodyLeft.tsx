import CreatePost from './CreatePost';
import styles from './HomeFeedBodyLeft.module.css';
import Post from './Post';

function HomeFeedBodyLeft() {
    return (
        <div className={styles.homeFeedBodyLeft}>
            <CreatePost />
            <div className={styles.homePostsBody}>
                <Post />
            </div>
        </div>
    )
}

export default HomeFeedBodyLeft;
