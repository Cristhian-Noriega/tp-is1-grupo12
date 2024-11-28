import React, { useState } from 'react'
import { SubmitButton } from '../ui/SubmitButton'
export const ValidateEmailForm = ({handleValidateEmail, setShowMessage}) => {
    const [email, setEmail] = useState("")

    const onSubmit = (event) => {
        event.preventDefault();
        setShowMessage({
            text: `La contraseña de ${email} ha sido cambiada con éxito.`,
            type: "success",
            duration: 3000
          })
          handleValidateEmail(email)
    }
  return (
    <div className='forget-password-form-wrapper'>
        <h2>Validar con email</h2>
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
            
            <div className='button-box-wrapper'>
                
                <SubmitButton 
                    type="submit"
                    text="Validar Contraseña"
                    backgroundColor="#4CAF50"
                    backgroundColorHover="#45a049"
                />
                
            </div>
            
        </form>
    </div>
  )
}
