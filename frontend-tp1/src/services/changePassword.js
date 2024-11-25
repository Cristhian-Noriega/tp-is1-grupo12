import axios from "axios";
const baseUrl = "http://localhost:8080/users/passChange";

const changePassword = async (credentials) => {
  console.log("nuevas credeciales")
  console.log(credentials)
  const response = await axios.patch(baseUrl, credentials); //Las credentials son pasadas como un objeto JSON
  console.log("contrasenia cambiada")
  return response.data;
};

export default { changePassword };