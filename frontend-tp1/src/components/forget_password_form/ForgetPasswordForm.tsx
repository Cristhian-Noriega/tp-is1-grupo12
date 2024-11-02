import React, { useState } from 'react'
import './forgetPasswordForm.css'
import { Link, useNavigate } from 'react-router-dom'
import { SubmitButton } from '../ui/SubmitButton'
export const ForgetPasswordForm = () => {
    const [email, setEmail] = useState("")

    const navigate = useNavigate();
    const onSubmit = (event) => {
        console.log('Se envio un mail de recuperacion al email: ', {email});
        navigate('/password-recovery');
    }
  return (
    <div className='forget-password-form-wrapper'>
        <h2>Recuperar Contraseña</h2>
        <form onSubmit={onSubmit}>
            <div className='email-box form-box'>
                <div className='email-text'>Para restablecer su contraseña, por favor ingrese la dirección de correo electrónico asociada a su cuenta.
                   <br /> Le enviaremos un enlace para que pueda crear una nueva contraseña.</div>
                <input
                    id="email"
                    type="text"
                    value={email}
                    name="Email"
                    placeholder='Email'
                    onChange={({ target }) => setEmail(target.value)}
                />
            </div>
            
            <div className='button-box-wrapper'>
                <Link to="/login" className='forget-password-box'>
                <SubmitButton 
                    type="submit"
                    text="Cancelar"
                    backgroundColor="#FF0000"
                    backgroundColorHover="#FF2080"
                />
                </Link>
                <SubmitButton 
                    type="submit"
                    text="Recuperar"
                    backgroundColor="#4CAF50"
                    backgroundColorHover="#45a049"
                />
                
            </div>
            
        </form>
    </div>
  )
}
