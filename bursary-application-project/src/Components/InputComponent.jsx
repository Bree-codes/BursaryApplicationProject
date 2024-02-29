import {InputGroup, Form} from "react-bootstrap";
import "./../ComponetStyles/InputComponentStyles.css"


const InputComponent = (props) =>{
    return(
        <>
            <InputGroup className={"m-2 p-2"}>
            <table>
                <tbody>
                <tr>
                    <td>
                        <InputGroup.Text className={"m-2"} id="inputGroup-sizing-default">
                            {/* eslint-disable-next-line react/prop-types */}
                            {props.filedName}
                        </InputGroup.Text>
                    </td>
                    <td>
                        <Form.Control className={"w-auto m-2"}
                            aria-label="Default"
                            aria-describedby="inputGroup-sizing-default"
                            /* eslint-disable-next-line react/prop-types */
                            type={props.type} onChange={props.onChange}
                                      /* eslint-disable-next-line react/prop-types */
                            placeholder={props.placeHolder}
                                      /* eslint-disable-next-line react/prop-types */
                            required={props.required}/>
                    </td>
            </tr>
                </tbody>
            </table>
            </InputGroup>
        </>
    );
}
export default InputComponent;