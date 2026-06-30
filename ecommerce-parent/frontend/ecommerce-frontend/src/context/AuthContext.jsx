import React, { createContext, useContext, useState } from 'react'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [token, setToken] = useState(localStorage.getItem('token') || '')
  const [user, setUser] = useState(
    JSON.parse(localStorage.getItem('user')) || null
  )

  const login = (tokenValue, userValue = null) => {
    localStorage.setItem('token', tokenValue)
    setToken(tokenValue)

    if (userValue) {
      localStorage.setItem('user', JSON.stringify(userValue))
      setUser(userValue)
    } else {
      localStorage.removeItem('user')
      setUser(null)
    }
  }

  const logout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    setToken('')
    setUser(null)
  }

  return (
    <AuthContext.Provider value={{ token, user, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  return useContext(AuthContext)
}