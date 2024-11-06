import axios from "axios";
const baseUrl = "http://localhost:8080/products/admin";

const create = async (productObject) => {
  const config = {
    headers: { Authorization: token },
  };

  const response = await axios.post(baseUrl, productObject, config);
  return response.data;
};

const remove = async (id) => {
  const response = await axios.delete(`${baseUrl}/${id}`);
  return response.data;
};

const update = async (id, newObject) => {
  const response = await axios.put(`${baseUrl}/${id}`, newObject);
  return response.data;
};

export default {
  create,
  update,
  remove,
};
