import React from 'react'

export const Button = ({text, handleAction,backgroundColor, backgroundColorHover}) => {
  const [isHovered, setIsHovered] = React.useState(false);

  const handleMouseEnter = () => setIsHovered(true);
  const handleMouseLeave = () => setIsHovered(false);

  const buttonStyle = {
    backgroundColor: isHovered ? backgroundColorHover : backgroundColor,
    width: '95%',
    height: '40px',
    padding: '10px 20px',
    border: 'none',
    borderRadius: '4px',
    color: '#f0f0f0',
    fontSize: '16px',
    fontWeight: '600',
    cursor: 'pointer',
    transition: 'background-color 0.3s ease',
    marginTop: '20px',
    justifyContent: 'center',
    whiteSpace: 'nowrap',
  };

  return (
    <div>
        <button 
        style={buttonStyle}
        onMouseEnter={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
        onClick={() => handleAction()}
        >
        {text}
        </button>
    </div>
  )
}
