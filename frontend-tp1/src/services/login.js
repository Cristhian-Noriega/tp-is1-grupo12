import axios from "axios";

const baseUrl = "http://localhost:8080/auth/login";

const login = async (credentials) => {
  const response = await axios.post(baseUrl, credentials); //Las credentials son pasadas como un objeto JSON
  return response.data;
  
};

export default { login };