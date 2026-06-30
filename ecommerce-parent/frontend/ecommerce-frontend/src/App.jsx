import { Routes, Route, Navigate } from 'react-router-dom'
import Header from './components/Header'
import ProductsPage from './pages/ProductsPage'
import ProductDetailsPage from './pages/ProductDetailsPage'
import LoginPage from './pages/LoginPage'
import AdminPage from './pages/AdminPage'
import { useAuth } from './context/AuthContext'

function App() {
  const { token } = useAuth()

  return (
    <>
      <Header />
      <div className="container">
        <Routes>
          <Route path="/" element={<ProductsPage />} />
          <Route path="/products/:id" element={<ProductDetailsPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route
            path="/admin"
            element={token ? <AdminPage /> : <Navigate to="/login" />}
          />
        </Routes>
      </div>
    </>
  )
}

export default App