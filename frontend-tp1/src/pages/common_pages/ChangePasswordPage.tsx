import { changePasswordUtils } from '../../utils/changePasswordUtils';
import { ChangePasswordForm } from '../../components/forget_password_form/ChangePasswordForm'
import {Context} from '../../context/Context'
import CardMessage from '../../components/card_message/CardMessagge';
import { useContext } from 'react';

export const ChangePasswordPage = () => {

  const {handlePasswordChange} = changePasswordUtils();
  const {showMessage, setShowMessage} = useContext(Context)
  

  return (
    <div>
        <ChangePasswordForm handlePasswordChange={handlePasswordChange}/>
        {showMessage && (
        <CardMessage
          messageProperties={showMessage}
          onClose={() => setShowMessage(false)}
        />
      )}
    </div>
  )
}
