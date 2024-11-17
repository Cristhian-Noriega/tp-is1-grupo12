import { FilterWithButtons } from "../filter/FilterWithButtons";
import { userOrdersUtils } from '../../utils/userOrdersUtils';
import "./orderStatusFilter.css"

export const OrderStatusFilter = () => {
  const { setOrders, originalOrders} = userOrdersUtils();

  const buttons = [
    {
      text: "Todas",
      action: () => handleShowAll(),
      backgroundColor: "#000", // verde
      backgroundColorHover: "#111",
    },
    {
      text: "Confirmadas",
      action: () => handleFilterOrdersButton("CONFIRMED"),
      backgroundColor: "#28a745", // verde
      backgroundColorHover: "#218838",
    },
    {
      text: "En proceso",
      action: () => handleFilterOrdersButton("PROCESSING"),
      backgroundColor: "#ffc107", // amarillo
      backgroundColorHover: "#e0a800",
    },
    {
      text: "Enviadas",
      action: () => handleFilterOrdersButton("SENT"),
      backgroundColor: "#007bff", // azul
      backgroundColorHover: "#0069d9",
    },
    {
      text: "Canceladas",
      action: () => handleFilterOrdersButton("CANCELED"),
      backgroundColor: "#dc3545", // rojo
      backgroundColorHover: "#c82333",
    },
  ];

  const handleFilterOrdersButton = (orderState) => {
    console.log(originalOrders)
    const filteredOrders = originalOrders.filter((order) => order.state === orderState);
    setOrders(filteredOrders);
    console.log(`Se filtraron las ordenes por el estado: ${orderState}`)
    console.log(filteredOrders)
  };
  const  handleShowAll = () => {
    
    setOrders(originalOrders)
  }

  return (
    <div className="filter-buttons-wrapper">
      <h2>Filtrar órdenes por estado</h2>
      <FilterWithButtons buttons={buttons} />
    </div>
  );
};
