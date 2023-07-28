import { useParams } from 'react-router-dom';
import MainHeader from '../Components/MainHeader';
import ProfileBody from '../Components/Profile/ProfileBody';
import styles from './Profile.module.css';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../Redux/store';
import { MainUserData, errorInfo } from '../interfaces';
import { logoutMain } from '../Api/logout';
import { fetchBasicUserData } from '../Api/fetchBasicUserData';
import ErrorModal from '../Components/Error/ErrorModal';

function Profile() {
    const [error, setError] = useState<errorInfo | null>(null);
    const [profileData, setProfileData] = useState<MainUserData | null>(null);

    const { profileId } = useParams();
    const userAccessToken = useSelector((state: RootState) => state.auth.accessToken);

    const dispatch = useDispatch();

    useEffect(() => {
        if (profileId && userAccessToken) fetchProfileData(profileId);
    }, [profileId, userAccessToken]);

    async function fetchProfileData(profileId: string) {
        async function fetchMain(): Promise<Response> {
            const response = await fetch("/api/user/fetchProfileData", {
                method: "POST",
                body: JSON.stringify({
                    profileId: profileId,
                }),
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + userAccessToken,
                }
            });

            return response;
        }

        let response = await fetchMain();

        if (response.status == 401) {
            await fetchBasicUserData(dispatch, setError);
            response = await fetchMain();
        }
        else if (response.status == 400) {
            const error: errorInfo = {
                message: "Your session has expired, please log back in to continue.",
                statusCode: response.status,
                action: () => logoutMain(dispatch),
            }
            setError(error);
            return null;
        }
        else if (response.status != 200) {
            const error: errorInfo = {
                message: "Internal server error, please try again.",
                statusCode: response.status,
                action: () => null,
            }
            setError(error);
            return null;
        }

        if (!response.ok) {
            const error: errorInfo = {
                message: "Your session has expired, please log back in to continue.",
                statusCode: response.status,
                action: () => logoutMain(dispatch),
            }
            setError(error);
            return null;
        }

        const data: MainUserData = await response.json();
        setProfileData(data);
    }

    return (
        <div className={styles.profile}>
            {error && <ErrorModal message={error.message} action={error.action} closeModal={() => setError(null)}/>}
            <MainHeader />
            <ProfileBody userData={profileData}/>
        </div>
    )
}

export default Profile;
