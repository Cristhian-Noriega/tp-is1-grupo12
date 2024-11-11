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

const getByUserId = async (userId) => {
  const response = await axios.get(`baseUrl/${userId}`);
  return response.data;
};

const create = async (orderObject) => {
    const response = await axios.post(`${baseUrl}/create`, orderObject);
  return response.data;
  };

export default {
  getAll, create, getByUserId
};
