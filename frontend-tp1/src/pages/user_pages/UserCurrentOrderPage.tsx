import "./productsSelectionPage.css"
import { UserCurrentOrderTable } from '../../components/orders/user_orders/UserCurrentOrderTable';
import { UserLayout } from '../../components/user_layout/UserLayout';


export const UserCurrentOrderPage = () => {
    
  return (
    <div className='order-page-wrapper'>
        <UserLayout/>
        <UserCurrentOrderTable/>
        
    </div>
  );
};
