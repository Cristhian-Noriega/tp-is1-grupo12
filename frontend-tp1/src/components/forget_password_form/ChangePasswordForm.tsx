import React, { useState } from 'react'
import './changePasswordForm.css'

import { SubmitButton } from '../ui/SubmitButton'
export const ChangePasswordForm = ({handlePasswordChange}) => {
    const [email, setEmail] = useState("")
    const [newPassword, setNewPassword] = useState("")

    const onSubmit = (event) => {
        event.preventDefault();
        handlePasswordChange(email,newPassword)
        setEmail("");
        setNewPassword("");
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
