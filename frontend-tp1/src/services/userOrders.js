import axios from "axios";
const baseUrl = "http://localhost:8080/orders";

let token = null;

const setToken = (newToken) => {
  token = `bearer ${newToken}`;
};

const getAll = async () => {
  const response = await axios.get(baseUrl);
  return response.data;
};

const deleteOrder = async(orderId) => {
  console.log(`desde el service order id, ${orderId}`)
  console.log(`${baseUrl}/${orderId}/delete`)
  const response = await axios.delete(`${baseUrl}/${orderId}/delete`)
}

const getByUserId = async (userId) => {
  console.log(`${baseUrl}/user/lucas.ezequiel.321@gmail.com`)
  const response = await axios.get(`${baseUrl}/user/lucas.ezequiel.321@gmail.com`); //el userId esta mockeado
  return response.data;
};

const create = async (orderObject) => {
    const response = await axios.post(`${baseUrl}/create`, orderObject);
  return response.data;
  };

export default {
  getAll, create, getByUserId, deleteOrder
};
