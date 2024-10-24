import React, { useState } from 'react'
import './loginForm.css'
import { Link } from 'react-router-dom';

export const LoginForm = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const onSubmit = (event) => {
        event.preventDefault();
        console.log('Iniciando sesión con:', { username, password });
        setUsername("");
        setPassword("");
      };

  return (
    <div className="login-form-wrapper">
    <div className="iniciar-sesion-box">
      <h2>Iniciar Sesión</h2>
    </div>
    
    <form onSubmit={onSubmit}>
      <div className="username-box">
        <div className="username">Username</div>
        <input
          id="username"
          type="text"
          value={username}
          name="Username"
          onChange={({ target }) => setUsername(target.value)}
        />
      </div>
      <div className="password-box">
        <div className="password">Password</div>
        <input
          id="password"
          type="password"
          value={password}
          name="Password"
          onChange={({ target }) => setPassword(target.value)}
        />
      </div>
      <Link to="/forget-password" className='forget-password-box'>
        ¿Olvidaste tu contraseña?
      </Link>
      <button id="login-btn" type="submit">Login</button>
    </form>
  </div>
  )
}



