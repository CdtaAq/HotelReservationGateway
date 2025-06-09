// src/components/ChatBox.js
import React, { useState } from 'react';

function ChatBox() {
  const [input, setInput] = useState('');
  const [messages, setMessages] = useState([]);

  const sendMessage = async () => {
    const res = await fetch('/api/chat', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message: input })
    });
    const data = await res.json();
    setMessages([
      ...messages,
      { role: 'user', content: input },
      { role: 'assistant', content: data.content }
    ]);
    setInput('');
  };

  return (
    <div className="fixed bottom-4 right-4 bg-white p-4 border shadow-lg w-80 rounded-xl">
      <h4 className="font-bold mb-2">Hotel Assistant</h4>
      <div className="h-40 overflow-y-auto text-sm mb-2">
        {messages.map((msg, i) => (
          <div key={i}><b>{msg.role}:</b> {msg.content}</div>
        ))}
      </div>
      <input
        className="border p-2 w-full mb-2"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Ask a question..."
      />
      <button
        onClick={sendMessage}
        className="bg-blue-600 text-white px-3 py-1 w-full"
      >
        Send
      </button>
    </div>
  );
}

export default ChatBox;
