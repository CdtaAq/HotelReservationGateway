// src/pages/PaymentPage.js
import React, { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { loadStripe } from '@stripe/stripe-js';

const stripePromise = loadStripe('pk_test_YOUR_PUBLIC_STRIPE_KEY'); // replace with your key

function PaymentPage() {
  const { bookingId } = useParams();

  useEffect(() => {
    const initiatePayment = async () => {
      const res = await fetch('/api/payment/create-checkout-session', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + localStorage.getItem('token')
        },
        body: JSON.stringify({ bookingId })
      });

      const session = await res.json();
      const stripe = await stripePromise;
      await stripe.redirectToCheckout({ sessionId: session.id });
    };

    initiatePayment();
  }, [bookingId]);

  return (
    <div className="p-6 text-center">
      <h2 className="text-xl font-bold mb-4">Redirecting to payment...</h2>
      <p>Please wait while we process your request.</p>
    </div>
  );
}

export default PaymentPage;
