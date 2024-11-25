import React, { useState } from 'react'
import './changePasswordForm.css'
import { useNavigate } from 'react-router-dom'
import { SubmitButton } from '../ui/SubmitButton'
export const ChangePasswordForm = ({handlePasswordChange, setShowMessage}) => {
    const [email, setEmail] = useState("")
    const [newPassword, setNewPassword] = useState("")

    const navigate = useNavigate();
    const onSubmit = (event) => {
        setShowMessage({
            text: `La contraseña de ${email} ha sido cambiada con éxito.`,
            type: "success",
            duration: 3000
          })
        handlePasswordChange(email,newPassword)
        navigate('/password/recovery/confirmation');
    }
  return (
    <div className='forget-password-form-wrapper'>
        <h2>Cambiar Contraseña</h2>
        <form onSubmit={onSubmit}>
            <div className='email-box form-box'>
                <input
                    id="email"
                    type="email"
                    value={email}
                    name="email"
                    placeholder='Email'
                    onChange={({ target }) => setEmail(target.value)}
                />
            </div>
            <div className='newPassword-box form-box'>
                <input
                    id="newPassword"
                    type="password"
                    value={newPassword}
                    name="password"
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
