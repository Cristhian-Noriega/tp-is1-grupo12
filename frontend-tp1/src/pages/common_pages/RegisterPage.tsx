import { RegisterForm } from '../../components/register_form/RegisterForm'
import CardMessage from '../../components/card_message/CardMessagge';
import { useContext } from 'react';
import { Context } from '../../context/Context';

export const RegisterPage = () => {
  
  const { showMessage, setShowMessage } = useContext(Context);
  
  return (
    <div>
        <RegisterForm/>
        {showMessage && (
        <CardMessage
          messageProperties={showMessage}
          onClose={() => setShowMessage(false)}
        />
      )}
    </div>
  )
}
