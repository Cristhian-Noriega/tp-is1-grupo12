import './navSideBar.css';

// Definimos cada ruta de los iconos
const iconHome = "public/assets/home.svg";
const iconProducts = "/public/assets/caja_productos.svg";
const iconOrder = "public/assets/bolsa_productos.svg"
const iconUsers = "public/assets/user.svg";

export const NavSideBar = () => {
  return (
    <div className='nav-side-bar-wrapper'>
      <div className='nav-section'>
        <div className='nav-item-box'>
          <img src={iconHome} alt="Home Icon" className="nav-icon" /> Home
        </div>
        <div className='nav-item-box'>
          <img src={iconProducts} alt="Users Icon" className="nav-icon" /> Productos
        </div>
        <div className='nav-item-box'>
          <img src={iconOrder} alt="Users Icon" className="nav-icon" /> Pedidos
        </div>
        <div className='nav-item-box'>
          <img src={iconUsers} alt="Users Icon" className="nav-icon" /> Usuario
        </div>
      </div>
    </div>
  );
};
