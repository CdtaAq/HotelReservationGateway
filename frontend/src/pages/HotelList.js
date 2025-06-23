import React, { useEffect, useState } from 'react';
import PriceSlider from '../components/PriceSlider';
import AmenitiesFilter from '../components/AmenitiesFilter';

function HotelList() {
  const [hotels, setHotels] = useState([]);
  const [priceRange, setPriceRange] = useState([50, 500]); // Default range
  const [filters, setFilters] = useState({});

  useEffect(() => {
    const params = {
      minPrice: priceRange[0],
      maxPrice: priceRange[1],
      ...filters,
    };

    fetch('/api/hotels/search', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token'),
      },
      body: JSON.stringify(params),
    })
      .then(res => res.json())
      .then(data => setHotels(data));
  }, [priceRange, filters]);

  return (
    <div className="p-4 max-w-4xl mx-auto">
      <h2 className="text-2xl font-bold mb-4">Available Hotels</h2>

      <PriceSlider
        min={50}
        max={500}
        priceRange={priceRange}
        setPriceRange={setPriceRange}
      />

      <AmenitiesFilter filters={filters} setFilters={setFilters} />

      <ul className="space-y-4 mt-4">
        {hotels.map(hotel => (
          <li key={hotel.id} className="border p-4 rounded shadow">
            <h3 className="text-lg font-bold">{hotel.name}</h3>
            <p>{hotel.location} • ${hotel.price} • {hotel.rating}⭐</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default HotelList;
