import styles from './HomeFeedBody.module.css';
import HomeFeedBodyLeft from './HomeFeedBodyLeft';
import HomeFeedBodyRight from './HomeFeedBodyRight';

function HomeFeedBody() {
    return (
        <div className={styles.homeFeedBody}>
            <HomeFeedBodyLeft />
            <HomeFeedBodyRight />
        </div>
    )
}

export default HomeFeedBody;
