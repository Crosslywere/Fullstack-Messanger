import axios from "axios";
import { toast } from "react-toastify";
import type { ErrorResponse } from "../Types";
const api = axios.create({
  baseURL: import.meta.env.VITE_APP_BACKEND_URL,
  withCredentials: true,
  xsrfCookieName: "XSRF-TOKEN",
  xsrfHeaderName: "X-XSRF-TOKEN",
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.data !== undefined) {
      const { status, message } = error.response.data as ErrorResponse;
      toast.error(`Error ${status} - ${message}`, {
        closeOnClick: true,
        closeButton: false,
      });
    }
    return Promise.reject(error);
  },
);

export default api;
