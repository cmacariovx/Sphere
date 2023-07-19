import { Route, Routes } from 'react-router-dom';
import './App.css';
import './global.css';
import HomeFeed from './Pages/HomeFeed';
import Profile from './Pages/Profile';
import Circle from './Pages/Circle';

function App() {
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
