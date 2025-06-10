// src/pages/BookingPage.js
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function BookingPage() {
  const { hotelId } = useParams();
  const navigate = useNavigate();

  const [hotel, setHotel] = useState(null);
  const [guests, setGuests] = useState(1);
  const [date, setDate] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchHotel = async () => {
      const res = await fetch(`/api/hotels/${hotelId}`, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token'),
        },
      });
      const data = await res.json();
      setHotel(data);
    };
    fetchHotel();
  }, [hotelId]);

  const handleBooking = async () => {
    if (!date) {
      setError('Please select a valid booking date.');
      return;
    }
    setError('');
    try {
      const res = await fetch('/api/bookings', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + localStorage.getItem('token'),
        },
        body: JSON.stringify({ hotelId, guests, date }),
      });

      if (res.ok) {
        const booking = await res.json();
        navigate(`/pay/${booking.id}`);
      } else {
        const err = await res.json();
        setError(err.message || 'Booking failed');
      }
    } catch (err) {
      console.error(err);
      setError('Something went wrong.');
    }
  };

  if (!hotel) return <div className="p-4">Loading hotel details...</div>;

  return (
    <div className="p-4 max-w-lg mx-auto">
      <h2 className="text-2xl font-bold mb-4">{hotel.name}</h2>
      <p className="mb-2 text-gray-600">{hotel.location}</p>
      <p className="mb-4">Price: ${hotel.price} per night</p>

      <div className="mb-2">
        <label className="block mb-1 font-medium">Guests</label>
        <input
          type="number"
          min="1"
          max="10"
          value={guests}
          onChange={(e) => setGuests(e.target.value)}
          className="border p-2 w-full"
        />
      </div>

      <div className="mb-4">
        <label className="block mb-1 font-medium">Date</label>
        <input
          type="date"
          value={date}
          onChange={(e) => setDate(e.target.value)}
          className="border p-2 w-full"
        />
      </div>

      {error && <div className="text-red-500 mb-3">{error}</div>}

      <button
        onClick={handleBooking}
        className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
      >
        Confirm Booking & Pay
      </button>
    </div>
  );
}

export default BookingPage;
