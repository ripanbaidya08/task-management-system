import React, {useState} from 'react';
import {IconButton, MenuItem, Menu} from "@mui/material";
import MoreVertIcon from '@mui/icons-material/MoreVert';
import UserList from "./UserList";
import SubmissionList from "./SubmissionList";
import EditTaskForm from "./EditTaskForm";

const role = "ROLE_ADMIN";

const TaskCard = () => {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const openMenu = Boolean(anchorEl);
    const handleMenuClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    // create all methods for open user list, submission list, update task model and delete task
    const [openUserList, setOpenUserList] = useState(false);
    const handleCloseUserList = () => {
        setOpenUserList(false);
    }
    const handleOpenUserList = () => {
        setOpenUserList(true);
        handleMenuClose()
    }

    const [openSubmissionList, setOpenSubmissionList] = useState(false);
    const handleCloseSubmissionList = () => {
        setOpenSubmissionList(false);
    }
    const handleOpenSubmissionList = () => {
        setOpenSubmissionList(true);
        handleMenuClose()
    }

    const [openUpdateTaskForm, setOpenUpdateTaskForm] = useState(false);
    const handleCloseUpdateTaskForm = () => {
        setOpenUpdateTaskForm(false);
    }
    const handleOpenUpdateTaskModel = () => {
        setOpenUpdateTaskForm(true);
        handleMenuClose();
    }
    const handleDeleteTask = () => {
        handleMenuClose();
    }
    return (
        <div className="relative bg-[#1e1e2f] rounded-2xl p-4 shadow-lg text-white w-full">
            {/* Icon Button positioned in top-right corner */}
            <div className="absolute top-3 right-3">
                <IconButton id="basic-button"
                            aria-controls={openMenu ? 'basic-menu' : undefined}
                            aria-haspopup="true"
                            aria-expanded={openMenu ? 'true' : undefined}
                            onClick={handleMenuClick}>
                    <MoreVertIcon className="text-white" />
                </IconButton>
                <Menu
                    id="basic-menu"
                    anchorEl={anchorEl}
                    open={openMenu}
                    onClose={handleMenuClose}
                    MenuListProps={{
                        'aria-labelledby': 'basic-button',
                    }}
                >

                    {
                        role==='ROLE_ADMIN' ? <>
                            <MenuItem onClick={handleOpenUserList}>Assign Task</MenuItem>
                            <MenuItem onClick={handleOpenSubmissionList}>See Submission</MenuItem>
                            <MenuItem onClick={handleOpenUpdateTaskModel}>Edit</MenuItem>
                            <MenuItem onClick={handleDeleteTask}>Delete</MenuItem>
                        </> : <>
                        </>
                    }
                </Menu>
            </div>

            <div className="flex gap-5 items-center">
                <img
                    className="w-28 h-28 object-cover rounded-xl"
                    src="https://images.pexels.com/photos/164634/pexels-photo-164634.jpeg?auto=compress&cs=tinysrgb&w=600"
                    alt="Project"
                />
                <div className="flex flex-col gap-3">
                    <div>
                        <h1 className="text-xl font-bold">Car Rental Website</h1>
                        <p className="text-sm text-gray-400 max-w-md">
                            This is a car rental website built with SpringBoot Backend and React frontend.
                        </p>
                    </div>
                    <div className="flex flex-wrap gap-2">
                        {['React', 'SpringBoot', 'PostgreSQL', 'Docker'].map((tech, index) => (
                            <span key={index}
                                className="bg-gradient-to-r from-purple-500 to-pink-500 text-white text-xs px-4 py-1 rounded-full shadow-md"
                            >
                                {tech}
                            </span>
                        ))}
                    </div>
                </div>
            </div>
            <UserList open={openUserList} handleClose={handleCloseUserList}/>
            <SubmissionList open={openSubmissionList} handleClose={handleCloseSubmissionList}/>
            <EditTaskForm open={openUpdateTaskForm} handleClose={handleCloseUpdateTaskForm}/>
        </div>
    );
};

export default TaskCard;
