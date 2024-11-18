import axios from "axios";
const baseUrl = "http://localhost:8080/products";

const getAll = async () => {
  const response = await axios.get(baseUrl);
  return response.data;
};

const getByAttributes = async (attributes) => {
  console.log("service getByAttributes")
  console.log(attributes)
  const response = await axios.get(`${baseUrl}/search`, attributes,
    { headers: { "Content-Type": "application/json" } }
  )
} 

export default {
  getAll, getByAttributes
};
