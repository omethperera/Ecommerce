export function saveAuthData(data) {
  console.log('saveAuthData START')
  console.log('saveAuthData received = ', data)

  localStorage.setItem('accessToken', data.accessToken)
  console.log('after accessToken = ', localStorage.getItem('accessToken'))

  localStorage.setItem('refreshToken', data.refreshToken)
  localStorage.setItem('username', data.username)

  localStorage.setItem('userId', String(data.userId))
  console.log('after userId set = ', localStorage.getItem('userId'))

  const normalizedRole =
    data.role === 'ROLE_ADMIN' ? 'ADMIN' :
    data.role === 'ROLE_USER' ? 'USER' :
    data.role

  localStorage.setItem('role', normalizedRole)
  console.log('after role set = ', localStorage.getItem('role'))
  console.log('saveAuthData END')
}

export function clearAuthData() {
  localStorage.removeItem('accessToken')
  localStorage.removeItem('refreshToken')
  localStorage.removeItem('role')
  localStorage.removeItem('username')
  localStorage.removeItem('userId')
}

export function getAccessToken() {
  return localStorage.getItem('accessToken')
}

export function getRefreshToken() {
  return localStorage.getItem('refreshToken')
}

export function getUsername() {
  return localStorage.getItem('username')
}

export function getUserId() {
  return localStorage.getItem('userId')
}

export function getRole() {
  return localStorage.getItem('role')
}

export function isAdmin() {
  return getRole() === 'ADMIN'
}

export function isUser() {
  return getRole() === 'USER'
}

export function isLoggedIn() {
  return !!getAccessToken()
}