import axios from "axios";
const baseUrl = "http://localhost:8080/users/passChange";

const changePassword = async (credentials) => {
  const response = await axios.post(baseUrl, credentials); //Las credentials son pasadas como un objeto JSON
  return response.data;
};

export default { changePassword };