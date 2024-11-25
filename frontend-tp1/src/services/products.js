import axios from "axios";
const baseUrl = "http://localhost:8080/products";
let token = null;

const setToken =(newToken) => {
  token = newToken 
} 

const getAll = async () => {
  const response = await axios.get(baseUrl, 
    { headers: { "Authorization": `Bearer ${token}` } });
  return response.data;
};

const getByAttributes = async (attributes) => {
  console.log("service getByAttributes")
  console.log(attributes)
  console.log("tokoeko",token)
  const response = await axios.get(`${baseUrl}/search`, {
    params: attributes, 
    headers: { 
      Authorization:`Bearer ${token}`
    }
  });
  console.log("dataa respuesta")
  console.log(response)
  return response.data
} 

export default {
  getAll, getByAttributes, setToken
};
