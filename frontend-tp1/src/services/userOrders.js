import axios from "axios";
const baseUrl = "http://localhost:8080/orders";

const getAll = async () => {
  const response = await axios.get(baseUrl);
  return response.data;
};

const cancelOrderByUserId = async(userId, orderId) => {

  const response = await axios.post(`${baseUrl}/${orderId}/cancelByUser?userId=${userId}`)
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

const getAvailableCommands = async(orderId) =>{
  const response = await axios.get(`${baseUrl}/${orderId}/availableCommands`)
  return response.data
}

const executeCommand = async(commandObject,orderId) =>{
  const response = await axios.post(`${baseUrl}/${orderId}/executeCommand`, commandObject)
  return response.data
}

export default {
  getAll, create, getByUserId, cancelOrderByUserId, getAvailableCommands, executeCommand
};
