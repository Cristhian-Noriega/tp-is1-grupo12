import { Link } from 'react-router-dom';
import './adminNavSideBar.css';
import { Button } from '../../ui/Button';
import {Context} from '../../../context/Context'
import { useContext } from 'react';
const iconProducts = "/public/assets/caja_productos.svg";
const iconOrder = "/public/assets/bolsa_productos.svg"
const iconUsers = "/public/assets/user.svg";

export const AdminNavSideBar = () => {

  const { handleLogout} = useContext(Context)

  return (
    <div className='nav-side-bar-wrapper'>
      <div className='nav-section'>
        
        <Link to="/admin/products">
        <div className='nav-item-box'>
          <img src={iconProducts} alt="Users Icon" className="nav-icon" /> Productos
        </div>
        </Link>

        <Link to="/admin/orders">
        <div className='nav-item-box'>
          <img src={iconOrder} alt="Users Icon" className="nav-icon" /> Pedidos
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
