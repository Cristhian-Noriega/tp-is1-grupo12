import validateEmailService  from '../../services/user';
import {Context} from '../../context/Context'
import CardMessage from '../../components/card_message/CardMessagge';
import { useContext } from 'react';
import { ValidateEmailForm } from '../../components/validate_email_form/ValidateEmailForm';

export const ValidationEmailPage = () => {

  const {showMessage, setShowMessage} = useContext(Context)
  
  const handleValidateEmail = (email) => {
    console.log(email)
    validateEmailService.validateEmail(email)
  }

  return (
    <div>
        <ValidateEmailForm handleValidateEmail={handleValidateEmail} setShowMessage={setShowMessage}/>
        {showMessage && (
        <CardMessage
          messageProperties={showMessage}
          onClose={() => setShowMessage(false)}
        />
      )}
    </div>
  )
}