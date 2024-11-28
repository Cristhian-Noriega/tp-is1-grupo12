import { Routes, Route, Navigate, BrowserRouter } from 'react-router-dom'
import { LoginPage } from './pages/common_pages/LoginPage'
import { RegisterPage } from './pages/common_pages/RegisterPage'
import { ForgetPasswordPage } from './pages/common_pages/ForgetPasswordPage'
import { ProductsAdministrationPage } from './pages/admin_pages/ProductsAdministrationPage'
import { Provider } from './context/Provider'
import ChangePasswordConfirmationPage from './pages/common_pages/ChangePasswordConfirmationPage'
import { ChangePasswordPage } from './pages/common_pages/ChangePasswordPage'
import { ProductsSelectionPage } from './pages/user_pages/ProductsSelectionPage'
import { UserCurrentOrderPage } from './pages/user_pages/UserCurrentOrderPage'
import { AdminOrdersPage } from './pages/admin_pages/AdminOrdersPage'
import { UserOrdersPage } from './pages/user_pages/UserOrdersPage'
import { CurrentOrderProvider } from './context/CurrentOrderProvider'
import { OrderProvider } from './context/OrdersProvider'
import { ProtectedRoutes } from './protected_routes/ProtectedRoutes'
import { UnauthorizedPage } from './pages/common_pages/UnauthorizedPage'
import { ProfilePage } from './pages/common_pages/ProfilePage'
import { ValidationEmailPage } from './pages/common_pages/ValidationEmailPage'

function App() {
  return (
    
          <BrowserRouter>
          <Provider>
            <CurrentOrderProvider>
              <OrderProvider>
                <Routes>
                {/* Rutas Publicas*/}
                <Route path="/" element={<Navigate to="/login" replace />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/forget/password" element={<ForgetPasswordPage />} />
                <Route path="/password/recovery" element={<ChangePasswordPage/>} />
                <Route path="/password/recovery/confirmation" element={<ChangePasswordConfirmationPage />} />
                <Route path="/unauthorized" element={<UnauthorizedPage/>} />
                <Route path="/profile" element={<ProfilePage/>} />
                <Route path="/password/validation" element={<ValidationEmailPage/>} />
            

                 {/* Rutas protegidas para ADMIN */}
              <Route element={<ProtectedRoutes role="ADMIN" />}>
                <Route path="/admin/products" element={<ProductsAdministrationPage />} />
                <Route path="/admin/orders" element={<AdminOrdersPage />} />
              </Route>

              {/* Rutas protegidas para USER */}
              <Route element={<ProtectedRoutes role="USER" />}>
                <Route path="/user/products" element={<ProductsSelectionPage />} />
                <Route path="/user/orders" element={<UserOrdersPage />} />
                <Route path="/user/current-order" element={<UserCurrentOrderPage />} />
              </Route>
                
              
          
                </Routes>
                </OrderProvider>
            </CurrentOrderProvider>
          </Provider>
        </BrowserRouter>
        
  )
}

export default App
