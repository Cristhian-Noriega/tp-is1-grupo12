import React, { useState } from "react";
import "./productFilter.css";
import { SubmitButton } from "../ui/SubmitButton";
const deleteButton = "/public/assets/delete.svg";

export const  ProductFilter = ({ handleGetProductByAttributes}) => {
  const [name, setName] = useState("");
  const [stock, setStock] = useState(0);
  const [brand, setBrand] = useState("");
  const [description, setDescription] = useState("");
  const [extraAtributes, setExtraAttributes] = useState({});
  const [newAttributeKey, setNewAttributeKey] = useState("");
  const [newAttributeValue, setNewAttributeValue] = useState("");

  // Manejo del envío del formulario
  const handleSubmit = (e) => {
    e.preventDefault();

    const attributesPayload = Object.fromEntries(
      Object.entries({
        name,
        brand,
        stock,
        description,
        extraAtributes,
      }).filter(([key, value]) =>
        value && // Excluye valores como "", 0, null, undefined
        (typeof value !== "object" || Object.keys(value).length > 0) // Incluye solo objetos no vacíos
      )
    );
    
    console.log("Payload enviado:", attributesPayload);
    handleGetProductByAttributes(attributesPayload)
  };

  // Manejo de agregar un nuevo atributo extra
  const handleAddExtraAttribute = () => {
    if (newAttributeKey && newAttributeValue) {
      setExtraAttributes({
        ...extraAtributes,
        [newAttributeKey]: newAttributeValue,
      });
      setNewAttributeKey("");
      setNewAttributeValue("");
    }
  };

  // Manejo de eliminar un atributo extra
  const handleRemoveExtraAttribute = (key) => {
    const updatedAttributes = { ...extraAtributes };
    delete updatedAttributes[key];
    setExtraAttributes(updatedAttributes);
  };

  return (
    <div className="product-form-wrapper">
      <form onSubmit={handleSubmit} className="product-form">
        <h2 className="product-form-heading">Filtrar Productos por Atributos</h2>

        {/* Campo Nombre */}
        <div className="product-form-field">
          <label>Nombre</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="product-form-input"
          
          />
        </div>

        {/* Campo Stock */}
        <div className="product-form-field">
          <label>Stock</label>
          <input
            type="number"
            value={stock}
            onChange={(e) => setStock(Number(e.target.value))}
            className="product-form-input"
            min="0"
          />
        </div>

        {/* Campo Marca */}
        <div className="product-form-field">
          <label>Marca</label>
          <input
            type="text"
            value={brand}
            onChange={(e) => setBrand(e.target.value)}
            className="product-form-input"
          
          />
        </div>

        {/* Campo Descripción */}
        <div className="product-form-field">
          <label>Descripción</label>
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            className="product-form-input"
          />
        </div>

        {/* Campo para Agregar Atributos Extras */}
        <div className="product-form-field">
          <label>Agregar Atributo Extra</label>
          <input
            type="text"
            value={newAttributeKey}
            onChange={(e) => setNewAttributeKey(e.target.value)}
            placeholder="Clave del atributo"
            className="product-form-input"
          />
          <input
            type="text"
            value={newAttributeValue}
            onChange={(e) => setNewAttributeValue(e.target.value)}
            placeholder="Valor del atributo"
            className="product-form-input"
          />
          <button
            type="button"
            onClick={handleAddExtraAttribute}
            className="product-form-button"
          >
            Agregar Atributo
          </button>
        </div>

        <div className="product-form-field">
          <h3>Atributos Extras</h3>
          {Object.entries(extraAtributes).map(([key, value]) => (
            <div key={key} className="product-form-extra-attribute">
              <span>{key}: {value}</span>
              <button type="button"
                onClick={() => handleRemoveExtraAttribute(key)}
                className="product-form-remove-button">
                  <img src={deleteButton} alt="BOTON ELIMINAR" className="remove-extra-attribute-icon" />
                </button>
            </div>
          ))}
        </div>
        

        
        <SubmitButton
          type="submit"
          text="Aplicar Filtro"
          backgroundColor="#000"
          backgroundColorHover="#6F6"
        />
      </form>
    </div>
  );
};

