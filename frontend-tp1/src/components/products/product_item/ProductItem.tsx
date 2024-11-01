import React from 'react'
const iconProducts = "/public/assets/caja_productos.svg";
export const ProductItem = ({product}) => {
  return (
    <tr>
    <td>{product.name}</td>
    <td>{product.description}</td>
    <td>{product.brand}</td>
    <td>{product.category}</td>
    <td>{product.price}</td>
    <td>{product.stock}</td>
    <td>{product.active ? 'Sí' : 'No'}</td>
    <td>
        {/* Puedes agregar botones o enlaces para las acciones */}
        <button><img src={iconProducts} alt="Users Icon" className="nav-icon" /></button>
        <button>Eliminar</button>
    </td>
</tr>
  )
}
