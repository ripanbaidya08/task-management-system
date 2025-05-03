import axios from 'axios';

/**
 * Since our application is build using monolithic architecture, so that we will have different port for each sevice.
 * We have to give the backend URL of API-GATEWAY in order to access the backend.
 * 
 * Here, is the base URL of the API-GATEWAY.
 * You can change the port number according to your local setup.
 */
export const BASE_URL = "http://localhost:8000";

export const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  }
});

/**
 * Sets or removes the Authorization header for API requests.
 *
 * @param {string} token - The authentication token to be set. If null or undefined, the header is removed.
 * @param {object} api - The axios instance where the Authorization header should be set.
 */
export const setAuthHeader = (token, api) => {
    if (token) {
        api.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    } else {
        delete api.defaults.headers.common['Authorization'];
    }
}
