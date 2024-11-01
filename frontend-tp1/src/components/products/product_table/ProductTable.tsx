import { ProductItem } from '../product_item/ProductItem';
import { ProductTableHeader } from '../product_table_header/ProductTableHeader'
import './productTable.css'
export const ProductTable = () => {
    const products = [
        { 
            name: 'Producto 1', 
            description: 'Descripción del producto 1', 
            brand: 'Marca A', 
            category: 'Categoría 1', 
            price: 100, 
            stock: 20, 
            active: true 
        },
        { 
            name: 'Producto 2', 
            description: 'Descripción del producto 2', 
            brand: 'Marca B', 
            category: 'Categoría 2', 
            price: 200, 
            stock: 0, 
            active: false 
        },
       
    ];
  return (
    <div className='product-table-header-wrapper'>
    <table>
      <ProductTableHeader/>
      <tbody>
      {products.map((product, index) => (
                        <ProductItem key={index} product={product} />
                    ))}
      </tbody>
    </table>
    </div>
  )
}
