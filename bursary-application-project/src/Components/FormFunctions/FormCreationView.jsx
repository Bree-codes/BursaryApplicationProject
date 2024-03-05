import CreateFormSection from "./CreateFormSection.jsx";
import {useState} from "react";
import {Button} from "react-bootstrap";
import {createForm} from "../../Resources/ApiResources.js";

const FormCreationView = () =>{

    const [fieldName, setFieldName] = useState("");
    const [fieldType, setFieldType] = useState("");
    const [section, setSection] = useState("");
    const [month, setMonth] = useState("");
    const [sectionField, setSectionField] = useState({});


    const onAddForm = () =>{
        setSectionField( fields =>({
            ...fields, [fieldName] : fieldType
        }))
        console.log(sectionField);
    }

    const onRemoveForm = () =>{

    }

    const onSubmitForm = () =>{
        console.log("sending data to the backend");
        createForm(section,month,localStorage.getItem('id'),sectionField).then()

    }


    return(
        <>
            <CreateFormSection fieldName={fieldName}
                               setFieldName={setFieldName}
                               fieldType={fieldType}
                               setFieldType={setFieldType}
                               section={section}
                               setSection={setSection}
                               month={month}
                               setMonth={setMonth}
                               onAddForm={onAddForm}
                               onRemoveForm={onRemoveForm}/>
            <Button style={{background:'lime', border:'none',}}
                    onClick={onSubmitForm}>Submit Form</Button>

        </>
    );
}

export default FormCreationView;