import CreateFormSection from "../Components/FormFunctions/CreateFormSection.jsx";
import {useState} from "react";

const FormCreationPage = () =>
{
    const [fieldType, setFieldType] = useState("");
    const [section, setSection] = useState("")
    const [fieldName, setFieldName] = useState("")


    const handleAddForm = () => {

    }

    const handleFormRemove = (e) =>{
        e.preventDefault()
        setFieldName("")
        setFieldType("")
    }

   return(
       <div >
        <CreateFormSection
            fieldName={fieldName}
            setFieldName={setFieldName}
            fieldType={fieldType}
            setFieldType={setFieldType}
            section={section}
            setSection={setSection}
            onAddForm={handleAddForm}
            onRemoveForm={handleFormRemove}/>
   </div>);
}

export default FormCreationPage;
