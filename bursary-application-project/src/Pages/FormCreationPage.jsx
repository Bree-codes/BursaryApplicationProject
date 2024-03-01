import CreateFormSection from "../Components/FormFunctions/CreateFormSection.jsx";
import {useState} from "react";

const FormCreationPage = () =>
{
    const [fieldType, setFieldType] = useState("");
    const [section, setSection] = useState("")
    const [fieldName, setFieldName] = useState("")


   return(
       <div >
        <CreateFormSection
            fieldName={fieldName}
            setFieldName={setFieldName}
            fieldType={fieldType}
            setFieldType={setFieldType}
            section={section}
            setSection={setSection}/>
   </div>);
}

export default FormCreationPage;