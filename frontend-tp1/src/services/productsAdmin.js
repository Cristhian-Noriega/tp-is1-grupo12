import axios from "axios";
const baseUrl = "http://localhost:8080/products";

let token = null;

const setToken =(newToken) => {
  token = newToken 
} 

const create = async (productObject) => {
  console.log("el tokenazo", token)
  console.log(productObject)
  const response = await axios.post(baseUrl, productObject, { headers: { "Authorization": `Bearer ${token}` } });
  return response.data;
};

const remove = async (productId) => {
  console.log("el tokenazo", token)
  const response = await axios.delete(`${baseUrl}/${productId}`, { headers: { "Authorization": `Bearer ${token}` } });
  return response.data;
};

const updateStock = async (id, newStockObject) => {
  console.log("updatin stock")
  console.log("id", id)
  console.log(newStockObject)
  const response = await axios.put(`${baseUrl}/${id}/stock`, newStockObject, { headers: { "Authorization": `Bearer ${token}` } });
  return response.data;
};
const getStockByProductId = async (productId) => {
  console.log("gettin stock")
  console.log(productId)
  const response = await axios.get(`${baseUrl}/${productId}/stock`, { headers: { "Authorization": `Bearer ${token}` } })
  return response.data
}

export default {
  create,
  updateStock,
  remove,
  setToken,
  getStockByProductId
};
