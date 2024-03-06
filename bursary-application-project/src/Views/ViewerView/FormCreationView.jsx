import CreateFormSection from "../../Components/FormFunctions/CreateFormSection.jsx";
import {useEffect, useState} from "react";
import {Alert, Button} from "react-bootstrap";
import { createForm } from "../../Resources/ApiResources.js";

const FormCreationView = () => {
    const [fieldName, setFieldName] = useState("");
    const [fieldType, setFieldType] = useState("");
    const [section, setSection] = useState("");
    const [month, setMonth] = useState("");
    const [sectionField, setSectionField] = useState({});
    const [modalMessage, setModalMessage] = useState("");

    const onAddForm = () => {
        console.log(fieldName, fieldType)
        if (fieldType === "" || fieldName === "" || section === "" || month === "") {
            setModalMessage("The FieldType,fieldName,Section or the month field should not be empty");
            return;
        }
        setSectionField((fields) => ({
            ...fields,
            [fieldName]: fieldType
        }));
        console.log(sectionField);
        setFieldName("");
        setFieldType("");
    };


    //this method will handle the removal of unwanted fields.
    const onRemoveForm = () => {
        if(fieldName !== "") {
            if(delete sectionField[fieldName]) {
                setModalMessage("Field Removal Successful.");
                setFieldName("");
                return;
            }
            setModalMessage("Field Not Found");
            return;
        }
        setModalMessage("Please Enter The FieldName Of The Field To Remove");
    };

    const onSubmitForm = () => {
        console.log("sending data to the backend");
        createForm(section, month, localStorage.getItem('id'), sectionField)
            .then((res) => {
                console.log(res.status);
                setModalMessage(res.data.message);
                setSectionField({});
            })
            .catch((error) => {
                console.log(error.response.status);
                setModalMessage(error.response.data.message);
            });
    };

    useEffect(() => {
        if (modalMessage) {
            const timer = setTimeout(() => {
                setModalMessage(null);
            }, 5000);
            return () => clearTimeout(timer);
        }
    }, [modalMessage]);




    return (
        <>
            {modalMessage && <Alert>{modalMessage}</Alert>}
            <CreateFormSection
                fieldName={fieldName}
                setFieldName={setFieldName}
                fieldType={fieldType}
                setFieldType={setFieldType}
                section={section}
                setSection={setSection}
                month={month}
                setMonth={setMonth}
                onAddForm={onAddForm}
                onRemoveForm={onRemoveForm}
                sectionField={sectionField}
                setModalMessage={setModalMessage}
            />
            <Button
                style={{ background: "lime", border: "none" }}
                onClick={onSubmitForm}
            >
                Submit Form
            </Button>
            <></>
        </>
    );
};

export default FormCreationView;
