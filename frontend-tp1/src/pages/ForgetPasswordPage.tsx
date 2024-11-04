import { requestPassChangeUtils } from '../utils/requestPassChangeUtils';
import { ForgetPasswordForm } from '../components/forget_password_form/ForgetPasswordForm'

export const ForgetPasswordPage = () => {
  const {handleSendEmailToChangePassword} = requestPassChangeUtils();
  return (
    <div>
        <ForgetPasswordForm handleSendEmailToChangePassword={handleSendEmailToChangePassword}/>
    </div>
  )
}
