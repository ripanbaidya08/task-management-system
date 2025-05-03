import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { api, BASE_URL, setAuthHeader } from "../api/api";

// It is highly recommended to user meaningful names for the actions and thunks
// whatever you name the action, it will use after the prefix "auth/"
// for example: auth/login, auth/logout, auth/register, auth/refreshToken, etc.
export const login = createAsyncThunk("auth/login", async (userData) => {
    try {
        const {data} = await axios.post(`${BASE_URL}/auth/login`, userData)
        localStorage.setItem("jwt", data.jwt); // Store token in local storage
        console.log("Login successful:", data); // Log the response data
        return data; // Return the response data to be used in the slice
    }
    catch (error) {
        // Handle error appropriately, e.g., show a notification or log it
        console.error("Login error:", error);
        throw Error(error.response.data.error); // Throw an error to be caught in the slice
    }
});

export const register = createAsyncThunk("auth/register", async (userData) => {
    try {
        const {data} = await axios.post(`${BASE_URL}/auth/signup`, userData)
        localStorage.setItem("jwt", data.jwt); // Store token in local storage
        console.log("Registration successful:", data); // Log the response data
        return data; // Return the response data to be used in the slice
    }
    catch (error) {
        // Handle error appropriately, e.g., show a notification or log it
        console.error("Login error:", error);
    }
});

// logout action
export const logout = createAsyncThunk("auth/logout", async (userData) => {
    try {
        localStorage.removeItem("jwt"); // Remove token from local storage
        console.log("Logout successful"); // Log the response data
    }
    catch (error) {
        // Handle error appropriately, e.g., show a notification or log it
        console.error("Logout error:", error);
        throw Error(error.response.data.error);

    }
});

export const getUserProfile = createAsyncThunk("auth/getUserProfile", async (jwt) => {
    setAuthHeader(jwt, api); // Set the Authorization header with the JWT token
    try {
        const {data} = await axios.get(`${BASE_URL}/api/users/profile`)
        console.log("User profile fetched successfully:", data); // Log the response data
        return data; // Return the response data to be used in the slice
    }
    catch (error) {
        // Handle error appropriately, e.g., show a notification or log it
        console.error("Get user profile error:", error);
        throw Error(error.response.data.error);
    }
});

export const getAllUsers = createAsyncThunk("auth/getAllUsers", async (jwt) => {
    setAuthHeader(jwt, api); // Set the Authorization header with the JWT token
    try {
        /**
         * If i Use the api instance, then it will automatically add the Authorization header to the request.
         * If i use the axios instance, then i have to set the Authorization header manually.
         */
        const {data} = await api.get(`/api/users`)
        console.log("All users fetched successfully:", data); // Log the response data
        return data; // Return the response data to be used in the slice
    }
    catch (error) {
        // Handle error appropriately, e.g., show a notification or log it
        console.error("Get all users error:", error);
        throw Error(error.response.data.error);
    }
});


const authSlice = createSlice({
    name: "auth",
    initialState: {
        user: null,
        loggedIn: false,
        loading: false,
        error: null,
        token: null,
        users: []
    },
    reducers: {
        setUser: (state, action) => {
            state.user = action.payload;
        },
        setToken: (state, action) => {
            state.token = action.payload;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(login.pending, (state) => {
                state.loading = true;
                state.error = null; // Reset error state on new login attempt
            })
            .addCase(login.fulfilled, (state, action) => {
                state.loading = false;
                state.jwt = action.payload.jwt; // Assuming the response contains the JWT token
                state.loggedIn = true;
                state.user = action.payload.user; // Assuming the response contains user data
                state.token = action.payload.jwt; // Assuming the response contains the JWT token
            })
            .addCase.apply(login.rejected, (state, action) => {
                state.loading = false;
                state.error = action.error.message; // Set error message on login failure
            })
            
            // similar for register, logout, getUserProfile, and getAllUsers
            // do this things after some time.
            // -2.22.28
            
    },
});