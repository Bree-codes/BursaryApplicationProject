import InputComponent from "../InputComponent.jsx";
import ChoiceInputComponent from "../ChoiceInputComponent.jsx";

const CreateFormSection = ({fieldName, setFieldName,fieldType, setFieldType}) =>{

    return(
        <>
            <table className={"justify-content-center align-content-center bg-dark m-auto"}>
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
                                            onChange={setFieldName}
                                            placeHolder={"e.g Section A"}
                                            value={fieldName}/>
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