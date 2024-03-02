import { Form, InputGroup } from 'react-bootstrap';
import './../ComponetStyles/InputComponentStyles.css';


// eslint-disable-next-line react/prop-types
const ChoiceInputComponent = ({options, onChange, value, }) => {


    return (
        <InputGroup className="m-0 p-2 justify-content-center container-fluid">
            <Form.Select
                className="w-auto m-2"
                value={value}
                onChange={onChange}
                aria-label="Default"
                aria-describedby="inputGroup-sizing-default">
                <option value="">Select The Field Type</option>
                {/* eslint-disable-next-line react/prop-types */}
                {options.map((option, index) => (
                    <option key={index} value={option}>
                        {option}
                    </option>
                ))}
            </Form.Select>
        </InputGroup>
    );
};

export default ChoiceInputComponent;
