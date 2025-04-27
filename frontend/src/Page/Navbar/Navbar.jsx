import React from 'react';
import { Avatar } from "@mui/material";
import "./Navbar.css"
export const Navbar = () => {
    return (
        <div className="container z-10 sticky top-0 left-0 right-0 py-3 px-5 lg:px-10 flex justify-between items-center bg-gradient-to-r ">
            {/* Logo */}
            <p className="font-bold text-xl text-white">Organize The Task</p>

            {/* Right side - App Name and Avatar */}
            <div className="flex items-center gap-4">
                <p className="text-white text-base font-medium">Ripan Baidya</p>
                <Avatar src='/images/nav-bar-demo-profile-image.jpeg'>R</Avatar>
            </div>
        </div>
    );
};
