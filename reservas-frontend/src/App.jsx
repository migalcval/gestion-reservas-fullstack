import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/NavigationBar';
import Home from './pages/Home';
import UserPanel from './pages/UserPanel';
import AdminPanel from './pages/AdminPanel';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <div style={{ padding: 20 }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/mis-reservas" element={<UserPanel />} />
          <Route path="/admin" element={<AdminPanel />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;