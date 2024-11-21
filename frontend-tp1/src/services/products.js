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
  const response = await axios.get(`${baseUrl}/search`, attributes,
    { headers: { "Authorization": `Bearer ${token}` } }
  )
  return response
} 

export default {
  getAll, getByAttributes, setToken
};
