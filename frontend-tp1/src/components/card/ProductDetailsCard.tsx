import './productDetailsCard.css'
export const ProductDetailsCard = ({title, body}) => {
  return (
    <div>
        <h3>{title}</h3>
        <div className='box'>
          {body}
          </div>
    </div>
  )
}