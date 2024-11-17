import { useContext } from 'react';
import "./userCurrentOrderTable.css"
import { CurrentOrderContext} from '../../../context/CurrentOrderContext';
import { Context} from '../../../context/Context';
import userOrdersService from '../../../services/userOrders'
import { Button } from '../../ui/Button';
const deleteButton = "/public/assets/delete.svg";

export const UserCurrentOrderTable = () => {
    
      const { currentOrder, removeProductFromOrder, clearOrder } = useContext(CurrentOrderContext);
      const { user, handleAddProduct, setShowMessage} = useContext(Context)


      const createOrderPayload = () => {
        return {
          userId: "lucas.ezequiel.321@gmail.com", //TODO: User solamente devuelve el token
          items: currentOrder.map(item => ({
            productId: item.id,
            quantity: item.quantity,
          })),
        };
      };

      const handleRemoveProduct = (productId) =>{
        removeProductFromOrder(productId)
        handleAddProduct(productId)
        setShowMessage({
          text: `El producto con ID ${productId} ha sido eliminado de la orden actual.`,
          type: "info",
          duration: 3000
        })
      }

      const createOrder = async () => {
        try {
          
          const orderPayLoad = createOrderPayload()
          console.log("DTOORDER")
          console.log(orderPayLoad)
          const orderResponse = await userOrdersService.create(
            orderPayLoad
          );
         clearOrder()
         setShowMessage({
          text: `Orden creada exitosamente.`,
          type: "success",
          duration: 3000
        })
        } catch (error) {
          console.log("error" + error);
        }
      }
  return (
    <div className='user-order-table-wrapper'>
        <table>
        <thead>
          <tr>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {currentOrder.map(product => (
            <tr key={product.id}>
              <td>{product.name}</td>
              <td>{product.quantity}</td>
              <td>
                <button className='action-button' 
                onClick={() => handleRemoveProduct(product.id)}
                >
                  <img src={deleteButton} alt="BOTON ELIMINAR" className="nav-icon" />
                </button>
              </td>
            </tr>
            
          ))}
          
              
        </tbody>
        
      </table>
      <div className='button-create-order-container'>
      <Button
          text="Crear Orden" 
           backgroundColor="#488A32"
            backgroundColorHover="#48FA30"
            handleAction={createOrder}/>
      </div>
      
    </div>
  )
}
