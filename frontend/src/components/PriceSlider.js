import React from 'react';

function PriceSlider({ min, max, priceRange, setPriceRange }) {
  const handleMinChange = (e) => {
    setPriceRange([+e.target.value, priceRange[1]]);
  };

  const handleMaxChange = (e) => {
    setPriceRange([priceRange[0], +e.target.value]);
  };

  return (
    <div className="p-4 border rounded mb-4">
      <h4 className="font-bold mb-2">Filter by Price ($)</h4>
      <div className="flex items-center justify-between mb-2">
        <span>${priceRange[0]}</span>
        <span>${priceRange[1]}</span>
      </div>

      <div className="space-y-2">
        <input
          type="range"
          min={min}
          max={priceRange[1] - 10}
          value={priceRange[0]}
          onChange={handleMinChange}
          className="w-full"
        />
        <input
          type="range"
          min={priceRange[0] + 10}
          max={max}
          value={priceRange[1]}
          onChange={handleMaxChange}
          className="w-full"
        />
      </div>
    </div>
  );
}

export default PriceSlider;
