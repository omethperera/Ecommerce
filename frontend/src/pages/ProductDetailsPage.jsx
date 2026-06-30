import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getProductById } from "../api/publicProductApi";
import { addToCart } from "../api/orderApi";

function ProductDetailsPage() {
  const { productId } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(false);

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    loadProduct();
  }, [productId]);

  const loadProduct = async () => {
    setLoading(true);
    try {
      const data = await getProductById(productId);
      setProduct(data);
    } catch (error) {
      console.error(error);
      alert("Failed to load product details");
    } finally {
      setLoading(false);
    }
  };

  const handleAddToCart = async () => {
    try {
      await addToCart(userId, {
        productId: product.id,
        quantity: 1,
      });

      alert("Item added to cart successfully");
      navigate("/user/cart");
    } catch (error) {
      console.error(error);
      alert(error.response?.data?.message || "Failed to add item to cart");
    }
  };

  return (
    <div style={styles.page}>
      <div style={styles.container}>
        <button style={styles.backButton} onClick={() => navigate(-1)}>
          Back
        </button>

        {loading ? (
          <p style={styles.infoText}>Loading...</p>
        ) : !product ? (
          <p style={styles.infoText}>Product not found.</p>
        ) : (
          <div style={styles.card}>
            <div style={styles.imageWrapper}>
              <img
                src={product.imageUrl || "https://via.placeholder.com/600x400?text=No+Image"}
                alt={product.name}
                style={styles.image}
              />
            </div>

            <div style={styles.content}>
              <p style={styles.category}>{product.categoryName}</p>
              <h1 style={styles.title}>{product.name}</h1>
              <p style={styles.price}>Rs. {product.price}</p>
              <p style={styles.stock}>Available Stock: {product.stockQuantity}</p>

              <div style={styles.descriptionBox}>
                <h3 style={styles.sectionTitle}>Description</h3>
                <p style={styles.description}>
                  {product.description || "No description available."}
                </p>
              </div>

              <button style={styles.cartButton} onClick={handleAddToCart}>
                Add to Cart
              </button>
            </div>
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
    padding: "30px 20px",
  },
  container: {
    maxWidth: "1100px",
    margin: "0 auto",
  },
  backButton: {
    backgroundColor: "#1f2937",
    color: "#ffffff",
    border: "1px solid #374151",
    padding: "10px 16px",
    borderRadius: "10px",
    fontSize: "14px",
    fontWeight: "600",
    cursor: "pointer",
    marginBottom: "20px",
  },
  infoText: {
    color: "#d1d5db",
    fontSize: "16px",
  },
  card: {
    display: "grid",
    gridTemplateColumns: "1fr 1fr",
    gap: "24px",
    backgroundColor: "#111827",
    border: "1px solid #1f2937",
    borderRadius: "20px",
    overflow: "hidden",
    boxShadow: "0 20px 50px rgba(0,0,0,0.35)",
  },
  imageWrapper: {
    backgroundColor: "#0f172a",
  },
  image: {
    width: "100%",
    height: "100%",
    minHeight: "420px",
    objectFit: "cover",
    display: "block",
  },
  content: {
    padding: "28px",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
  },
  category: {
    color: "#60a5fa",
    fontSize: "14px",
    marginBottom: "8px",
  },
  title: {
    color: "#f9fafb",
    fontSize: "34px",
    fontWeight: "700",
    marginBottom: "12px",
  },
  price: {
    color: "#22c55e",
    fontSize: "24px",
    fontWeight: "700",
    marginBottom: "10px",
  },
  stock: {
    color: "#d1d5db",
    fontSize: "15px",
    marginBottom: "22px",
  },
  descriptionBox: {
    backgroundColor: "#0f172a",
    border: "1px solid #1f2937",
    borderRadius: "14px",
    padding: "18px",
    marginBottom: "24px",
  },
  sectionTitle: {
    color: "#f9fafb",
    fontSize: "18px",
    marginBottom: "10px",
  },
  description: {
    color: "#d1d5db",
    fontSize: "15px",
    lineHeight: "1.7",
  },
  cartButton: {
    background: "linear-gradient(135deg, #2563eb, #3b82f6)",
    color: "#fff",
    border: "none",
    padding: "14px 18px",
    borderRadius: "12px",
    fontSize: "15px",
    fontWeight: "700",
    cursor: "pointer",
    width: "220px",
  },
};

export default ProductDetailsPage;