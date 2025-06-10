// src/pages/PaymentSuccess.js
export default function PaymentSuccess() {
  return (
    <div className="p-6 text-center text-green-600 font-bold">
      Payment successful! Your booking is confirmed.
    </div>
  );
}

// src/pages/PaymentCancel.js
export default function PaymentCancel() {
  return (
    <div className="p-6 text-center text-red-600 font-bold">
      Payment was canceled. Please try again.
    </div>
  );
}
