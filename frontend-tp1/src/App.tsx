
import { Routes, Route, HashRouter, Navigate } from 'react-router-dom'
import { LoginPage } from './pages/LoginPage'
import { RegisterPage } from './pages/RegisterPage'
import { ForgetPasswordPage } from './pages/ForgetPasswordPage'
import ChangePasswordConfirmationPage from './pages/ChangePasswordConfirmationPage'
import { ChangePasswordPage } from './pages/ChangePasswordPage'

function App() {

  return (
    <>
      <HashRouter>
      <Routes>
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/forget-password" element={<ForgetPasswordPage />} />
      <Route path="/password-recovery" element={<ChangePasswordPage/>} />
      <Route path="/password-recovery-confirmation" element={<ChangePasswordConfirmationPage />} />
      </Routes>
      </HashRouter>
    </>
  )
}

export default App
