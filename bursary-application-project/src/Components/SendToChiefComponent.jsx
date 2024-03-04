import {Button, Col, Form, InputGroup, Row} from "react-bootstrap";

// eslint-disable-next-line react/prop-types
const SendToChiefComponent = ({chiefUsername, setChiefUsername, handleSend}) =>{
    return(
        <InputGroup  className={"align-content-center justify-content-center m-3"}>
            <Row>
                <Col md={"auto"}><InputGroup.Text>Enter Chief Username : </InputGroup.Text></Col>
                <Col md={"auto"}>
                    <Form.Control
                        placeholder="eg. Chief Kimathi Location."
                        aria-label="Recipient's username"
                        aria-describedby="basic-addon2"
                        value={chiefUsername}
                        onChange={(e) => setChiefUsername(e.target.value)}/>
                </Col>
                <Col md={"auto"}>
                    <Button variant="outline-secondary" id="button-addon2" onClick={handleSend}>
                        Send
                    </Button>
                </Col>
            </Row>
        </InputGroup>
    )
}

export default SendToChiefComponent;