import React, { useState } from "react";
import "./createProductForm.css";
const deleteButton = "/public/assets/delete.svg";
import productsAdminService from "../../../services/productsAdmin";

const CreateProductForm: React.FC = () => {
  const [name, setName] = useState("");
  const [stock, setStock] = useState(0);
  const [brand, setBrand] = useState("");
  const [description, setDescription] = useState("");
  const [extraAttributes, setExtraAttributes] = useState<{ [key: string]: string }>({});
  const [newAttributeKey, setNewAttributeKey] = useState("");
  const [newAttributeValue, setNewAttributeValue] = useState("");

  const handleCreateProduct = async (product) => {
    try {
      console.log(product)
      const productResponse = await productsAdminService.create({
        product
      });
      // setProductos(products.concat(productResponse));
    } catch (error) {
      console.log("error" + error);
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const product = {
      name,
      stock,
      brand,
      description,
      extraAttributes,
    };
    console.log("Producto:", product);
    handleCreateProduct(product)
  };

  const handleAddExtraAttribute = () => {
    if (newAttributeKey && newAttributeValue) {
      setExtraAttributes({
        ...extraAttributes,
        [newAttributeKey]: newAttributeValue,
      });
      setNewAttributeKey("");
      setNewAttributeValue("");
    }
  };

  const handleRemoveExtraAttribute = (key: string) => {
    const updatedAttributes = { ...extraAttributes };
    delete updatedAttributes[key];
    setExtraAttributes(updatedAttributes);
  };

  return (
    <div className="product-form-wrapper">
      <form onSubmit={handleSubmit} className="product-form">
        <h2 className="product-form-heading">Atributos del producto</h2>
        
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
          {Object.entries(extraAttributes).map(([key, value]) => (
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

        <button type="submit" id="save-product-button" className="product-form-button">
          Guardar Producto
        </button>
      </form>
    </div>
  );
};

export default CreateProductForm;
