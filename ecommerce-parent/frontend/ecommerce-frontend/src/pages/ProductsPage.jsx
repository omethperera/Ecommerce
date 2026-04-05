import { useEffect, useState } from 'react'
import { getAllProducts } from '../services/productService'
import { getAllCategories } from '../services/categoryService'
import ProductCard from '../components/ProductCard'
import CategoryFilter from '../components/CategoryFilter'

function ProductsPage() {
  const [products, setProducts] = useState([])
  const [categories, setCategories] = useState([])
  const [selectedCategory, setSelectedCategory] = useState('')
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    loadData()
  }, [])

  const loadData = async () => {
    try {
      const productsData = await getAllProducts()
      console.log("productsData from service:", productsData)
      setProducts(productsData)

      const categoriesData = await getAllCategories()
      setCategories(categoriesData)

    } catch (error) {
      console.error('Error loading data', error)
    } finally {
      setLoading(false)
    }
  }

  const filteredProducts = selectedCategory
    ? products.filter(
        (p) =>
          p.categoryName === selectedCategory ||
          p.category?.name === selectedCategory
      )
    : products

  console.log("PRODUCTS STATE:", products)

  return (
    <div className="page">
      <h2>Products</h2>

      <CategoryFilter
        categories={categories}
        selectedCategory={selectedCategory}
        onChange={setSelectedCategory}
      />

      {loading ? (
        <p>Loading...</p>
      ) : (
        <div className="grid">
          {filteredProducts.length > 0 ? (
            filteredProducts.map((product) => (
              <ProductCard key={product.id} product={product} />
            ))
          ) : (
            <p>No products found.</p>
          )}
        </div>
      )}
    </div>
  )
}

export default ProductsPage