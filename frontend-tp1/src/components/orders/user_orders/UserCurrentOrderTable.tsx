import { useContext } from 'react';
import "./userCurrentOrderTable.css"
import { OrderContext} from '../../../context/OrderContext';
import { Context} from '../../../context/Context';
import userOrdersService from '../../../services/userOrders'
import { Button } from '../../ui/Button';
const deleteButton = "/public/assets/delete.svg";

export const UserCurrentOrderTable = () => {
    
      const { currentOrder, removeProductFromOrder, clearOrder } = useContext(OrderContext);
      const { user, handleAddProduct} = useContext(Context)


      const createOrderPayload = () => {
        return {
          userId: user.id, //TODO: User solamente devuelve el token 
          items: currentOrder.map(item => ({
            productId: item.id,
            quantity: item.quantity,
          })),
        };
      };

      const handleRemoveProduct = (productId) =>{
        removeProductFromOrder(productId)
        handleAddProduct(productId)
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
         console.log(orderResponse)
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
      <Button
          text="Crear Orden" 
           backgroundColor="#488A32"
            backgroundColorHover="#48FA30"
            handleAction={createOrder}/>
    </div>
  )
}
