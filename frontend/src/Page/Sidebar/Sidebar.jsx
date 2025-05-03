import React, { useState } from 'react';
import {Avatar, Button} from "@mui/material";
import "./Sidebar.css";
import CreateNewTaskForm from "../Task/TaskCard/CreateTask";
import { useLocation, useNavigate } from 'react-router-dom';

const menu = [
    {name: "Home", value: "Home", role: ["ROLE_ADMIN", "ROLE_CUSTOMER"]},
    {name: "Done", value: "Done", role: ["ROLE_ADMIN", "ROLE_CUSTOMER"]},
    {name: "Assigned", value: "Assigned", role: ["ROLE_ADMIN"]},
    {name: "Not Assigned", value: "Pending", role: ["ROLE_ADMIN"]},
    {name: "Create Task", value: "", role: ["ROLE_ADMIN"]},
    {name: "Notification", value:"Notification", role: ["ROLE_CUSTOMER"]}
];

const Sidebar = () => {
  // hooks from react-router-dom
  const location = useLocation();
  // React hooks from react-router-dom
  // name must be start with word "use"
  const navigate = useNavigate();
  
  const [openCreateTaskForm, setOpenCreateTaskForm] = useState(false);
  const handleCloseCreateTaskForm = () => {
    setOpenCreateTaskForm(false);
  };
  const handleOpenCreateTaskModel = () => {
    setOpenCreateTaskForm(true);
  };

  const [activeMenu, setActiveMenu] = useState("Home");

  const handleMenuChange = (item) => {
    const updatedParams = new URLSearchParams(location.search);

    if (item.name === "Create Task") {
      handleOpenCreateTaskModel();
    }

    // navigate to home page and remove filter query param
    else if (item.name === "Home") {
      updatedParams.delete("filter");
      const queryString = updatedParams.toString();
      const updatedPath = queryString
        ? `${location.pathname}?${queryString}`
        : location.pathname;
      navigate(updatedPath);
    } else {
      updatedParams.set("filter", item.value);
      navigate(`${location.pathname}?${updatedParams.toString()}`);
    }
    setActiveMenu(item.name);
  };
  const handleLogout = () => {
    console.log("handle logout");
  };
  return (
    <>
      <div className="card min-h-[85vh] flex flex-col justify-center w-[280px]">
        <div className="space-y-5 h-full">
          <div className="flex justify-center">
            <Avatar
              sx={{ width: "8rem", height: "8rem" }}
              className="border-2 border-[#c24dd0]"
              src="/images/task-management-web-banner.png"
            />
          </div>
          {menu
            .filter((item) => item.role.includes(role))
            .map((item) => (
              <p
                key={item.name}
                onClick={() => handleMenuChange(item)}
                className={`py-3 px-5 rounded-full text-center cursor-pointer ${
                  activeMenu === item.name ? "activeMenuItem" : "menuItem"
                }`}
              >
                {item.name}
              </p>
            ))}
          <Button
            onClick={handleLogout}
            sx={{ padding: ".7rem", borderRadius: "2rem" }}
            fullWidth
            className="logoutButton"
          >
            Logout
          </Button>
        </div>
      </div>
      <CreateNewTaskForm
        open={openCreateTaskForm}
        handleClose={handleCloseCreateTaskForm}
      />
    </>
  );
}
const role = "ROLE_ADMIN";

export default Sidebar;