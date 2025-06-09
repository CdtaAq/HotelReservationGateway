import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

function HotelList() {
  const [hotels, setHotels] = useState([]);

  useEffect(() => {
    fetch('/api/hotels', {
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    })
      .then(res => res.json())
      .then(data => setHotels(data));
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-2xl font-bold mb-4">Available Hotels</h2>
      <ul className="space-y-2">
        {hotels.map(hotel => (
          <li key={hotel.id} className="border p-4 rounded">
            <h3 className="text-lg font-semibold">{hotel.name}</h3>
            <p>{hotel.location}</p>
            <Link className="text-blue-500" to={`/book/${hotel.id}`}>Book Now</Link> | <Link to={`/feedback/${hotel.id}`}>Feedback</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default HotelList;
