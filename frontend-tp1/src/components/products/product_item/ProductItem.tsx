import './productItem.css';
const editButton = "/public/assets/edit.svg";
const deleteButton = "/public/assets/delete.svg";
const infoButton = "/public/assets/info.svg";

export const ProductItem = ({ product, onShowEditStock,deleteProduct, onShowDetails }) => {

  const handleDelete = () => {
    if (window.confirm(`Seguro que quieres eliminar el producto: ${product.name}?`)) {
      deleteProduct(product.id);
    }
  };

  return (
    <tr>
      <td>{product.name}</td>
      <td>{product.stock}</td>
      <td>{product.brand}</td>
      <td>{product.description}</td>
      <td>
        <button className='action-button' onClick={() => onShowEditStock(product)}><img src={editButton} alt="BOTON INFO" className="nav-icon" /></button>
        <button className='action-button' onClick={handleDelete}><img src={deleteButton} alt="BOTON ELIMINAR" className="nav-icon" /></button>
        <button className='action-button' onClick={() => onShowDetails(product)}><img src={infoButton} alt="BOTON INFO" className="nav-icon" /></button>
      </td>
    </tr> 
  );
}
