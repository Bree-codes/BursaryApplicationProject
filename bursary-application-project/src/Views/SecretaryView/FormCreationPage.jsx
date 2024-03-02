import CreateFormSection from "../../Components/FormFunctions/CreateFormSection.jsx";
import {useEffect, useState} from "react";

const FormCreationPage = () =>
{
    const [formSection, setFormSection] = useState({})
    const [fieldType, setFieldType] = useState("");
    const [section, setSection] = useState("")
    const [fieldName, setFieldName] = useState("")


    const handleAddForm = () => {
        setFormSection([...formSection, {
            fieldName: fieldName,
            fieldType: fieldType
        }]);
    };

    const handleFormRemove = (e) =>{
        e.preventDefault()
        setFieldName("")
        setFieldType("")
    }

    /*If the user enters a new section the previous section is sent to the backend*/
    useEffect(() =>{
    /*Here we will send the content to the backend*/


    }, [section])

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
