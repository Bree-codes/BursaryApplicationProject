import CreateFormSection from "./CreateFormSection.jsx";
import {useState} from "react";

const FormCreationView = () =>{

    const [fieldName, setFieldName] = useState("");
    const [fieldType, setFieldType] = useState("");
    const [section, setSection] = useState("");

    const onAddForm = () =>{
        console.log(fieldName);
    }

    const onRemoveForm = () =>{

    }


    return(
        <>
            <CreateFormSection fieldName={fieldName}
                               setFieldName={setFieldName()}
                               fieldType={fieldType}
                               setFieldType={setFieldType()}
                               section={section}
                               setSection={setSection()}
                               onAddForm={onAddForm}
                               onRemoveForm={onRemoveForm}
            />
        </>
    );
}

export default FormCreationView;