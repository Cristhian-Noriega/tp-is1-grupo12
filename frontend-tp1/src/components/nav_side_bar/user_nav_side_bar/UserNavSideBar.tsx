import { Link } from 'react-router-dom';
import './userNavSideBar.css';
import { Button } from '../../ui/Button';
import { useContext } from 'react';
import {Context} from '../../../context/Context'
const iconProducts = "/public/assets/caja_productos.svg";
const iconActualOrder = "/public/assets/cart.svg";
const iconMyOrders = "/public/assets/orders.svg"
const iconUsers = "/public/assets/user.svg";

export const UserNavSideBar = () => {
  const { handleLogout} = useContext(Context)

  return (
    <div className='nav-side-bar-wrapper'>
      <div className='nav-section'>
        
        <Link to="/user/products">
        <div className='nav-item-box'>
          <img src={iconProducts} alt="Users Icon" className="nav-icon" /> Productos
        </div>
        </Link>

        <Link to="/user/current-order">
        <div className='nav-item-box'>
          <img src={iconActualOrder} alt="Users Icon" className="nav-icon" /> Orden Actual
        </div>
        </Link>

        <Link to="/user/orders">
        <div className='nav-item-box'>
          <img src={iconMyOrders} alt="Users Icon" className="nav-icon" /> Mis Ordenes
        </div>
        </Link>

        <Link to="/profile">
        <div className='nav-item-box'>
          <img src={iconUsers} alt="Users Icon" className="nav-icon" /> Usuario
        </div>
        </Link>

        <Link to="/login">
        <div className='nav-item-box'>
          <Button
          text="Logout"
          handleAction={() => handleLogout()}
          backgroundColor={"#F00"}
          backgroundColorHover={"000"}
          />
        </div>
        </Link>
      </div>
    </div>
  );
};
