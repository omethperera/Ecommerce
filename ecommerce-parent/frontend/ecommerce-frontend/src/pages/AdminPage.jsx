import React, { useEffect, useState } from 'react'
import {
  getAllProducts,
  createProduct,
  updateProduct,
  deleteProduct,
} from '../services/productService'
import {
  getAllCategories,
  createCategory,
  updateCategory,
  deleteCategory,
} from '../services/categoryService'

function AdminPage() {
  const [products, setProducts] = useState([])
  const [categories, setCategories] = useState([])

  const [productForm, setProductForm] = useState({
    id: '',
    name: '',
    description: '',
    price: '',
    imageUrl: '',
    stockQuantity: '',
    categoryId: '',
  })

  const [categoryForm, setCategoryForm] = useState({
    id: '',
    name: '',
  })

  async function loadData() {
    try {
      const [productsData, categoriesData] = await Promise.all([
        getAllProducts(),
        getAllCategories(),
      ])
      setProducts(productsData)
      setCategories(categoriesData)
    } catch (error) {
      console.error('Error loading admin data', error)
    }
  }

  useEffect(() => {
    loadData()
  }, [])

  const handleProductChange = (e) => {
    setProductForm({
      ...productForm,
      [e.target.name]: e.target.value,
    })
  }

  const handleCategoryChange = (e) => {
    setCategoryForm({
      ...categoryForm,
      [e.target.name]: e.target.value,
    })
  }

  const submitProduct = async (e) => {
    e.preventDefault()

    try {
      const payload = {
        name: productForm.name,
        description: productForm.description,
        price: Number(productForm.price),
        imageUrl: productForm.imageUrl,
        stockQuantity: Number(productForm.stockQuantity),
        categoryId: Number(productForm.categoryId),
      }

      if (productForm.id) {
        await updateProduct(productForm.id, payload)
      } else {
        await createProduct(payload)
      }

      resetProductForm()
      loadData()
    } catch (error) {
      console.error('Error saving product', error)
      alert('Failed to save product')
    }
  }

  const submitCategory = async (e) => {
    e.preventDefault()

    try {
      const payload = {
        name: categoryForm.name,
      }

      if (categoryForm.id) {
        await updateCategory(categoryForm.id, payload)
      } else {
        await createCategory(payload)
      }

      resetCategoryForm()
      loadData()
    } catch (error) {
      console.error('Error saving category', error)
      alert('Failed to save category')
    }
  }

  const editProduct = (product) => {
    setProductForm({
      id: product.id,
      name: product.name || '',
      description: product.description || '',
      price: product.price || '',
      imageUrl: product.imageUrl || '',
      stockQuantity: product.stockQuantity || '',
      categoryId: product.categoryId || product.category?.id || '',
    })
  }

  const editCategory = (category) => {
    setCategoryForm({
      id: category.id,
      name: category.name || '',
    })
  }

  const removeProduct = async (id) => {
    try {
      await deleteProduct(id)
      loadData()
    } catch (error) {
      console.error('Error deleting product', error)
      alert('Failed to delete product')
    }
  }

  const removeCategory = async (id) => {
    try {
      await deleteCategory(id)
      loadData()
    } catch (error) {
      console.error('Error deleting category', error)
      alert('Failed to delete category')
    }
  }

  const resetProductForm = () => {
    setProductForm({
      id: '',
      name: '',
      description: '',
      price: '',
      imageUrl: '',
      stockQuantity: '',
      categoryId: '',
    })
  }

  const resetCategoryForm = () => {
    setCategoryForm({
      id: '',
      name: '',
    })
  }

  return (
    <div className="page">
      <h2>Admin Dashboard</h2>

      <div className="admin-grid">
        <div className="card">
          <h3>{productForm.id ? 'Edit Product' : 'Create Product'}</h3>
          <form className="form" onSubmit={submitProduct}>
            <input
              type="text"
              name="name"
              placeholder="Product Name"
              value={productForm.name}
              onChange={handleProductChange}
            />

            <textarea
              name="description"
              placeholder="Description"
              value={productForm.description}
              onChange={handleProductChange}
            />

            <input
              type="number"
              name="price"
              placeholder="Price"
              value={productForm.price}
              onChange={handleProductChange}
            />

            <input
              type="text"
              name="imageUrl"
              placeholder="Image URL"
              value={productForm.imageUrl}
              onChange={handleProductChange}
            />

            <input
              type="number"
              name="stockQuantity"
              placeholder="Stock Quantity"
              value={productForm.stockQuantity}
              onChange={handleProductChange}
            />

            <select
              name="categoryId"
              value={productForm.categoryId}
              onChange={handleProductChange}
            >
              <option value="">Select Category</option>
              {categories.map((c) => (
                <option key={c.id} value={c.id}>
                  {c.name}
                </option>
              ))}
            </select>

            <button type="submit">
              {productForm.id ? 'Update Product' : 'Create Product'}
            </button>

            <button type="button" onClick={resetProductForm}>
              Clear
            </button>
          </form>
        </div>

        <div className="card">
          <h3>{categoryForm.id ? 'Edit Category' : 'Create Category'}</h3>
          <form className="form" onSubmit={submitCategory}>
            <input
              type="text"
              name="name"
              placeholder="Category Name"
              value={categoryForm.name}
              onChange={handleCategoryChange}
            />

            <button type="submit">
              {categoryForm.id ? 'Update Category' : 'Create Category'}
            </button>

            <button type="button" onClick={resetCategoryForm}>
              Clear
            </button>
          </form>
        </div>
      </div>

      <h3>All Products</h3>
      <div className="grid">
        {products.map((product) => (
          <div className="card" key={product.id}>
            <h4>{product.name}</h4>
            <p>Rs. {product.price}</p>
            <p>{product.categoryName || product.category?.name || 'N/A'}</p>
            <button onClick={() => editProduct(product)}>Edit</button>
            <button onClick={() => removeProduct(product.id)}>Delete</button>
          </div>
        ))}
      </div>

      <h3>All Categories</h3>
      <div className="grid">
        {categories.map((category) => (
          <div className="card" key={category.id}>
            <h4>{category.name}</h4>
            <button onClick={() => editCategory(category)}>Edit</button>
            <button onClick={() => removeCategory(category.id)}>Delete</button>
          </div>
        ))}
      </div>
    </div>
  )
}

export default AdminPage