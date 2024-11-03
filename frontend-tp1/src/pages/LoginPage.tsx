
import { loginUtils } from '../utils/loginUtils';
import { LoginForm } from '../components/login_form/LoginForm'
import { Navigate } from 'react-router-dom';
export const LoginPage = () => {
  const {user,handleLogin} = loginUtils();


if (user != null) {
  return <Navigate to="/" />;
}
  return (
    <div>
        <LoginForm handleLogin={handleLogin}/>
    </div>
  )
}
