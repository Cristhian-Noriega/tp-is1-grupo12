import { registerUtils } from '../utils/registerUtils';
import { RegisterForm } from '../components/register_form/RegisterForm'

export const RegisterPage = () => {
  const {handleRegister} = registerUtils();
  return (
    <div>
        <RegisterForm handleRegister={handleRegister}/>
    </div>
  )
}
