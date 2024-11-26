import axios from "axios";
const baseUrl = "http://localhost:8080/auth/register";

const register = async (userRegistrationData) => {
  console.log(userRegistrationData);
  const response = await axios.post(
    baseUrl, 
    userRegistrationData,
    { headers: { "Content-Type": "application/json" } }
  
  ); //Las credentials son pasadas como un objeto JSON
  console.log(response);
  return response;
};

export default { register };