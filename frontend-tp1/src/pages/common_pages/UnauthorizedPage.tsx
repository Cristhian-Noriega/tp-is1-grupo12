import { useNavigate } from 'react-router-dom';
import './unauthorizedPage.css'; // Puedes crear estilos personalizados aquí

export const UnauthorizedPage = () => {
  const navigate = useNavigate();

  const handleBackToLogin = () => {
    navigate('/login'); // Redirige a la página de inicio de sesión
  };

  return (
    <div className="unauthorized-container">
      <h1 className="unauthorized-title">Acceso no autorizado</h1>
      <p className="unauthorized-message">
        No tienes permiso para acceder a esta página.
      </p>
      <button className="unauthorized-button" onClick={handleBackToLogin}>
        Volver al inicio de sesión
      </button>
    </div>
  );
};
