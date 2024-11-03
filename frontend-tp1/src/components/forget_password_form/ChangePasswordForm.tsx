import React, { useState } from 'react'
import './changePasswordForm.css'
import { useNavigate } from 'react-router-dom'
import { SubmitButton } from '../ui/SubmitButton'
export const ChangePasswordForm = () => {
    const [email, setEmail] = useState("")
    const [newPassword, setNewPassword] = useState("")

    const navigate = useNavigate();
    const onSubmit = (event) => {
        console.log('Se Cambio Correctamente la contrasenia ', {email},{newPassword});
        navigate('/password-recovery-confirmation');
    }
  return (
    <div className='forget-password-form-wrapper'>
        <h2>Cambiar Contraseña</h2>
        <form onSubmit={onSubmit}>
            <div className='email-box form-box'>
                <input
                    id="email"
                    type="text"
                    value={email}
                    name="Email"
                    placeholder='Email'
                    onChange={({ target }) => setEmail(target.value)}
                />
            </div>
            <div className='newPassword-box form-box'>
                <input
                    id="newPassword"
                    type="password"
                    value={newPassword}
                    name="Password"
                    placeholder='New Password'
                    onChange={({ target }) => setNewPassword(target.value)}
                />
            </div>
            
            <div className='button-box-wrapper'>
                
                <SubmitButton 
                    type="submit"
                    text="Cambiar Contraseña"
                    backgroundColor="#4CAF50"
                    backgroundColorHover="#45a049"
                />
                
            </div>
            
        </form>
    </div>
  )
}
