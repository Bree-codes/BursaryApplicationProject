import {InputGroup, Form, Row, Col} from "react-bootstrap";

const  ConsentInput = (isChecked, setIsChecked) =>{
    return(
        <InputGroup  className={"mb-2 p-2 justify-content-center container-fluid"}>
            <Row>
                <Col md="auto">
                    <InputGroup.Checkbox onChange={(e) => {
                        setIsChecked(e.target.checked)
                    }} aria-label="Checkbox for following text input"
                    checked={isChecked}/>
                </Col>
                <Col md="auto">
                    <Form.Control aria-label="Text input with checkbox"
                                  value={"consent your are 18 years"}
                                  disabled={true}/>
                </Col>
            </Row>
        </InputGroup>
    )
}

export default ConsentInput;
