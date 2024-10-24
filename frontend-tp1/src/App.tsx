
import { Routes, Route, HashRouter, Navigate } from 'react-router-dom'
import { LoginPage } from './pages/LoginPage'

function App() {

  return (
    <>
      <HashRouter>
      <Routes>
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="/login" element={<LoginPage />} />
      </Routes>
      </HashRouter>
    </>
  )
}

export default App
