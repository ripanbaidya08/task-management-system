import React from "react";
import OpenInNewIcon from '@mui/icons-material/OpenInNew';
import {Button, IconButton} from "@mui/material";
import DoneIcon from '@mui/icons-material/Done';
import CloseIcon from '@mui/icons-material/Close';

const handleAcceptOrDecline =(status) => {
    console.log(status)
}

const SubmissionCard = () => {
    return (
        <div className='rounded-md bg-black p-5 flex-col space-y-4 text-white'>
            {/*Link Section*/}
            <div className='flex items-center justify-between'>
                <div className='space-y-2'>
                    <div className='flex items-center gap-2'>
                        <span className='font-medium'>Github Link</span>
                        <div className='flex items-center gap-2 text-[#c24dd0]'>
                            <OpenInNewIcon fontSize="small" />
                            <a href='https://github.com' target='_blank' rel='noopener noreferrer' className='hover:underline text-sm'>Go to the Link</a>
                        </div>
                    </div>
                    <div className='flex items-center gap-2 text-xs text-gray-400'>
                        <p>Submission time:</p>
                        <p>December 1, 2025 12:00 PM</p>
                    </div>
                </div>
            </div>

            {/* Action Buttons */}
            <div className='flex justify-center gap-10'>
                <div className='text-green-500'>
                    <IconButton color='success' onClick={() => handleAcceptOrDecline("ACCEPTED")}>
                        <DoneIcon />
                    </IconButton>
                </div>
                <div className='text-red-500'>
                    <IconButton color='error' onClick={() => handleAcceptOrDecline("DECLINED")}>
                        <CloseIcon />
                    </IconButton>
                </div>
            </div>
        </div>
    );
};

export default SubmissionCard