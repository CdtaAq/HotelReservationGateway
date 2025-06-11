import React, { useEffect, useState } from 'react';

function AdminFeedback() {
  const [feedbackList, setFeedbackList] = useState([]);

  const fetchFeedback = async () => {
    const res = await fetch('/api/feedback/all', {
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    });
    const data = await res.json();
    setFeedbackList(data);
  };

  const handleApprove = async (id) => {
    await fetch(`/api/feedback/approve/${id}`, {
      method: 'PUT',
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    });
    fetchFeedback();
  };

  const handleDelete = async (id) => {
    await fetch(`/api/feedback/${id}`, {
      method: 'DELETE',
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    });
    fetchFeedback();
  };

  useEffect(() => {
    fetchFeedback();
  }, []);

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <h2 className="text-2xl font-bold mb-4">Feedback Moderation</h2>
      <ul className="space-y-3">
        {feedbackList.map(f => (
          <li key={f.id} className="border p-4 rounded">
            <p><strong>Hotel:</strong> {f.hotel.name}</p>
            <p><strong>User:</strong> {f.user.username}</p>
            <p><strong>Rating:</strong> {f.rating}</p>
            <p><strong>Comment:</strong> {f.comment}</p>
            <p><strong>Status:</strong> {f.approved ? "✅ Approved" : "⏳ Pending"}</p>
            {!f.approved && (
              <button onClick={() => handleApprove(f.id)} className="mr-2 px-3 py-1 bg-green-600 text-white">Approve</button>
            )}
            <button onClick={() => handleDelete(f.id)} className="px-3 py-1 bg-red-600 text-white">Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AdminFeedback;
