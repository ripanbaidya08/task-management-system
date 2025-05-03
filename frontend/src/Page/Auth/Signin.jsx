import React, { useState } from "react";
import { TextField, Button } from "@mui/material";

const Signin = ({ togglePanel }) => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Login form data:", formData);
    // Integrate your login API here
  };

  return (
    <div>
      <h1 className="form-title">Login</h1>
      <form onSubmit={handleSubmit} className="form">
        <TextField
          label="Email"
          name="email"
          type="email"
          value={formData.email}
          variant="outlined"
          fullWidth
          onChange={handleChange}
          placeholder="Enter your email"
          sx={{ marginBottom: "16px" }}
        />
        <TextField
          label="Password"
          name="password"
          type="password"
          value={formData.password}
          variant="outlined"
          fullWidth
          onChange={handleChange}
          sx={{ marginBottom: "16px" }}
        />
        <Button
          className="submit-button"
          variant="contained"
          fullWidth
          type="submit"
          sx={{ py: 1.5, mt: 1 }}
        >
          Login
        </Button>
        <div className="toggle-link">
          <span>Don't have an account?</span>
          <Button onClick={togglePanel}>
            Signup
          </Button>
        </div>
      </form>
    </div>
  );
};

export default Signin;
