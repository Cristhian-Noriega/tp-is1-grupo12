import React from 'react'
import "./overlayFocus.css"
export const OverlayFocus = ({handleCloseCard}) => {
  return (
    <>
        <div className="overlay" onClick={handleCloseCard}></div>
        </>
  )
}
