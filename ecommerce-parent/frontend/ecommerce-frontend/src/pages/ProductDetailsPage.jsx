import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { getProductById } from '../services/productService'

function ProductDetailsPage() {
  const { id } = useParams()
  const [product, setProduct] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  async function loadProduct() {
    try {
      setLoading(true)
      setError('')
      const data = await getProductById(id)
      setProduct(data)
    } catch (err) {
      console.error('Error loading product details', err)
      setError('Failed to load product details')
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    if (id) {
      loadProduct()
    }
  }, [id])

  if (loading) {
    return <p>Loading...</p>
  }

  if (error) {
    return <p className="error">{error}</p>
  }

  if (!product) {
    return <p>No product found.</p>
  }

  return (
    <div className="page">
      <h2>{product.name}</h2>

      {product.imageUrl && (
        <img
          src={product.imageUrl}
          alt={product.name}
          className="details-image"
        />
      )}

      <p><strong>Price:</strong> Rs. {product.price}</p>
      <p><strong>Description:</strong> {product.description}</p>
      <p><strong>Category:</strong> {product.categoryName || product.category?.name || 'N/A'}</p>
      <p><strong>Stock:</strong> {product.stockQuantity ?? 'N/A'}</p>
    </div>
  )
}

export default ProductDetailsPage