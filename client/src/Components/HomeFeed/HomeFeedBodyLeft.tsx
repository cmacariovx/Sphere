import CreatePost from './CreatePost';
import styles from './HomeFeedBodyLeft.module.css';

function HomeFeedBodyLeft() {
    return (
        <div className={styles.homeFeedBodyLeft}>
            <CreatePost />
        </div>
    )
}

export default HomeFeedBodyLeft;
