import React, { useState } from 'react';
import { useParams } from 'react-router-dom';

function FeedbackPage() {
  const { hotelId } = useParams();
  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState('');

  const submitFeedback = async () => {
    await fetch(`/api/hotels/${hotelId}/feedback`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      },
      body: JSON.stringify({ rating, comment })
    });
    alert('Thank you for your feedback!');
  };

  return (
    <div className="p-4 max-w-md mx-auto">
      <h2 className="text-xl font-bold mb-4">Rate Hotel #{hotelId}</h2>
      <select value={rating} onChange={(e) => setRating(e.target.value)} className="border p-2 w-full mb-2">
        {[1,2,3,4,5].map(num => <option key={num} value={num}>{num}</option>)}
      </select>
      <textarea className="border p-2 w-full mb-2" placeholder="Comments" value={comment} onChange={(e) => setComment(e.target.value)} />
      <button onClick={submitFeedback} className="bg-blue-500 text-white px-4 py-2">Submit</button>
    </div>
  );
}

export default FeedbackPage;
