import { Link } from 'react-router-dom';
import './userNavSideBar.css';

const iconProducts = "/public/assets/caja_productos.svg";
const iconOrder = "public/assets/bolsa_productos.svg"
const iconUsers = "public/assets/user.svg";

export const UserNavSideBar = () => {
  return (
    <div className='nav-side-bar-wrapper'>
      <div className='nav-section'>
        
        <Link to="/user-products">
        <div className='nav-item-box'>
          <img src={iconProducts} alt="Users Icon" className="nav-icon" /> Productos
        </div>
        </Link>

        <Link to="/user-orders">
        <div className='nav-item-box'>
          <img src={iconOrder} alt="Users Icon" className="nav-icon" /> Pedidos
        </div>
        </Link>

        <Link to="/user-confirmed-orders">
        <div className='nav-item-box'>
          <img src={iconOrder} alt="Users Icon" className="nav-icon" /> Pedidos Confirmados
        </div>
        </Link>

        <Link to="/user-profile">
        <div className='nav-item-box'>
          <img src={iconUsers} alt="Users Icon" className="nav-icon" /> Usuario
        </div>
        </Link>
      </div>
    </div>
  );
};
