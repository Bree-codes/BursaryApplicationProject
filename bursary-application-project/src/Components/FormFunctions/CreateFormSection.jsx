import InputComponent from "../InputComponent.jsx";
import ChoiceInputComponent from "../ChoiceInputComponent.jsx";
import {Button} from "react-bootstrap";

// eslint-disable-next-line react/prop-types
const CreateFormSection = ({fieldName, setFieldName,fieldType, setFieldType, section, setSection, onAddForm, onRemoveForm}) =>{
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
                                            onChange={(e) => setFieldName(e.target.value)}
                                            placeHolder={"e.g Student Full Name"}
                                            value={fieldName}/>

                        </td>
                        <td>
                             <InputComponent filedName={"Field Section : "}
                                            type={"text"}
                                            onChange={(e) => setSection(e.target.value)}
                                            placeHolder={"e.g Section A"}
                                            value={section}/>
                        </td>
                    </tr>
                <tr>
                    <td colSpan={2}>
                        <ChoiceInputComponent  onChange={(e) => setFieldType(e.target.value)}
                                               options={["text", "checkbox", "password", "file"]}
                                               value={fieldType}/>
                    </td>
                </tr>
                <tr>
                   <td>
                       <Button onClick={onAddForm}
                           className="m-0 p-2 justify-content-center container-fluid"
                               style={{
                                   outline:"none",
                                   borderRadius: '10px',
                                   display: 'flex',
                                   justifyContent: 'center',
                                   alignItems: 'center',
                               }}>Add Field</Button>
                   </td>
                    <td>
                        <Button onClick={onRemoveForm}
                            className="m-0 p-2 justify-content-center container-fluid"
                                style={{
                                    outline:"none",
                                    borderRadius: '10px',
                                    display: 'flex',
                                    justifyContent: 'center',
                                    alignItems: 'center',
                                }}>Remove Field</Button>
                    </td>
                </tr>
                </tbody>
            </table>

        </>
    )
}
export default  CreateFormSection;
