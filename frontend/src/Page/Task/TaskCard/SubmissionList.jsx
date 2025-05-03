import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import RefreshIcon from '@mui/icons-material/Refresh';
import SubmissionCard from "./SubmissionCard";

const submissions = [1, 1, 1, 1];

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

// Function to render when there are no submissions
function renderNoSubmission() {
    return(
        <div className="flex flex-col items-center justify-center py-8">
            <Typography variant="h6" color="textSecondary" className="mb-4 font-semibold">
                No Submissions Found!
            </Typography>
            <Button
                variant="outlined"
                color="primary"
                startIcon={<RefreshIcon />}
                className="rounded-md"
            >
                Refresh
            </Button>
        </div>
    );
}
export default function SubmissionList({handleClose, open}) {
    return (
        <div>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <div>
                        {submissions.length > 0 ?
                            <div className='space-y-2'>
                                {
                                    submissions.map((item) => <SubmissionCard/>)
                                }
                            </div>
                            : renderNoSubmission() }
                    </div>
                </Box>
            </Modal>
        </div>
    );
}
