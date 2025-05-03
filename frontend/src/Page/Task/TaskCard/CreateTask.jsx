import dayjs from 'dayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import * as React from 'react';
import {Box, Typography, Modal, TextField, Autocomplete, Button,} from "@mui/material";
import { useState } from "react";


const modalStyle = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '90%',
    maxWidth: 500,
    bgcolor: 'background.paper',
    borderRadius: '12px',
    boxShadow: 8,
    p: 4,
};

const inputStyle = {
    marginBottom: '20px',
};

const tagOptions = [
    "C", "C++", "Java", "Python", "JavaScript", "React", "Node.js", "Spring", "Django",
    "HTML", "CSS", "PHP", "Ruby", "Swift", "Kotlin", "Go", "Rust", "Dart", "TypeScript",
    "C#", "Objective-C", "Visual Basic", "Scala", "Perl", "Shell Scripting"
];

export default function CreateNewTaskForm({ handleClose, open }) {
    const [formData, setFormData] = useState({
        title: "",
        description: "",
        image: "",
        tags: [],
        deadline: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleTagsChange = (event, value) => {
        setFormData((prev) => ({
            ...prev,
            tags: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Form Submitted:", formData);
    };

    return (
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="create-task-title"
            aria-describedby="create-task-description"
        >
            <Box sx={modalStyle}>
                <Typography align="center" variant="h5" sx={{ mb: 2, fontWeight: 600 }}>
                    Create New Task
                </Typography>

                {/* title, image, description, tags, deadline */}
                <form onSubmit={handleSubmit}>
                    <TextField
                        label="Title"
                        variant="outlined"
                        fullWidth
                        name="title"
                        value={formData.title}
                        onChange={handleChange}
                        sx={inputStyle}
                    />

                    <TextField
                        label="Image URL"
                        variant="outlined"
                        fullWidth
                        name="image"
                        value={formData.image}
                        onChange={handleChange}
                        sx={inputStyle}
                    />

                    <TextField
                        label="Description"
                        variant="outlined"
                        fullWidth
                        multiline
                        rows={6}
                        name="description"
                        value={formData.description}
                        onChange={handleChange}
                        sx={{
                            ...inputStyle,
                            '& textarea': { resize: 'none' },
                        }}
                    />

                    <Autocomplete
                        multiple
                        options={tagOptions}
                        value={formData.tags}
                        onChange={handleTagsChange}
                        renderInput={(params) => (
                            <TextField
                                {...params}
                                label="Tags"
                                placeholder="Select tags"
                                variant="outlined"
                                fullWidth
                            />
                        )}
                        sx={{
                            ...inputStyle,
                            '.MuiAutocomplete-inputRoot': {
                                minHeight: 60,
                                alignItems: 'flex-start',
                            },
                        }}
                    />

                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DateTimePicker
                            label="Deadline"
                            value={formData.deadline ? dayjs(formData.deadline) : null}
                            onChange={(newValue) =>
                                setFormData((prev) => ({
                                    ...prev,
                                    deadline: newValue ? newValue.toISOString() : "",
                                }))
                            }
                            slotProps={{
                                textField: {
                                    fullWidth: true,
                                    variant: 'outlined',
                                    sx: inputStyle,
                                }
                            }}
                        />
                    </LocalizationProvider>


                    <Button
                        className="bg-[#c24dd0] hover:bg-[#a61e69] text-white font-bold"
                        variant="contained"
                        fullWidth
                        color="primary"
                        type="submit"
                        sx={{ py: 1.5, mt: 1 }}
                    >
                        Create Task
                    </Button>
                </form>
            </Box>
        </Modal>
    );
}
