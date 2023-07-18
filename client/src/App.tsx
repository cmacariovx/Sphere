import { Route, Routes } from 'react-router-dom';
import './App.css';
import './global.css';
import HomeFeed from './Pages/HomeFeed';
import Profile from './Pages/Profile';

function App() {
    return (
        <div className="app">
            <Routes>
                <Route path="/home" element={<HomeFeed />} />
                <Route path="/profile" element={<Profile />} />
            </Routes>
        </div>
    );
}

export default App;
