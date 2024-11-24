import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useEffect } from "react";
import { Context } from '../context/Context'; 
import { useContext } from 'react';

export const ProtectedRoutes = ({ children, role }) => {
  const { user, getUserFromLocalStorage} = useContext(Context); 

  useEffect(() => {
    getUserFromLocalStorage()
  }, []);


  if (!user) {
    return <Navigate to="/login" replace />; 
  }
  if (role && user.role !== role) {
    return <Navigate to="/unauthorized" replace />;
  }
  
  return <Outlet />;
};
