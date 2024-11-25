import React, { useState, useContext } from "react";
import { SubmitButton } from "../ui/SubmitButton";
import { Context} from '../../context/Context';
import "./productEditStock.css"

export const ProductEditStock = ({ product, editProductStock, handleCloseCard}) => {
  const [productStock, setProductStock] = useState(0);
  const { setProducts, setShowMessage} = useContext(Context)



  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await editProductStock(product.id,productStock);
      handleUpdateStock(product.id,productStock)
      setShowMessage({
        text: `Se actualizo el stock correctamente del producto ${product.name} (id: ${product.id})`,
        type: "success",
        duration: 3000
      })
      handleCloseCard();
    } catch (error) {
      setShowMessage({
        text: error,
        type: "error",
        duration: 3000
      })
    }
  };

  const handleUpdateStock = (productId, newStock) => {
    setProducts((prevProducts) =>
      prevProducts.map((product) =>
        product.id === productId
          ? { ...product, stock: newStock } // Actualiza solo el producto que coincide
          : product // Mantén el resto igual
      )
);
  };

  return (
    <div className="product-edit-stock-wrapper">
      <form onSubmit={handleSubmit} className="product-edit-stock-form">
        <h2 className="product-form-heading">Actualizar Stock del Producto</h2>

        <div className="product-form-field">
          <label>Nuevo stock de {product.name} (ID: {product.id})</label>
          <input
            type="number"
            value={productStock}
            onChange={(e) => setProductStock(e.target.value)}
            className="product-form-input"
            min="0"
          
          />
        </div>

        
        <SubmitButton
          type="submit"
          text="Actualizar Stock"
          backgroundColor="var(--buton-submit)"
          backgroundColorHover="var(--buton-submit-hover)"
        />
      </form>
      {productStock && (
        <div className="product-update-stock-box">
         El producto {product.name} tendra un stock disponible de {productStock} unidades.
        </div>
)}
    </div>
  );
};

