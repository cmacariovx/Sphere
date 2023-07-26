import { Route, Routes } from 'react-router-dom';
import './App.css';
import './global.css';
import HomeFeed from './Pages/HomeFeed';
import Profile from './Pages/Profile';
import Circle from './Pages/Circle';
import { useFetchUserData } from './Hooks/useFetchUserData';
import { useDispatch, useSelector } from 'react-redux';
import { logout } from './Redux/slices/authSlice';
import { RootState } from './Redux/store';
import { useEffect } from 'react';

function App() {
    const { isLoading, error } = useFetchUserData();
    const dispatch = useDispatch();

    if (error) {
        return (
            <div>
                Error: {error}
                <button onClick={() => dispatch(logout())}>Logout</button>
            </div>
        );
    }

    return (
        <div className="app">
            <Routes>
                <Route path="/home" element={<HomeFeed />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/circle" element={<Circle />} />
            </Routes>
        </div>
    );
}

export default App;
