import {Col, Row} from "react-bootstrap";
import InputComponent from "./InputComponent.jsx";
import {useState} from "react";

const AddUserComponent = () =>{
    const [username, setUsername] = useState("");



    return(
        <Row>
            <Col>
                <InputComponent filedName={"Username"}
                                type={"text"}
                                placeHolder={"eg. Chief Kimathi Location"}
                                onChange={(e) => setUsername(e.target.value)}
                                value={username}
                                />
            </Col>
        </Row>
    )
}

export default AddUserComponent;
