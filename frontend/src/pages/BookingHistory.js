import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';

function BookingHistory() {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    fetch('/api/bookings/my-bookings', {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      }
    })
      .then(res => res.json())
      .then(data => setBookings(data));
  }, []);

  return (
    <div className="p-4 max-w-3xl mx-auto">
      <Navbar />
      <h2 className="text-2xl font-bold mb-4">My Bookings</h2>
      {bookings.length === 0 ? (
        <p>No bookings found.</p>
      ) : (
        <ul className="space-y-3">
          {bookings.map((b, i) => (
            <li key={i} className="border p-4 rounded shadow">
              <h3 className="text-lg font-semibold">{b.hotel.name}</h3>
              <p><strong>Location:</strong> {b.hotel.location}</p>
              <p><strong>Guests:</strong> {b.guests}</p>
              <p><strong>Date:</strong> {b.bookingDate}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default BookingHistory;
