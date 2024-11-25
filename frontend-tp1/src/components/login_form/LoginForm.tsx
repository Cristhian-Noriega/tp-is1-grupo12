import  { useState } from 'react'
import './loginForm.css'
import { Link } from 'react-router-dom';
import { SubmitButton } from '../ui/SubmitButton';

export const LoginForm = ({handleLogin}) => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const onSubmit = (event) => {
        event.preventDefault();
        console.log('Iniciando sesión con:', { email, password });
        handleLogin(email, password);
        setEmail("");
        setPassword("");
      };

  return (
    <div className="login-form-wrapper">
    <div className="iniciar-sesion-box">
      <h2>Iniciar Sesión</h2>
    </div>
    
    <form onSubmit={onSubmit}>
      <div className="username-box form-box">
        <div className="username">Email</div>
        <input
          id="email"
          type="email"
          value={email}
          name="email"
          onChange={({ target }) => setEmail(target.value)}
        />
      </div>
      <div className="password-box form-box">
        <div className="password">Password</div>
        <input
          id="password"
          type="password"
          value={password}
          name="password"
          onChange={({ target }) => setPassword(target.value)}
        />
      </div>
      <Link to="/forget/password" className='forget-password-box'>
        ¿Olvidaste tu contraseña?
      </Link>
      
      <Link to="/register">
          <SubmitButton 
          type="navigation"
          text="Crear Cuenta"
          backgroundColor="#64B82B"
          backgroundColorHover="#6bf80c"
          />
      </Link>
      
      <SubmitButton
      type="submit"
      text="Login"
      backgroundColor="#007fca"
      backgroundColorHover="#23A3E8"
      />
    </form>
  </div>
  )
}



