import { requestPassChangeUtils } from '../../utils/requestPassChangeUtils';
import { ForgetPasswordForm } from '../../components/forget_password_form/ForgetPasswordForm'
import {Context} from '../../context/Context'
import CardMessage from '../../components/card_message/CardMessagge';
import { useContext } from 'react';

export const ForgetPasswordPage = () => {
  const {showMessage, setShowMessage} = useContext(Context)

  const {handleSendEmailToChangePassword} = requestPassChangeUtils();
  return (
    <div>
        <ForgetPasswordForm handleSendEmailToChangePassword={handleSendEmailToChangePassword}
          setShowMessage={setShowMessage}
        />
        {showMessage && (
        <CardMessage
          messageProperties={showMessage}
          onClose={() => setShowMessage(false)}
        />
      )}
    </div>
  )
}
