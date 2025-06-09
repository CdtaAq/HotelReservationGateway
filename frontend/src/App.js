import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import HotelList from './pages/HotelList';
import BookingPage from './pages/BookingPage';
import FeedbackPage from './pages/FeedbackPage';
import ChatBox from './components/ChatBox';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/hotels" element={<HotelList />} />
        <Route path="/book/:hotelId" element={<BookingPage />} />
        <Route path="/feedback/:hotelId" element={<FeedbackPage />} />
      </Routes>
      <ChatBox />
    </Router>
  );
}
