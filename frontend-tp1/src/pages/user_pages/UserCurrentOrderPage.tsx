import "./productsSelectionPage.css"
import { UserCurrentOrderTable } from '../../components/orders/user_orders/UserCurrentOrderTable';
import { UserLayout } from '../../components/user_layout/UserLayout';
import CardMessage from "../../components/card_message/CardMessagge";
import { Context } from "../../context/Context"
import { useContext, useEffect } from "react";
import userOrdersService from '../../services/userOrders';

export const UserCurrentOrderPage = () => {
  const { showMessage, setShowMessage, user, getUserFromLocalStorage } = useContext(Context);
  
  useEffect(() => {
    getUserFromLocalStorage();
  }, []);

  useEffect(() => {
    if (user.token) { 
    userOrdersService.setToken(user.token)
}}, [user]); 

  return (
    <div className='order-page-wrapper'>
        <UserLayout/>
        <UserCurrentOrderTable/>

        {showMessage && (
        <CardMessage
          messageProperties={showMessage}
          onClose={() => setShowMessage(false)}
        />
      )}
    </div>
    
  );
};
