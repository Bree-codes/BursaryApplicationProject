import {Col, Form, InputGroup, Row} from 'react-bootstrap';
import './../ComponetStyles/InputComponentStyles.css';


// eslint-disable-next-line react/prop-types
const ChoiceInputComponent = ({options, onChange, value, fieldName}) => {
    return (
        <Row className={"justify-content-center align-content-center"}>
            <Col md={"auto"}>
            <InputGroup className="md-0 p-2 justify-content-center container-fluid">
                <InputGroup.Text className={"m-2"} id="inputGroup-sizing-default">
                    {fieldName}
                </InputGroup.Text>
                <Form.Select
                    className="w-auto m-2"
                    value={value}
                    onChange={onChange}
                    aria-label="Default"
                    aria-describedby="inputGroup-sizing-default">
                    {/* eslint-disable-next-line react/prop-types */}
                    {options.map((option, index) => (
                        <option key={index} value={option}>
                            {option}
                        </option>
                    ))}
                </Form.Select>
            </InputGroup>
            </Col>
        </Row>
    );
};

export default ChoiceInputComponent;
