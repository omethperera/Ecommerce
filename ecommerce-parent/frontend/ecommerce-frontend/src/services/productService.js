import api from '../api/axios'

export const getAllProducts = async () => {
  const response = await api.get('/api/products')
  console.log('RAW PRODUCTS RESPONSE:', response.data)
  return response.data
}

export const getProductById = async (id) => {
  const response = await api.get(`/api/products/${id}`)
  return response.data
}

export const createProduct = async (product) => {
  const response = await api.post('/api/admin/products', product)
  return response.data
}

export const updateProduct = async (id, product) => {
  const response = await api.put(`/api/admin/products/${id}`, product)
  return response.data
}

export const deleteProduct = async (id) => {
  const response = await api.delete(`/api/admin/products/${id}`)
  return response.data
}