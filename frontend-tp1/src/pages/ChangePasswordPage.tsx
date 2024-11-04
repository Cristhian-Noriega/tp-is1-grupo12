import { changePasswordUtils } from '../utils/changePasswordUtils';
import { ChangePasswordForm } from '../components/forget_password_form/ChangePasswordForm'

export const ChangePasswordPage = () => {
  const {handlePasswordChange} = changePasswordUtils();
  return (
    <div>
        <ChangePasswordForm handlePasswordChange={handlePasswordChange}/>
    </div>
  )
}
