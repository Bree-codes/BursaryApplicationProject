import {InputGroup, Form} from "react-bootstrap";


const InputComponent = () =>{
    return(
        <>
            <InputGroup size="lg" className={"m-3 w-50 border-opacity-10"}>
                <InputGroup.Text className="px-5 mx-4"
                                 id="inputGroup-sizing-lg">User Name :
                </InputGroup.Text>
                <Form.Control
                    aria-label="Large"
                    aria-describedby="inputGroup-sizing-sm"
                />
            </InputGroup>
        </>
    );
}
export default InputComponent;