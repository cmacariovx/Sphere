import MainHeader from '../Components/MainHeader';
import ProfileBody from '../Components/Profile/ProfileBody';
import styles from './Profile.module.css';

function Profile() {
    return (
        <div className={styles.profile}>
            <MainHeader />
            <ProfileBody />
        </div>
    )
}

export default Profile;
