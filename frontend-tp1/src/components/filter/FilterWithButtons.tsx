import { Button } from '../ui/Button';
import './filterWithButtons.css'
export const FilterWithButtons = ({ buttons }) => {
  return (
    <div className='button-filter-wrapper'>
      {buttons.map((button, index) => (
        <Button 
          key={index} 
          text={button.text} 
          handleAction={button.action} 
          backgroundColor={button.backgroundColor} 
          backgroundColorHover={button.backgroundColorHover} 
        />
      ))}
    </div>
  );
};
