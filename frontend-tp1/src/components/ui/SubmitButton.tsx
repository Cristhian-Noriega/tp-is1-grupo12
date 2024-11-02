import React from 'react'

export const SubmitButton = ({type,text, backgroundColor, backgroundColorHover}) => {
  const [isHovered, setIsHovered] = React.useState(false);

  const handleMouseEnter = () => setIsHovered(true);
  const handleMouseLeave = () => setIsHovered(false);

  const buttonStyle = {
    backgroundColor: isHovered ? backgroundColorHover : backgroundColor,
    width: '95%',
    padding: '10px',
    border: 'none',
    borderRadius: '4px',
    color: '#f0f0f0',
    fontSize: '16px',
    fontWeight: '600',
    cursor: 'pointer',
    transition: 'background-color 0.3s ease',
    marginTop: '20px',
  };

  return (
    <div>
        <button 
        type={type}
        style={buttonStyle}
        onMouseEnter={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
        >
        {text}
        </button>
    </div>
  )
}
