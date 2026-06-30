import { Routes, Route } from 'react-router-dom'
import HomePage from './pages/HomePage'
import SignupChoicePage from './pages/SignupChoicePage'
import LoginChoicePage from './pages/LoginChoicePage'
import UserSignupPage from './pages/UserSignupPage'
import AdminSignupPage from './pages/AdminSignupPage'
import UserLoginPage from './pages/UserLoginPage'
import UserDashboard from './pages/UserDashboard'
import AdminLoginPage from './pages/AdminLoginPage'
import AdminDashboard from './pages/AdminDashboard'
import ProtectedRoute from './components/ProtectedRoute'
import AdminProductManagementPage from './pages/AdminProductManagementPage'
import ProductDetailsPage from './pages/ProductDetailsPage'
import CartPage from "./pages/CartPage";
import CurrentOrdersPage from "./pages/CurrentOrdersPage";
import OrderHistoryPage from "./pages/OrderHistoryPage";
import AdminOrdersPage from "./pages/AdminOrdersPage";
import PaymentPage from "./pages/PaymentPage";
import PaymentSuccessPage from "./pages/PaymentSuccessPage";
import PaymentCancelPage from "./pages/PaymentCancelPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/signup" element={<SignupChoicePage />} />
      <Route path="/login" element={<LoginChoicePage />} />
      <Route path="/signup/user" element={<UserSignupPage />} />
      <Route path="/signup/admin" element={<AdminSignupPage />} />
	  <Route path="/login/user" element={<UserLoginPage />} />
	  <Route path="/login/admin" element={<AdminLoginPage />} />
	  <Route
	    path="/user-dashboard"
	    element={
	      <ProtectedRoute allowedRoles={['USER', 'ROLE_USER']}>
	        <UserDashboard />
	      </ProtectedRoute>
	    }
	  />

	  <Route
	    path="/admin-dashboard"
	    element={
	      <ProtectedRoute allowedRoles={['ADMIN', 'ROLE_ADMIN']}>
	        <AdminDashboard />
	      </ProtectedRoute>
	    }
	  />
	  
	  <Route
	    path="/admin/products"
	    element={
	      <ProtectedRoute allowedRoles={['ADMIN', 'ROLE_ADMIN']}>
	        <AdminProductManagementPage />
	      </ProtectedRoute>
	    }
	  />
	  
	  <Route path="/products/:productId" element={<ProductDetailsPage />} />	
	  
	  <Route path="/user/cart" element={<CartPage />} />
	  <Route path="/user/orders/current" element={<CurrentOrdersPage />} />
	  <Route path="/user/orders/history" element={<OrderHistoryPage />} />
	  <Route path="/admin/orders" element={<AdminOrdersPage />} />  
	  
	  <Route path="/payment/:orderId" element={<PaymentPage />} />
	  <Route path="/payment/success" element={<PaymentSuccessPage />} />
	  <Route path="/payment/cancel" element={<PaymentCancelPage />} />
	  
	 </Routes>
  )
}

export default App