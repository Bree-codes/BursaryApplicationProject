import axios from 'axios';
import InputComponent from "../InputComponent.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";
import ConsentInput from "../ConsentInput.jsx";
import { useState } from "react";

// eslint-disable-next-line react/prop-types
const FieldDisplayComponent = ({ field }) => {
    const [activeField, setActiveField] = useState(null);
    const [form, setForm] = useState({});
    const [gender, setGender] = useState(field.fieldValue);
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [textValue, setTextValue] = useState(field.fieldValue);
    const [consent, setConsent] = useState(false);



    const handleFieldClick = (fieldId) => {
        if (isSubmitted) {
            setActiveField(fieldId);
        }
    };

    const handleOnChange = () => {
        console.log("onchnge called.")
        if (isSubmitted) {
            if (field.fieldInputType === "gender") {
                console.log("gender")
                setForm(
                    [...form,{fieldId : field.fieldId,
                                    fieldValue : gender}]
                )
            } else if (field.fieldInputType === "text"){
                setForm(
                    [...form,{fieldId : field.fieldId,
                                    fieldValue : textValue}]
                )
            }else if (field.fieldInputType === "password"){
                setForm(
                    [...form,{fieldId : field.fieldId,
                                    fieldValue : textValue}]
                )
            }else if (field.fieldInputType === "consent"){
                setForm(
                    [...form,{fieldId : field.fieldId,
                                    fieldValue : consent}]
                )
            }
        }
    };


    return (
        <>
            <div key={field.fieldId} className={"m-2 p-3"} onClick={() => handleFieldClick(field.fieldId)}>
                {(activeField === field.fieldId || !activeField) && (
                    <>
                        {field.fieldInputType === "text" && (
                            <InputComponent
                                filedName={field.fieldName}
                                type={field.fieldInputType}
                                value={textValue}
                                onChange={(e) => {
                                    setTextValue(e.target.value);
                                    handleOnChange();
                                }}
                                disabled={!isSubmitted}
                            />
                        )}
                        {field.fieldInputType === "gender" && (
                            <GenderInputComponent
                                gender={gender}
                                setGender={setGender}
                                onChange={handleOnChange}
                                disabled={!isSubmitted}
                            />
                        )}
                        {field.fieldInputType === "consent" && <ConsentInput
                        isChecked={consent}  setIsChecked={setConsent}  value={field.fieldName}
                        onChange={handleOnChange}/>}

                        {field.fieldInputType === "password" && (
                            <InputComponent
                                filedName={field.fieldName}
                                type={field.fieldInputType}
                                value={textValue}
                                onChange={(e) => {setTextValue(e.target.value)}}
                                disabled={!isSubmitted}
                            />
                        )}
                    </>
                )}
            </div>
        </>
    );
};

export default FieldDisplayComponent;


