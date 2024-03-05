import CreateFormSection from "./CreateFormSection.jsx";
import { useState } from "react";
import { Button } from "react-bootstrap";
import { createForm } from "../../Resources/ApiResources.js";
import Modal from "../Modal.jsx";

const FormCreationView = () => {
    const [fieldName, setFieldName] = useState("");
    const [fieldType, setFieldType] = useState("");
    const [section, setSection] = useState("");
    const [month, setMonth] = useState("");
    const [sectionField, setSectionField] = useState({});
    const [modalMessage, setModalMessage] = useState("");

    const onAddForm = () => {
        if (fieldType === "" || fieldName === "") {
            setModalMessage("The FieldType or The fieldName should not be empty");
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

    const onRemoveForm = () => {
        // Implement removal logic if needed
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

    return (
        <>
            {modalMessage && <Modal message={modalMessage} />}
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
            />
            <Button
                style={{ background: "lime", border: "none" }}
                onClick={onSubmitForm}
            >
                Submit Form
            </Button>
        </>
    );
};

export default FormCreationView;
