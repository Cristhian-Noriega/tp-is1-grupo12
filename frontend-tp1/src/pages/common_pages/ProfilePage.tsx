import React, { useContext, useState } from 'react'
import { UserNavSideBar } from '../../components/nav_side_bar/user_nav_side_bar/UserNavSideBar'
import { AdminNavSideBar } from '../../components/nav_side_bar/admin_nav_side_bar/AdminNavSideBar'
import './ProfilePage.css'
import userService from '../../services/user'
import { useEffect } from 'react';
import { Context } from "../../context/Context"
const profilePicture = "/public/assets/logo.jpg";

export const ProfilePage = () => {

    const { getUserFromLocalStorage, user } = useContext(Context);
    const [userData, setUserData] = useState({ })

    useEffect(() => {
        getUserFromLocalStorage();
        }, []);

    useEffect(() => {
        userService.setToken(user.token);
        userService.getPublicProfile().then((user) => setUserData(user));
    }, []);


  return (
    <div className="profile-page-container" >
        {user.role === "ADMIN" ? (
                <AdminNavSideBar />
            ) : (
                <UserNavSideBar />
            )}

        <div className='profile-info-container'>
            <h1 className="profile-title">Perfil de Usuario</h1>
            <div id='profile-picture-container'>
                    <img src={profilePicture}/>
            </div>  
            <div >
                <div className='profile-data-box'>
                    <p>Email: {userData.email}</p>
                </div>  
                
                <div className='profile-data-box'>
                    <p>Nombre: {userData.name}</p>
                </div>

                <div className='profile-data-box'>
                    <p>Apellido: {userData.surname}</p> 
                </div>

                <div className='profile-data-box'>
                    <p>Edad: {userData.age}</p>
                </div>

                <div className='profile-data-box'>
                    <p>Genero: {userData.gender}</p>
                </div>

                <div className='profile-data-box'>
                    <p>Direccion: {userData.address}</p>
                </div>

                <div className='profile-data-box'>
                    <p>Rol: {userData.role}</p>
                </div>
                
            </div>
        </div>
    </div>

  )
}
