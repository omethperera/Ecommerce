import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

function Header() {
  const { token, logout } = useAuth()
  const navigate = useNavigate()

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <header className="header">
      <div className="header-inner">
	  <h2>Gaming Gear Nexus</h2>
        <nav>
          <Link to="/">Products</Link>
          {token ? (
            <>
              <Link to="/admin">Admin</Link>
              <button onClick={handleLogout}>Logout</button>
            </>
          ) : (
            <Link to="/login">Login</Link>
          )}
        </nav>
      </div>
    </header>
  )
}

export default Header