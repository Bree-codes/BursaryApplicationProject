import {Button, Col, Row, Stack} from "react-bootstrap";
import InputComponent from "./InputComponent.jsx";
import {useState} from "react";
import ChoiceInputComponent from "./ChoiceInputComponent.jsx";

const AddUserComponent = () =>{
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [role, setRole] = useState("");


    const handleAddUser = () =>{
        console.log("adding the new user");

    }


    return(
        <Stack direction={"vertical"}
            style={{
                    borderRadius: '10px',
                    boxShadow: '2 5 10px rgba(100, 20, 90, 0.3)',
                    backdropFilter: 'blur(5px)',
                    backgroundColor: 'rgba(15,235,202,0.5)',
                    maxWidth: '400px',
                    margin:'3em'
                }}
                className={"align-content-center justify-content-center p-3"}>
            <Col>
                <h1 style={{
                    color:'grey'
                }}>Add Privileged User </h1>
            </Col>
            <hr />
            <Col md={"auto"} className={"m-1 pt-2"}>
                <InputComponent filedName={"Username"}
                                type={"text"}
                                placeHolder={"eg. Chief Kimathi Location"}
                                onChange={(e) => setUsername(e.target.value)}
                                value={username}
                                />
            </Col>
            <Col md={"auto"} className={"m-1 p-2"}>
                <InputComponent filedName={"User Email "}
                                type={"text"}
                                placeHolder={"eg. brendamukami04@gmail.com"}
                                onChange={(e) => setEmail(e.target.value)}
                                value={email}
                />
            </Col>
            <Col md={"auto"} className={"m-1 p-2"}>
                <ChoiceInputComponent   options={["Chief", "Viewer"]}
                                        onChange={(e) => setRole(e.target.value)}
                                        value={role}
                                        fieldName={"New User's Role"}
                />
            </Col>

            <Col className={"align-content-center justify-content-center"}>
                <Button className={"align-content-center justify-content-center p-2 m-2"}
                        style={{
                            backgroundColor: 'rgba(15,25,20,0.5)',
                            outline:'none',
                            border:"none"
                        }} onClick={handleAddUser}
                >Add User</Button>
            </Col>

        </Stack>
    )
}

export default AddUserComponent;
