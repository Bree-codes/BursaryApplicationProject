import {InputGroup, Form, Row, Col} from "react-bootstrap";

// eslint-disable-next-line react/prop-types
const  ConsentInput = ({isChecked, setIsChecked, value}) =>{
    return(
        <InputGroup  className={"mb-2 p-2 justify-content-center container-fluid"}>
            <Row>
                <Col md="auto">
                    <InputGroup.Checkbox onChange={
                        (e) => setIsChecked(e.target.checked)}
                                         aria-label="Checkbox for following text input"
                    checked={isChecked}/>
                </Col>
                <Col md="auto">
                    <Form.Control aria-label="Text input with checkbox"
                                  value={value}
                                  disabled={true}/>
                </Col>
            </Row>
        </InputGroup>
    )
}

export default ConsentInput;
