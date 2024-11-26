import axios from "axios";
const baseUrl = "http://localhost:8080/orders";

let token = null;

const setToken =(newToken) => {
  token = newToken 
} 

const getAll = async () => {
  console.log("getting with tioken", token)
  const response = await axios.get(baseUrl, { headers: { "Authorization": `Bearer ${token}` } });
  return response.data;
};

const cancelOrderByUserId = async(orderId) => {
  const response = await axios.post(`${baseUrl}/${orderId}/cancelByUser`,orderId,
     { headers: { "Authorization": `Bearer ${token}` } })
     return response
}
  

const getByUserId = async () => {
  const response = await axios.get(`${baseUrl}/user`,
    { headers: { "Authorization": `Bearer ${token}` } }
  ); 
  return response.data;
};

const deleteOrder = async (orderId) => {
  const response = await axios.delete(`${baseUrl}/orderId`,
    { headers: { "Authorization": `Bearer ${token}` } }
  );
  return response.data;
};

const create = async (orderObject) => {
    const response = await axios.post(`${baseUrl}/create`, orderObject,
      { headers: { "Authorization": `Bearer ${token}` } }
    );
    return response.data;
  };

const getAvailableCommands = async(orderId) =>{
  const response = await axios.get(`${baseUrl}/${orderId}/availableCommands`,
    { headers: { "Authorization": `Bearer ${token}` } }
  )
  return response.data
}

const executeCommand = async(commandObject,orderId) =>{
  const response = await axios.post(`${baseUrl}/${orderId}/executeCommand`, commandObject,
    { headers: { "Authorization": `Bearer ${token}` } }
  )
  return response.data
}

export default {
  getAll, create, getByUserId, cancelOrderByUserId, getAvailableCommands, executeCommand, setToken, deleteOrder
};
