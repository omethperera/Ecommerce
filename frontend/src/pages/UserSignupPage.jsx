import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

function UserSignupPage() {
  const navigate = useNavigate()

  const [form, setForm] = useState({
    username: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    phone: '',
    profilePic: '',
    address: '',
  })

  const [loading, setLoading] = useState(false)

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)

    try {
      const response = await fetch('http://localhost:8080/api/auth/register/user', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(form),
      })

      const message = await response.text()

      if (response.ok) {
        alert(message || 'User registered successfully')
        navigate('/login')
      } else {
        alert(message || 'User registration failed')
      }
    } catch (error) {
      console.error(error)
      alert('Error connecting to backend')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div style={styles.page}>
      <div style={styles.card}>
        <h2 style={styles.title}>User Sign Up</h2>
        <p style={styles.subtitle}>Create your buyer account</p>

        <form style={styles.form} onSubmit={handleSubmit}>
          <input
            style={styles.input}
            type="text"
            name="username"
            placeholder="Username"
            value={form.username}
            onChange={handleChange}
            required
          />

          <input
            style={styles.input}
            type="text"
            name="firstName"
            placeholder="First Name"
            value={form.firstName}
            onChange={handleChange}
            required
          />

          <input
            style={styles.input}
            type="text"
            name="lastName"
            placeholder="Last Name"
            value={form.lastName}
            onChange={handleChange}
          />

          <input
            style={styles.input}
            type="email"
            name="email"
            placeholder="Email"
            value={form.email}
            onChange={handleChange}
            required
          />

          <input
            style={styles.input}
            type="password"
            name="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
          />

          <input
            style={styles.input}
            type="text"
            name="phone"
            placeholder="Phone"
            value={form.phone}
            onChange={handleChange}
          />

          <input
            style={styles.input}
            type="text"
            name="address"
            placeholder="Address"
            value={form.address}
            onChange={handleChange}
          />

          <button style={styles.primaryButton} type="submit" disabled={loading}>
            {loading ? 'Registering...' : 'Register as User'}
          </button>

          <button
            style={styles.secondaryButton}
            type="button"
            onClick={() => navigate('/signup')}
          >
            Back
          </button>
        </form>
      </div>
    </div>
  )
}

const styles = {
  page: {
    minHeight: '100vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    background: 'linear-gradient(135deg, #0b1020, #111827)',
    padding: '20px',
  },
  card: {
    width: '100%',
    maxWidth: '460px',
    backgroundColor: '#111827',
    border: '1px solid #1f2937',
    borderRadius: '18px',
    padding: '36px 30px',
    boxShadow: '0 20px 50px rgba(0,0,0,0.35)',
  },
  title: {
    fontSize: '30px',
    marginBottom: '8px',
    color: '#f9fafb',
    fontWeight: '700',
    textAlign: 'center',
  },
  subtitle: {
    fontSize: '15px',
    marginBottom: '24px',
    color: '#9ca3af',
    textAlign: 'center',
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '14px',
  },
  input: {
    backgroundColor: '#0f172a',
    border: '1px solid #374151',
    color: '#f9fafb',
    padding: '13px 14px',
    borderRadius: '10px',
    fontSize: '15px',
    outline: 'none',
  },
  primaryButton: {
    background: 'linear-gradient(135deg, #2563eb, #3b82f6)',
    color: '#ffffff',
    border: 'none',
    padding: '13px',
    borderRadius: '10px',
    fontSize: '15px',
    fontWeight: '600',
    marginTop: '6px',
  },
  secondaryButton: {
    backgroundColor: '#1f2937',
    color: '#d1d5db',
    border: '1px solid #374151',
    padding: '13px',
    borderRadius: '10px',
    fontSize: '15px',
    fontWeight: '600',
  },
}

export default UserSignupPage