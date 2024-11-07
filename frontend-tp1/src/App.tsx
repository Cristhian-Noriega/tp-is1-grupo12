
import { Routes, Route, HashRouter, Navigate } from 'react-router-dom'
import { LoginPage } from './pages/common_pages/LoginPage'
import { RegisterPage } from './pages/common_pages/RegisterPage'
import { ForgetPasswordPage } from './pages/common_pages/ForgetPasswordPage'
import { ProductsAdministrationPage } from './pages/admin_pages/ProductsAdministrationPage'
import { Provider } from './context/Provider'
import ChangePasswordConfirmationPage from './pages/common_pages/ChangePasswordConfirmationPage'
import { ChangePasswordPage } from './pages/common_pages/ChangePasswordPage'
import { OrderProvider } from './context/OrderProvider'
import { ProductsSelectionPage } from './pages/user_pages/ProductsSelectionPage'
import { UserOrderPage } from './pages/user_pages/UserOrderPage'
import { UserConfirmedOrdersPage } from './pages/user_pages/UserConfirmedOrdersPage'
import { AdminOrdersPage } from './pages/admin_pages/AdminOrdersPage'


function App() {

  return (
    <Provider>
      <OrderProvider>
        <HashRouter>
        <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/forget-password" element={<ForgetPasswordPage />} />
        <Route path="/password-recovery" element={<ChangePasswordPage/>} />
        <Route path="/password-recovery-confirmation" element={<ChangePasswordConfirmationPage />} />

        <Route path="/user-products" element={<ProductsSelectionPage/>} />
        <Route path="/user-orders" element={<UserOrderPage/>} />
        <Route path="/user-confirmed-orders" element={<UserConfirmedOrdersPage/>} />

        <Route path="/admin-products" element={<ProductsAdministrationPage/>} />
        <Route path="/admin-orders" element={<AdminOrdersPage/>} />
  

        </Routes>
        </HashRouter>
      </OrderProvider>
    </Provider>
  )
}

export default App
