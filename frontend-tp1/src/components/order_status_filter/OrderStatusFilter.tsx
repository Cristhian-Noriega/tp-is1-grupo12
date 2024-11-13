import { FilterWithButtons } from "../filter/FilterWithButtons";
import { userOrdersUtils } from '../../utils/userOrdersUtils';
import { useState } from "react";

export const OrderStatusFilter = () => {
  const { orders, setOrders, originalOrders, setOriginalsOrders} = userOrdersUtils();
 

  const buttons = [
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
      action: () => handleFilterOrdersButton("CANCEL"),
      backgroundColor: "#dc3545", // rojo
      backgroundColorHover: "#c82333",
    },
  ];

  const handleFilterOrdersButton = (orderState) => {
    const filteredOrders = originalOrders.filter((order) => order.state === orderState);
    setOrders(filteredOrders);
    console.log(`Se filtraron las ordenes por el estado: ${orderState}`)
    console.log(orders)
  };

  return (
    <div>
      <h2>Filtrar órdenes por estado</h2>
      <FilterWithButtons buttons={buttons} />
    </div>
  );
};
