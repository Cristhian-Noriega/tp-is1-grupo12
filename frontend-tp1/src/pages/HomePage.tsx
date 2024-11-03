import './homePage.css';
import { NavSideBar } from '../components/nav_side_bar/NavSideBar';
import { ProductTable } from '../components/products/product_table/ProductTable';

export const HomePage = () => {

  return (
    <div className='home-page-wrapper'>
      <NavSideBar />
      <ProductTable />
    </div>
  );
}
