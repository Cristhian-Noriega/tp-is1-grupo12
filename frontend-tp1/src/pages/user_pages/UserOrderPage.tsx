import "./productsSelectionPage.css"
import { UserOrdersTable } from '../../components/orders/user_orders/UserOrdersTable';
import { UserLayout } from '../../components/user_layout/UserLayout';


export const UserOrderPage = () => {
    
  return (
    <div className='order-page-wrapper'>
        <UserLayout/>
        <UserOrdersTable/>
        
    </div>
  );
};
