import axios from "axios";
const baseUrl = "http://localhost:8080/users/requestPassChange";

const sendEmailToChangePassword = async (email) => {
  const baseUrl = `http://localhost:8080/users/requestPassChange?email=${email}`;
  try {
  const response = await axios.post(baseUrl)
  return response.data;
  } catch (error) {
    console.error("Error en la petición:", error);
  }
};


export default { sendEmailToChangePassword };
