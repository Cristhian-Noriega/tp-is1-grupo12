import React, { useState } from "react";
import "./productFilter.css";
import { SubmitButton } from "../ui/SubmitButton";
const deleteButton = "/public/assets/delete.svg";


const productAttributes = [
  { name: 'Peso', type: 'Double' },
  { name: 'Garantía', type: 'String' },
  { name: 'Litraje', type: 'Integer' },
  { name: 'Color', type: 'String' },
  { name: 'Origen', type: 'String' },
  { name: 'Dimensiones', type: 'String' },
  { name: 'Fabricante', type: 'String' },
  { name: 'Apto para', type: 'String' },
];

export const ProductFilter = ({ createProduct }) => {
  const [name, setName] = useState("");
  const [stock, setStock] = useState(0);
  const [brand, setBrand] = useState("");
  const [description, setDescription] = useState("");
  const [extraAttributes, setExtraAttributes] = useState({});
  const [newAttributeKey, setNewAttributeKey] = useState("");
  const [newAttributeValue, setNewAttributeValue] = useState("");

  const [selectedFilters, setSelectedFilters] = useState({});

  const renderFilterOptions = () => {
    return productAttributes.map((attribute) => (
      <div key={attribute.name} className="filter-option">
        <label>
          <input
            type="checkbox"
            checked={selectedFilters[attribute.name] || false}
            onChange={() => handleFilterChange(attribute.name)}
          />
          {attribute.name} ({attribute.type})
        </label>
      </div>
    ));
  };

  const createExtraAttributesPayload = () => {
    return Object.keys(selectedFilters)
      .filter((key) => selectedFilters[key]) // Filtros seleccionados
      .reduce((payload, attribute) => {
        payload[attribute] = true; // Agrega el atributo al payload
        return payload;
      }, {});
  };

  // Manejo del envío del formulario
  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      name,
      brand,
      stock,
      ...(description),
      extraAttributes: createExtraAttributesPayload(),
    };

    
    console.log("Payload enviado:", payload);
  };

  const handleFilterChange = (attributeName) => {
    setSelectedFilters((prevState) => ({
      ...prevState,
      [attributeName]: !prevState[attributeName], // Toggle entre marcado y desmarcado
    }));
  };

  return (
    <div className="product-form-wrapper">
      <form onSubmit={handleSubmit} className="product-form">
        <h2 className="product-form-heading">Atributos del Producto</h2>

        <div className="product-form-field">
          <label>Nombre</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="product-form-input"
          />
        </div>

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

        <div className="product-form-field">
          <label>Marca</label>
          <input
            type="text"
            value={brand}
            onChange={(e) => setBrand(e.target.value)}
            className="product-form-input"
            
          />
        </div>

        <div className="product-form-field">
          <label>Descripción</label>
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            className="product-form-input"
          />
        </div>
            
        {/* Lista de Atributos Extras */}
        <div className="product-form-field">
          <h3>Atributos Extras</h3>
          <div className="filter-options">{renderFilterOptions()}</div>
        </div>

        <SubmitButton
          type="submit"
          text="Crear Producto"
          backgroundColor="#0F0"
          backgroundColorHover="#0FF"
        />
      </form>
    </div>
  );
};


