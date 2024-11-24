import React, { useState } from "react";
import "./productStockSearch.css";
import { SubmitButton } from "../ui/SubmitButton";

export const ProductStockSearch = ({ handleGetStockByProductId}) => {
  const [productId, setProductId] = useState("");
  const [productStock, setProductStock] = useState(null);


  const handleSubmit = async (e) => {
    e.preventDefault();
  
    console.log(productId);
    try {
      const stock = await handleGetStockByProductId(productId);
      setProductStock(stock); 
    } catch (error) {
      console.error("Error al obtener el stock:", error);
      setProductStock(null);
    }
  };

  return (
    <div className="product-serach-by-id-form-wrapper">
      <form onSubmit={handleSubmit} className="product-serach-by-id-form">
        <h2 className="product-form-heading">Buscar Stock por ID</h2>

        <div className="product-form-field">
          <label>Buscar Stock por ID</label>
          <input
            type="number"
            value={productId}
            onChange={(e) => setProductId(e.target.value)}
            className="product-form-input"
            min="0"
          
          />
        </div>

        
        <SubmitButton
          type="submit"
          text="Buscar"
          backgroundColor="#000"
          backgroundColorHover="#6F6"
        />
      </form>
      {productStock && (
        <div className="product-stock-box">
         El producto con ID {productId} tiene un stock disponible de {productStock} unidades.
        </div>
)}
    </div>
  );
};

