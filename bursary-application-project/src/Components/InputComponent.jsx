import {InputGroup, Form} from "react-bootstrap";


const InputComponent = () =>{
    return(
        <>
            <InputGroup className="mb-3">
                <InputGroup.Text id="inputGroup-sizing-default">
                    Default
                </InputGroup.Text>
                <Form.Control
                    aria-label="Default"
                    aria-describedby="inputGroup-sizing-default"
                />
            </InputGroup>
        </>
    );
}
export default InputComponent;