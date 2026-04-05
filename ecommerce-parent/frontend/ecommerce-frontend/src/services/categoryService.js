import api from '../api/axios'

export const getAllCategories = async () => {
  const response = await api.get('/api/categories')
  console.log("CATEGORY RESPONSE FULL:", response)
  console.log("CATEGORY RESPONSE DATA:", response.data)
  return response.data
}

export const createCategory = async (category) => {
  const response = await api.post('/api/admin/categories', category)
  return response.data
}

export const updateCategory = async (id, category) => {
  const response = await api.put(`/api/admin/categories/${id}`, category)
  return response.data
}

export const deleteCategory = async (id) => {
  const response = await api.delete(`/api/admin/categories/${id}`)
  return response.data
}