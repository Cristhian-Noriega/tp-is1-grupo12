import { useEffect } from 'react';
import PropTypes from 'prop-types';
import './cardMessage.css'; // Archivo CSS para estilos

const CardMessage = ({ messageProperties, onClose}) => {
  
  useEffect(() => {
    const timer = setTimeout(onClose, messageProperties.duration);
    return () => clearTimeout(timer); // Limpiar el temporizador al desmontar
  }, [onClose, messageProperties.duration]);

  return (
    <div className={`card-message ${messageProperties.type}`}>
      <p>{messageProperties.text}</p>
      <button className="close-button" onClick={onClose}>
        &times;
      </button>
    </div>
  );
};

export default CardMessage;
