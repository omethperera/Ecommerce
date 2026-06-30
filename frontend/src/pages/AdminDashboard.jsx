import { useNavigate } from "react-router-dom";
import { getUsername, clearAuthData } from "../utils/auth";

function AdminDashboard() {
  const navigate = useNavigate();
  const username = getUsername();

  const handleLogout = () => {
    clearAuthData();
    navigate("/login");
  };

  const handleManageProducts = () => {
    navigate("/admin/products");
  };

  return (
    <div style={styles.page}>
      <div style={styles.card}>
        <div style={styles.topRow}>
          <div>
            <p style={styles.badge}>Seller Dashboard</p>
            <h1 style={styles.title}>Welcome, {username || "Admin"}</h1>
            <p style={styles.subtitle}>
              Manage your product catalog and monitor customer orders from one place.
            </p>
          </div>

          <button style={styles.logoutButton} onClick={handleLogout}>
            Logout
          </button>
        </div>

        <div style={styles.buttonRow}>
          <button style={styles.primaryButton} onClick={handleManageProducts}>
            Manage Products
          </button>

          <button style={styles.secondaryButton} onClick={() => navigate("/admin/orders")}>
            View Orders
          </button>
        </div>
      </div>
    </div>
  );
}

const styles = {
  page: {
    minHeight: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "linear-gradient(135deg, #0b1020, #111827)",
    padding: "24px",
  },
  card: {
    width: "100%",
    maxWidth: "760px",
    backgroundColor: "#111827",
    border: "1px solid #1f2937",
    borderRadius: "20px",
    padding: "36px 32px",
    boxShadow: "0 20px 50px rgba(0,0,0,0.35)",
  },
  topRow: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "flex-start",
    gap: "20px",
    flexWrap: "wrap",
    marginBottom: "28px",
  },
  badge: {
    color: "#60a5fa",
    fontSize: "13px",
    marginBottom: "12px",
    letterSpacing: "0.5px",
  },
  title: {
    color: "#f9fafb",
    fontSize: "34px",
    marginBottom: "12px",
    fontWeight: "700",
  },
  subtitle: {
    color: "#9ca3af",
    fontSize: "16px",
    lineHeight: "1.7",
    maxWidth: "620px",
  },
  buttonRow: {
    display: "flex",
    gap: "14px",
    flexWrap: "wrap",
  },
  primaryButton: {
    background: "linear-gradient(135deg, #2563eb, #3b82f6)",
    color: "#ffffff",
    border: "none",
    padding: "12px 20px",
    borderRadius: "12px",
    fontSize: "15px",
    fontWeight: "600",
    cursor: "pointer",
    minWidth: "170px",
  },
  secondaryButton: {
    backgroundColor: "#1f2937",
    color: "#ffffff",
    border: "1px solid #374151",
    padding: "12px 20px",
    borderRadius: "12px",
    fontSize: "15px",
    fontWeight: "600",
    cursor: "pointer",
    minWidth: "170px",
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

export default AdminDashboard;