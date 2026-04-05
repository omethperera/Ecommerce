import { Link } from 'react-router-dom'

function ProductCard({ product }) {
  return (
    <div className="card">
      <h3>{product.name}</h3>
      <p><strong>Price:</strong> Rs. {product.price}</p>
      <p><strong>Category:</strong> {product.categoryName || product.category?.name || 'N/A'}</p>
      <p>{product.description}</p>

      {product.imageUrl && (
        <img
          src={product.imageUrl}
          alt={product.name}
          className="product-image"
        />
      )}

      <Link to={`/products/${product.id}`} className="btn-link">
        View Details
      </Link>
    </div>
  )
}

export default ProductCard