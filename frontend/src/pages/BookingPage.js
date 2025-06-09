import React, { useState } from 'react';
import { useParams } from 'react-router-dom';

function BookingPage() {
  const { hotelId } = useParams();
  const [guests, setGuests] = useState(1);
  const [date, setDate] = useState('');

  const handleBooking = async () => {
    await fetch('/api/bookings', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      },
      body: JSON.stringify({ hotelId, guests, date })
    });
    alert('Booking confirmed!');
  };

  return (
    <div className="p-4 max-w-md mx-auto">
      <h2 className="text-xl font-bold mb-4">Book Hotel #{hotelId}</h2>
      <input className="border p-2 w-full mb-2" type="number" value={guests} onChange={(e) => setGuests(e.target.value)} placeholder="Number of guests" />
      <input className="border p-2 w-full mb-2" type="date" value={date} onChange={(e) => setDate(e.target.value)} />
      <button onClick={handleBooking} className="bg-green-500 text-white px-4 py-2">Confirm Booking</button>
    </div>
  );
}

export default BookingPage;
