import React from 'react';

function Navbar() {
  const handleLogout = () => {
    localStorage.removeItem('token');
    window.location.href = '/';
  };

  return (
    <div className="flex justify-between items-center p-4 bg-gray-800 text-white">
      <h1 className="text-lg font-bold">Hotel Reservation</h1>
      <button onClick={handleLogout} className="bg-red-500 px-3 py-1 rounded">Logout</button>
    </div>
  );
}

export default Navbar;
