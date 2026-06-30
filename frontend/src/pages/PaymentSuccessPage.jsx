import { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";

function PaymentSuccessPage() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState("Completing payment...");
  const [done, setDone] = useState(false);

  useEffect(() => {
    const capturePayment = async () => {
      try {
        const paypalOrderId = searchParams.get("token");
        const orderId = localStorage.getItem("payment_order_id");
        const token = localStorage.getItem("accessToken");

        if (!paypalOrderId || !orderId) {
          setMessage("Missing PayPal order information.");
          setLoading(false);
          return;
        }

        const response = await axios.post(
          "http://localhost:8080/api/payments/paypal/capture",
          {
            orderId: Number(orderId),
            paypalOrderId: paypalOrderId,
          },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        console.log("PAYPAL CAPTURE RESPONSE:", response.data);

        localStorage.removeItem("paypal_order_id");
        localStorage.removeItem("payment_order_id");

        setMessage("Payment completed successfully.");
        setDone(true);
      } catch (error) {
        console.error(error);
        setMessage(error.response?.data || "Failed to complete payment.");
      } finally {
        setLoading(false);
      }
    };

    capturePayment();
  }, [searchParams]);

  return (
    <div style={styles.page}>
      <div style={styles.card}>
        <p style={styles.badge}>Buyer Payment</p>
        <h1 style={styles.title}>Payment Success</h1>
        <p style={styles.message}>{message}</p>

        {loading ? (
          <p style={styles.info}>Please wait...</p>
        ) : (
          <div style={styles.actions}>
            {done && (
              <>
                <button
                  style={styles.primaryButton}
                  onClick={() => navigate("/user/orders/current")}
                >
                  Go to Current Orders
                </button>

                <button
                  style={styles.secondaryButton}
                  onClick={() => navigate("/user/orders/history")}
                >
                  Go to Order History
                </button>
              </>
            )}

            {!done && (
              <button
                style={styles.secondaryButton}
                onClick={() => navigate("/user/cart")}
              >
                Back to Cart
              </button>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

const styles = {
  page: {
    minHeight: "100vh",
    background: "linear-gradient(135deg, #0b1020, #111827)",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    padding: "24px",
  },
  card: {
    width: "100%",
    maxWidth: "760px",
    backgroundColor: "#111827",
    border: "1px solid #1f2937",
    borderRadius: "20px",
    padding: "32px",
    boxShadow: "0 20px 50px rgba(0,0,0,0.25)",
  },
  badge: {
    color: "#60a5fa",
    fontSize: "13px",
    marginBottom: "10px",
    letterSpacing: "0.5px",
  },
  title: {
    color: "#f9fafb",
    fontSize: "38px",
    fontWeight: "700",
    marginBottom: "14px",
  },
  message: {
    color: "#d1d5db",
    fontSize: "18px",
    marginBottom: "20px",
  },
  info: {
    color: "#9ca3af",
    fontSize: "15px",
  },
  actions: {
    display: "flex",
    gap: "12px",
    flexWrap: "wrap",
    marginTop: "10px",
  },
  primaryButton: {
    background: "linear-gradient(135deg, #2563eb, #3b82f6)",
    color: "#ffffff",
    border: "none",
    padding: "12px 18px",
    borderRadius: "12px",
    fontSize: "15px",
    fontWeight: "600",
    cursor: "pointer",
  },
  secondaryButton: {
    backgroundColor: "#1f2937",
    color: "#ffffff",
    border: "1px solid #374151",
    padding: "12px 18px",
    borderRadius: "12px",
    fontSize: "15px",
    fontWeight: "600",
    cursor: "pointer",
  },
};

export default PaymentSuccessPage;