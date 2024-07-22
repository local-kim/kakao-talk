import { Routes, Route } from 'react-router-dom';
import RoomList from './pages/RoomList';
import Room from './pages/Room';

function App() {
  return (
    <Routes>
      <Route path='/rooms' element={<RoomList/>} />
      <Route path='/room/:roomId' element={<Room/>} />
    </Routes>
  );
}

export default App;
