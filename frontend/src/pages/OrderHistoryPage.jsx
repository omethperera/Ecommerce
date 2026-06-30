import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getOrderHistory } from "../api/orderApi";

function OrderHistoryPage() {
  const [orders, setOrders] = useState([]);
  const userId = localStorage.getItem("userId");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await getOrderHistory(userId);
        setOrders(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    if (userId) {
      fetchOrders();
    }
  }, [userId]);

  return (
    <div style={styles.page}>
      <div style={styles.container}>
        <div style={styles.headerRow}>
          <div>
            <p style={styles.badge}>Buyer Orders</p>
            <h1 style={styles.title}>Order History</h1>
            <p style={styles.subtitle}>
              These are your dispatched orders. You can review purchased items from here later.
            </p>
          </div>

          <button style={styles.backButton} onClick={() => navigate("/user-dashboard")}>
            Back to Dashboard
          </button>
        </div>

        {orders.length === 0 ? (
          <div style={styles.emptyCard}>
            <h3 style={styles.emptyTitle}>No dispatched orders yet</h3>
            <p style={styles.emptyText}>
              Once a seller dispatches an order, it will appear here.
            </p>
          </div>
        ) : (
          <div style={styles.ordersColumn}>
            {orders.map((order) => (
              <div key={order.orderId} style={styles.orderCard}>
                <div style={styles.orderTop}>
                  <div>
                    <h3 style={styles.orderCode}>{order.orderCode}</h3>
                    <p style={styles.meta}>Total: Rs. {order.totalAmount}</p>
                    <p style={styles.meta}>
                      Shipping Address: {order.shippingAddress || "N/A"}
                    </p>
                    <p style={styles.meta}>
                      Payment Ref: {order.paymentReference || "N/A"}
                    </p>
                  </div>

                  <div style={styles.statusBadge}>{order.orderStatus}</div>
                </div>

                <div style={styles.itemsBox}>
                  {order.items.map((item) => (
                    <div key={item.itemId} style={styles.itemCard}>
                      <img
                        src={item.productImageUrl}
                        alt={item.productName}
                        style={styles.itemImage}
                      />

                      <div style={styles.itemInfo}>
                        <h4 style={styles.itemName}>{item.productName}</h4>
                        <p style={styles.itemMeta}>Quantity: {item.quantity}</p>
                        <p style={styles.itemMeta}>Unit Price: Rs. {item.unitPrice}</p>
                        <p style={styles.itemSubtotal}>Subtotal: Rs. {item.subtotal}</p>
                      </div>

                      <div style={styles.itemActions}>
                        <button style={styles.reviewButton}>Review</button>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            ))}
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
    padding: "24px",
  },
  container: {
    maxWidth: "1180px",
    margin: "0 auto",
  },
  headerRow: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "flex-start",
    gap: "20px",
    flexWrap: "wrap",
    marginBottom: "24px",
  },
  badge: {
    color: "#60a5fa",
    fontSize: "13px",
    marginBottom: "10px",
    letterSpacing: "0.5px",
  },
  title: {
    color: "#f9fafb",
    fontSize: "36px",
    fontWeight: "700",
    marginBottom: "10px",
  },
  subtitle: {
    color: "#9ca3af",
    fontSize: "16px",
    lineHeight: "1.7",
    maxWidth: "720px",
  },
  backButton: {
    backgroundColor: "#1f2937",
    color: "#ffffff",
    border: "1px solid #374151",
    padding: "12px 18px",
    borderRadius: "12px",
    fontSize: "14px",
    fontWeight: "600",
    cursor: "pointer",
  },
  emptyCard: {
    backgroundColor: "#111827",
    border: "1px solid #1f2937",
    borderRadius: "20px",
    padding: "32px",
    textAlign: "center",
  },
  emptyTitle: {
    color: "#f9fafb",
    fontSize: "24px",
    marginBottom: "10px",
  },
  emptyText: {
    color: "#9ca3af",
    fontSize: "15px",
  },
  ordersColumn: {
    display: "flex",
    flexDirection: "column",
    gap: "20px",
  },
  orderCard: {
    backgroundColor: "#111827",
    border: "1px solid #1f2937",
    borderRadius: "20px",
    padding: "22px",
    boxShadow: "0 20px 50px rgba(0,0,0,0.25)",
  },
  orderTop: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "flex-start",
    gap: "16px",
    flexWrap: "wrap",
    marginBottom: "18px",
  },
  orderCode: {
    color: "#f9fafb",
    fontSize: "30px",
    fontWeight: "700",
    marginBottom: "10px",
  },
  meta: {
    color: "#d1d5db",
    fontSize: "15px",
    marginBottom: "8px",
    lineHeight: "1.6",
  },
  statusBadge: {
    backgroundColor: "#2563eb",
    color: "#ffffff",
    padding: "9px 14px",
    borderRadius: "999px",
    fontSize: "12px",
    fontWeight: "700",
    whiteSpace: "nowrap",
  },
  itemsBox: {
    display: "flex",
    flexDirection: "column",
    gap: "14px",
  },
  itemCard: {
    display: "grid",
    gridTemplateColumns: "120px 1fr auto",
    gap: "18px",
    alignItems: "center",
    backgroundColor: "#0f172a",
    border: "1px solid #1f2937",
    borderRadius: "16px",
    padding: "16px",
  },
  itemImage: {
    width: "120px",
    height: "120px",
    objectFit: "cover",
    borderRadius: "14px",
    backgroundColor: "#111827",
  },
  itemInfo: {
    minWidth: 0,
  },
  itemName: {
    color: "#f9fafb",
    fontSize: "20px",
    fontWeight: "700",
    marginBottom: "10px",
  },
  itemMeta: {
    color: "#d1d5db",
    fontSize: "15px",
    marginBottom: "8px",
  },
  itemSubtotal: {
    color: "#22c55e",
    fontSize: "16px",
    fontWeight: "700",
    marginTop: "6px",
  },
  itemActions: {
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },
  reviewButton: {
    background: "linear-gradient(135deg, #2563eb, #3b82f6)",
    color: "#ffffff",
    border: "none",
    padding: "12px 20px",
    borderRadius: "12px",
    fontSize: "15px",
    fontWeight: "600",
    cursor: "pointer",
    minWidth: "110px",
  },
};

export default OrderHistoryPage;