import CreateFormSection from "./CreateFormSection.jsx";
import {useState} from "react";
import {Button} from "react-bootstrap";

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
                               onRemoveForm={onRemoveForm}
            />
        </>
    );
}

export default FormCreationView;