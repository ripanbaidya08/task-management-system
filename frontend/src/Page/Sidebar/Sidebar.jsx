import React, { useState } from 'react';
import {Avatar, Button} from "@mui/material";
import "./Sidebar.css";

const menu = [
    {name: "Home", value: "Home", role: ["ROLE_ADMIN", "ROLE_CUSTOMER"]},
    {name: "Done", value: "Done", role: ["ROLE_ADMIN", "ROLE_CUSTOMER"]},
    {name: "Assigned", value: "Assigned", role: ["ROLE_ADMIN"]},
    {name: "Not Assigned", value: "Pending", role: ["ROLE_ADMIN"]},
    {name: "Create Task", value: "", role: ["ROLE_ADMIN"]},
    {name: "Notification", value:"Notification", role: ["ROLE_CUSTOMER"]}
];

const role = "ROLE_ADMIN";
const Sidebar = () => {
    const [activeMenu, setActiveMenu] = useState("Home");
    const handleLogout = () => {
        console.log("handle logout")
    }
    return (
        <div className="card min-h-[85vh] flex flex-col justify-center w-[280px]">
        <div className='space-y-5 h-full'>
                <div className='flex justify-center'>
                    <Avatar
                        sx={{ width: "8rem", height: "8rem" }}
                        className='border-2 border-[#c24dd0]'
                        src='/images/task-management-web-banner.png'
                    />
                </div>
                {
                    menu.filter((item) => item.role.includes(role))
                        .map((item) => (
                            <p
                                key={item.name}
                                onClick={() => setActiveMenu(item.name)}
                                className={`py-3 px-5 rounded-full text-center cursor-pointer ${activeMenu === item.name ? "activeMenuItem" : "menuItem"}`}
                            >
                                {item.name}
                            </p>
                        ))
                }
                <Button onClick={handleLogout} sx={{padding:".7rem", borderRadius:"2rem"}} fullWidth className='logoutButton'>
                    Logout
                </Button>
            </div>
        </div>
    );
}

export default Sidebar;