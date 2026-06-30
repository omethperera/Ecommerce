import { useNavigate } from 'react-router-dom'

function SignupChoicePage() {
  const navigate = useNavigate()

  return (
    <div style={styles.page}>
      <div style={styles.card}>
        <h2 style={styles.title}>Create Account</h2>
        <p style={styles.subtitle}>Choose the type of account to register</p>

        <div style={styles.buttonGroup}>
          <button
            style={styles.primaryButton}
            onClick={() => navigate('/signup/user')}
          >
            Sign Up as User
          </button>

          <button
            style={styles.secondaryButton}
            onClick={() => navigate('/signup/admin')}
          >
            Sign Up as Admin
          </button>

          <button style={styles.backButton} onClick={() => navigate('/')}>
            Back
          </button>
        </div>
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
    maxWidth: '430px',
    backgroundColor: '#111827',
    border: '1px solid #1f2937',
    borderRadius: '18px',
    padding: '40px 30px',
    boxShadow: '0 20px 50px rgba(0,0,0,0.35)',
    textAlign: 'center',
  },
  title: {
    fontSize: '30px',
    marginBottom: '10px',
    color: '#f9fafb',
    fontWeight: '700',
  },
  subtitle: {
    fontSize: '15px',
    marginBottom: '28px',
    color: '#9ca3af',
  },
  buttonGroup: {
    display: 'flex',
    flexDirection: 'column',
    gap: '14px',
  },
  primaryButton: {
    background: 'linear-gradient(135deg, #2563eb, #3b82f6)',
    color: '#ffffff',
    border: 'none',
    padding: '13px',
    borderRadius: '10px',
    fontSize: '15px',
    fontWeight: '600',
  },
  secondaryButton: {
    backgroundColor: '#0f172a',
    color: '#e5e7eb',
    border: '1px solid #374151',
    padding: '13px',
    borderRadius: '10px',
    fontSize: '15px',
    fontWeight: '600',
  },
  backButton: {
    backgroundColor: '#1f2937',
    color: '#d1d5db',
    border: '1px solid #374151',
    padding: '13px',
    borderRadius: '10px',
    fontSize: '15px',
    fontWeight: '600',
  },
}

export default SignupChoicePage