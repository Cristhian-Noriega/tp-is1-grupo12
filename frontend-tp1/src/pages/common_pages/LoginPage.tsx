
import { loginUtils } from '../../utils/loginUtils';
import { LoginForm } from '../../components/login_form/LoginForm'
import { Navigate } from 'react-router-dom';
import {Context} from '../../context/Context'
import { useContext } from 'react';
import CardMessage from '../../components/card_message/CardMessagge';
export const LoginPage = () => {
  const {user,handleLogin} = loginUtils();
  const {showMessage, setShowMessage} = useContext(Context)


if (user != null) {
  return <Navigate to="/user-products" />;
}
  return (
    <div>
        <LoginForm handleLogin={handleLogin}/>

        {showMessage && (
        <CardMessage
          messageProperties={showMessage}
          onClose={() => setShowMessage(false)}
        />
      )}
    </div>
  )
}
