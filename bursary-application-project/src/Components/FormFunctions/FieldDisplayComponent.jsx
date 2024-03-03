import axios from 'axios';
import InputComponent from "../InputComponent.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";
import ConsentInput from "../ConsentInput.jsx";
import { useState } from "react";

// eslint-disable-next-line react/prop-types
const FieldDisplayComponent = ({ field }) => {
    const [activeField, setActiveField] = useState(null);
    const [form, setForm] = useState({});
    const [gender, setGender] = useState("");
    const [isSubmitted, setIsSubmitted] = useState(false);

    const handleFieldClick = (fieldId) => {
        if (isSubmitted) {
            setActiveField(fieldId);
        }
    };

    const handleOnChange = (value) => {
        if (isSubmitted) {
            if (field.fieldInputType === "gender") {
                setGender(value);
            } else {
                setForm({ ...form, [field.fieldName]: value });
            }
        }
    };

    const sendDataToBackend = () => {
        axios.post('/your-backend-endpoint', form)
            .then(response => {
                console.log('Form data sent successfully:', response.data);
                setIsSubmitted(true);
            })
            .catch(error => {
                console.error('Error sending form data:', error);
            });
    };

    return (
        <>
            <div key={field.fieldId} className={"m-2 p-3"} onClick={() => handleFieldClick(field.fieldId)}>
                {(activeField === field.fieldId || !activeField) && (
                    <>
                        {field.fieldInputType === "text" && (
                            <InputComponent
                                fieldName={field.fieldName}
                                type={field.fieldInputType}
                                value={field.fieldValue}
                                onChange={handleOnChange}
                                disabled={!isSubmitted}
                            />
                        )}
                        {field.fieldInputType === "gender" && (
                            <GenderInputComponent
                                gender={field.fieldValue}
                                setGender={handleOnChange}
                                disabled={!isSubmitted}
                            />
                        )}
                        {field.fieldInputType === "consent" && <ConsentInput />}
                        {field.fieldInputType === "password" && (
                            <InputComponent
                                fieldName={field.fieldName}
                                type={field.fieldInputType}
                                value={field.fieldValue}
                                onChange={handleOnChange}
                                disabled={!isSubmitted}
                            />
                        )}
                    </>
                )}
            </div>
            {!isSubmitted && (
                <button onClick={sendDataToBackend}>Submit</button>
            )}
        </>
    );
};

export default FieldDisplayComponent;
