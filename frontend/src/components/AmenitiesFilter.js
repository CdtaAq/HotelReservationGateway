import React from 'react';

function AmenitiesFilter({ filters, setFilters }) {
  const handleChange = (e) => {
    setFilters(prev => ({
      ...prev,
      [e.target.name]: e.target.checked
    }));
  };

  return (
    <div className="p-4 border rounded">
      <h4 className="font-bold mb-2">Filter by Amenities</h4>
      {["wifi", "pool", "parking", "breakfast", "airConditioning"].map(item => (
        <label key={item} className="block mb-1">
          <input
            type="checkbox"
            name={item}
            checked={filters[item] || false}
            onChange={handleChange}
            className="mr-2"
          />
          {item.charAt(0).toUpperCase() + item.slice(1)}
        </label>
      ))}
    </div>
  );
}

export default AmenitiesFilter;
