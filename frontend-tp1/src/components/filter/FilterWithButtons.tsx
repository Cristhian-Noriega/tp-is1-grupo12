import { Button } from '../ui/Button';

export const FilterWithButtons = ({ buttons }) => {
  return (
    <div className='filter-wrapper'>
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
