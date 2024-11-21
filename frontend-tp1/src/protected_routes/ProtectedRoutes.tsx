import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { Context } from '../context/Context'; 
import { useContext } from 'react';

export const ProtectedRoutes = ({ children, role }) => {
  const { user} = useContext(Context); // Obtener los valores del contexto de autenticación

  if (!user) {
    return <Navigate to="/login" replace />; // Si no está autenticado, redirige a la página de login
  }
  if (role && user.role !== role) {
    return <Navigate to="/unauthorized" replace />;
  }
  //user.role !== role
  return <Outlet />;
};
