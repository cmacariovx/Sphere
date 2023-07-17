import HomeFeedBody from '../Components/HomeFeed/HomeFeedBody';
import MainHeader from '../Components/MainHeader';
import styles from './HomeFeed.module.css';

function HomeFeed() {
    return (
        <div className={styles.homeFeed}>
            <MainHeader />
            <HomeFeedBody />
        </div>
    )
}

export default HomeFeed;
