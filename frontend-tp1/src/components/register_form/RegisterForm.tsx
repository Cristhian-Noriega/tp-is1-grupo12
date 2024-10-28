import React, { useState } from 'react'
import './registerForm.css'
import { useNavigate } from 'react-router-dom';

export const RegisterForm  = () => {
    const [username, setUsername] = useState("");
    const [surname, setSurname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [gender, setGender] = useState("");
    const [address, setAddress] = useState("");

    const navigate = useNavigate();

    const onSubmit = (event) => {
        event.preventDefault();
        console.log('Se creo una cuenta con:', { username, surname, email, password, gender, address });
        setUsername("");
        setSurname("");
        setEmail("");
        setPassword("");
        setGender("")
        setAddress("")
        
        navigate("/login");
      };

      const handleGenderChange = (event) => {
        setGender(event.target.value);
        
      };

  return (
    <div className="register-form-wrapper">
    <div className="register-box">
      <h2>Crear una nueva cuenta</h2>
    </div>
    
    <form onSubmit={onSubmit}>
      <div className="name-box">
        <div className="username">Username</div>
        <input
          id="username"
          type="text"
          value={username}
          name="Username"
          onChange={({ target }) => setUsername(target.value)}
        />
      </div>
      
      <div className="surname-box">
        <div className="surname">Apellido</div>
        <input
          id="surname"
          type="text"
          value={surname}
          name="Apellido"
          onChange={({ target }) => setSurname(target.value)}
        />
      </div>

      <div className="email-box">
        <div className="email">Email</div>
        <input
          id="email"
          type="text"
          value={email}
          name="Email"
          onChange={({ target }) => setEmail(target.value)}
        />
      </div>

      <div className="password-box">
        <div className="password">Contraseña</div>
        <input
          id="password"
          type="password"
          value={password}
          name="Password"
          onChange={({ target }) => setPassword(target.value)}
        />
      </div>
      
      <div className="gender-box">
        <div className="Gender">Género</div>
            <input
                type="radio"
                value="Femenino"
                checked={gender === "Femenino"}
                onChange={({ target }) => setGender(target.value)}
            />
            Femenino

            <input
                type="radio"
                value="Masculino"
                checked={gender === "Masculino"}
                onChange={({ target }) => setGender(target.value)}
            />
            Masculino
        </div>

      <div className="address-box">
        <div className="address">Direccion</div>
        <input
          id="address"
          type="text"
          value={address}
          name="Direccion"
          onChange={({ target }) => setAddress(target.value)}
        />
      </div>
      
      <button id="register-btn" type="submit">Registrarse</button>
    </form>
  </div>
  )
}



