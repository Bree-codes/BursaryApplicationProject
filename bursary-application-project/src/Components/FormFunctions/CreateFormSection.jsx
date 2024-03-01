import InputComponent from "../InputComponent.jsx";
import ChoiceInputComponent from "../ChoiceInputComponent.jsx";

// eslint-disable-next-line react/prop-types
const CreateFormSection = ({fieldName, setFieldName,fieldType, setFieldType, section, setSection}) =>{
    return(
        <>
            <table className={"justify-content-center align-content-center bg-dark m-auto position-fixed"}
                   style={{
                       left:250
                   }}>
                <tbody>
                    <tr>
                        <td>
                            <InputComponent filedName={"Field Name : "}
                                            type={"text"}
                                            onChange={setFieldName}
                                            placeHolder={"e.g Student Full Name"}
                                            value={fieldName}/>

                        </td>
                        <td>
                             <InputComponent filedName={"Field Section : "}
                                            type={"text"}
                                            onChange={setSection}
                                            placeHolder={"e.g Section A"}
                                            value={section}/>
                        </td>
                    </tr>
                <tr>
                    <td colSpan={2}>
                        <ChoiceInputComponent  onChange={setFieldType}
                                               options={["text", "checkbox", "password", "file"]}
                                               value={fieldType}/>
                    </td>
                </tr>
                </tbody>
            </table>

        </>
    )
}

export default  CreateFormSection;
