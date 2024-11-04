import React, { useState } from 'react'
import './registerForm.css'
import { Link, useNavigate } from 'react-router-dom';
import { SubmitButton } from '../ui/SubmitButton';

export const RegisterForm  = ({handleRegister}) => {
    const [username, setUsername] = useState("");
    const [surname, setSurname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [gender, setGender] = useState("");
    const [age, setAge] = useState("");
    const [address, setAddress] = useState("");

    const navigate = useNavigate();

    const onSubmit = (event) => {
        event.preventDefault();
        handleRegister(username, surname, email, password, gender,age, address )
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
          name="username"
          onChange={({ target }) => setUsername(target.value)}
        />
      </div>

      <div className="surname-box">
        <div className="surname">Apellido</div>
        <input
          id="surname"
          type="text"
          value={surname}
          name="surname"
          onChange={({ target }) => setSurname(target.value)}
        />
      </div>

      <div className="email-box">
        <div className="email">Email</div>
        <input
          id="email"
          type="email"
          value={email}
          name="email"
          onChange={({ target }) => setEmail(target.value)}
        />
      </div>

      <div className="password-box">
        <div className="password">Contraseña</div>
        <input
          id="password"
          type="password"
          value={password}
          name="password"
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

        <div className="age-box">
        <div className="age">Edad</div>
        <input
          id="age"
          type="text"
          value={age}
          name="age"
          onChange={({ target }) => setAge(target.value)}
        />
      </div>  

      <div className="address-box">
        <div className="address">Direccion</div>
        <input
          id="address"
          type="text"
          value={address}
          name="address"
          onChange={({ target }) => setAddress(target.value)}
        />
      </div>

      <Link to="/login">
      <SubmitButton
        type="navigation"
        text="Cancelar"
        backgroundColor="#ff0000"
        backgroundColorHover="#ff3020"
      />
      </Link>

      <SubmitButton
        type="submit"
        text="Registrarse"
        backgroundColor="#4CAF50"
        backgroundColorHover="#45a049"
      />
    </form>
  </div>
  )
}



