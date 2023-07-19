import MainHeader from '../Components/MainHeader';
import CircleBody from '../Components/Circle/CircleBody';
import styles from './Circle.module.css';

function Circle() {
    return (
        <div className={styles.circle}>
            <MainHeader />
            <CircleBody />
        </div>
    )
}

export default Circle;
