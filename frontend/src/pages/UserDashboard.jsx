import { useNavigate } from "react-router-dom";
import { getUsername, clearAuthData } from "../utils/auth";
import UserProductSection from "../components/UserProductSection";

function UserDashboard() {
  const navigate = useNavigate();
  const username = getUsername();

  const handleLogout = () => {
    clearAuthData();
    navigate("/login");
  };

  return (
    <div style={styles.page}>
      <div style={styles.container}>
        <div style={styles.headerCard}>
          <div style={styles.headerTop}>
            <div>
              <p style={styles.badge}>Buyer Dashboard</p>
              <h1 style={styles.title}>Welcome, {username || "User"}</h1>
              <p style={styles.subtitle}>
                Browse available products below, manage your cart, and track your orders.
              </p>
            </div>

            <button style={styles.logoutButton} onClick={handleLogout}>
              Logout
            </button>
          </div>

          <div style={styles.actionGrid}>
            <button style={styles.actionButton} onClick={() => navigate("/user/cart")}>
              View Cart
            </button>

            <button
              style={styles.actionButton}
              onClick={() => navigate("/user/orders/current")}
            >
              Current Orders
            </button>

            <button
              style={styles.actionButton}
              onClick={() => navigate("/user/orders/history")}
            >
              Order History
            </button>
          </div>
        </div>

        <UserProductSection />
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
    width: "100%",
    maxWidth: "1200px",
    margin: "0 auto",
  },
  headerCard: {
    backgroundColor: "#111827",
    border: "1px solid #1f2937",
    borderRadius: "20px",
    padding: "32px",
    boxShadow: "0 20px 50px rgba(0,0,0,0.35)",
    marginBottom: "24px",
  },
  headerTop: {
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
    marginBottom: "12px",
    letterSpacing: "0.5px",
  },
  title: {
    color: "#f9fafb",
    fontSize: "36px",
    marginBottom: "12px",
    fontWeight: "700",
  },
  subtitle: {
    color: "#9ca3af",
    fontSize: "16px",
    lineHeight: "1.7",
    maxWidth: "720px",
  },
  actionGrid: {
    display: "flex",
    flexWrap: "wrap",
    gap: "14px",
  },
  actionButton: {
    background: "linear-gradient(135deg, #1d4ed8, #2563eb)",
    color: "#ffffff",
    border: "none",
    padding: "12px 18px",
    borderRadius: "12px",
    fontSize: "15px",
    fontWeight: "600",
    cursor: "pointer",
    minWidth: "160px",
  },
  logoutButton: {
    backgroundColor: "#dc2626",
    color: "#ffffff",
    border: "none",
    padding: "12px 20px",
    borderRadius: "12px",
    fontSize: "15px",
    fontWeight: "600",
    cursor: "pointer",
  },
};

export default UserDashboard;