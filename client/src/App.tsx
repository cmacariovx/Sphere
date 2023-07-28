import { Route, Routes } from 'react-router-dom';
import './App.css';
import './global.css';
import HomeFeed from './Pages/HomeFeed';
import Profile from './Pages/Profile';
import Circle from './Pages/Circle';
import { useDispatch, useSelector } from 'react-redux';
import { setNeedsAuth } from './Redux/slices/authSlice';
import { RootState } from './Redux/store';
import { useEffect, useState } from 'react';
import { fetchBasicUserData } from './Api/fetchBasicUserData';
import ErrorModal from './Components/Error/ErrorModal';
import { logoutMain } from './Api/logout';
import { errorInfo } from './interfaces';

function App() {
    const [error, setError] = useState<errorInfo | null>(null);

    const dispatch = useDispatch();
    const isLoggedIn = useSelector((state: RootState) => state.auth.isLoggedIn);
    const userData = useSelector((state: RootState) => state.auth.userData);

    useEffect(() => {
        const na: string | null = localStorage.getItem('na');
        if (na != "0" && na != "1") localStorage.setItem('na', "0");
        const needsAuth: boolean = localStorage.getItem('na') == "0" ? false : true;
        dispatch(setNeedsAuth(needsAuth));

        if (!needsAuth && !isLoggedIn) fetchBasicUserData(dispatch, setError);
    }, [dispatch, isLoggedIn]);

    return (
        <div className="app">
            {error && <ErrorModal message={error.message} action={error.action} closeModal={() => setError(null)}/>}
            <Routes>
                <Route path="/home" element={<HomeFeed />} />
                <Route path="/profile/:profileId" element={<Profile />} />
                <Route path="/circle" element={<Circle />} />
            </Routes>
        </div>
    );
}

export default App;
