import './productItem.css';
const editButton = "/public/assets/add.svg";
const deleteButton = "/public/assets/delete.svg";
const infoButton = "/public/assets/info.svg";

export const ProductItem = ({ product,deleteProduct, onShowDetails }) => {

  const handleDelete = () => {
    if (window.confirm(`Seguro que quieres eliminar el contacto ${product.name}?`)) {
      deleteProduct(product.id);
    }
  };

  return (
    <tr>
      <td>{product.name}</td>
      <td>{product.stock}</td>
      <td>{product.brand}</td>
      <td>{product.description}</td>
      <td>{product.state === 'confirm' && 'Confirmado'}
      {product.state === 'processing' && 'En Proceso'}
      {product.state === 'shipped' && 'Enviado'}
      {product.state === 'canceled' && 'Cancelado'}
      </td>
      <td>
        <button className='action-button'><img src={editButton} alt="BOTON EDITAR" className="nav-icon" /></button>
        <button className='action-button' onClick={handleDelete}><img src={deleteButton} alt="BOTON ELIMINAR" className="nav-icon" /></button>
        <button className='action-button' onClick={() => onShowDetails(product)}><img src={infoButton} alt="BOTON INFO" className="nav-icon" /></button>
      </td>
    </tr>
  );
}
