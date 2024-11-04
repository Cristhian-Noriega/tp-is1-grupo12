import axios from "axios";
const baseUrl = "http://localhost:8080/users/register";

const register = async (userRegistrationData) => {
  const response = await axios.post(baseUrl, userRegistrationData); //Las credentials son pasadas como un objeto JSON
  return response.data;
};

export default { register };