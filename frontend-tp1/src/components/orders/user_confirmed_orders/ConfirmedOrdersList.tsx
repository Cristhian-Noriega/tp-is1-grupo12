import { Button } from '../../ui/Button';
import './confirmedOrdersList.css'


// Mock de datos de órdenes (puedes moverlo a un archivo separado si lo prefieres)
const mockOrders = [
  {
    userId: "john",
    items: [
      { productId: 102, quantity: 2 },
      { productId: 105, quantity: 1 },
    ],
  },
  {
    userId: "alice",
    items: [
      { productId: 204, quantity: 3 },
      { productId: 207, quantity: 4 },
    ],
  },
];

export const ConfirmedOrdersList = () => {
    const cancelOrder = () => {
        console.log("orden cancelada")
        //usar el service cancel order
        // si no ocurre ningun error entonces puedo eliminar esta orden del front
    }
  return (
    <div className='orders-wrapper'>
      {mockOrders.map((order, index) => (
        <div key={index} className='orders-boxes'>
          <h3>Usuario: {order.userId}</h3>
          <ul>
            {order.items.map((item, itemIndex) => (
              <li key={itemIndex}>
                Producto ID: {item.productId}, Cantidad: {item.quantity}
              </li>
            ))}
          </ul>
          <Button
          text="Cancelar Orden" 
           backgroundColor="#FF6644"
            backgroundColorHover="#FF0000"
            handleAction={cancelOrder}/>
        </div>
      ))}
    </div>
  );
};

