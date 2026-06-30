import api from '../api/axios'

export const loginUser = async (email, password) => {
  const response = await api.post('/api/auth/login', {
    usernameOrEmail: email,
    password: password,
  })
  return response.data
}