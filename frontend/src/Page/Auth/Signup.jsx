import React, { useState } from "react";
import {
  TextField,
  Button,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";

const Signup = ({ togglePanel }) => {
  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
    role: "",
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
    console.log("Signup form data:", formData);
    // Integrate your registration API here
  };

  return (
    <div>
      <h1 className="form-title">Register</h1>
      <form onSubmit={handleSubmit} className="form">
        <TextField
          label="Full Name"
          name="fullName"
          value={formData.fullName}
          variant="outlined"
          fullWidth
          onChange={handleChange}
          placeholder="Enter your full name"
          sx={{ marginBottom: "16px" }}
        />
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
        <FormControl fullWidth sx={{ marginBottom: "16px" }}>
          <InputLabel id="role-label">Role</InputLabel>
          <Select
            labelId="role-label"
            id="role-select"
            value={formData.role}
            label="Role"
            name="role"
            onChange={handleChange}
          >
            <MenuItem value={"ROLE_ADMIN"}>ADMIN</MenuItem>
            <MenuItem value={"ROLE_CUSTOMER"}>USER</MenuItem>
          </Select>
        </FormControl>
        <Button
          className="submit-button"
          variant="contained"
          fullWidth
          type="submit"
          sx={{ py: 1.5, mt: 1 }}
        >
          Register
        </Button>
        <div className="toggle-link">
          <span>Already have an account?</span>
          <Button onClick={togglePanel}>Signin</Button>
        </div>
      </form>
    </div>
  );
};

export default Signup;
