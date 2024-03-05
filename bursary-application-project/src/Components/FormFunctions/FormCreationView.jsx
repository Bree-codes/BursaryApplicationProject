import CreateFormSection from "./CreateFormSection.jsx";
import {useState} from "react";

/*fieldName, setFieldName,fieldType, setFieldType, section, setSection, onAddForm, onRemoveForm*/
const FormCreationView = () =>{

    const [fieldName, setFieldName] = useState("");
    const [fieldType, setFieldType] = useState("");
    const [section, setSection] = useState("");

    const onAddForm = () =>{

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
                               onAddForm={onAddForm}
                               onRemoveForm={onRemoveForm}
            />
        </>
    );
}

export default FormCreationView;