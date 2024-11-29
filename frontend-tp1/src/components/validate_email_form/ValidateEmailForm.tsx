import React, { useState } from 'react'
import { SubmitButton } from '../ui/SubmitButton'
import { validationEmailUtils } from "../../utils/validationEmailUtils";
export const ValidateEmailForm = ({setShowMessage}) => {
    const [email, setEmail] = useState("")
    const {handleValidateEmail } = validationEmailUtils();

    const onSubmit = (event) => {
        event.preventDefault();
        handleValidateEmail(email)
        setEmail("")
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
