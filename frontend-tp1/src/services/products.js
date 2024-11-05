import axios from "axios";
const baseUrl = "http://localhost:8080/products";

const getAll = async () => {
  const response = await axios.get(baseUrl);
  return response.data;
};

const create = async (newObject) => {
  const config = {
    headers: { Authorization: token },
  };

  const response = await axios.post(baseUrl, newObject, config);
  return response.data;
};

const update = async (id, newObject) => {
  const response = await axios.put(`${baseUrl}/${id}`, newObject);
  return response.data;
};

const remove = async (id) => {
  const response = await axios.delete(`admin/${id}`);
  return response.data;
};

// eslint-disable-next-line
export default {
  getAll,
  create,
  update,
  remove,
};