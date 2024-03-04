import {Alert, Button, Col, Container, Row, Stack} from "react-bootstrap";
import InputComponent from "./InputComponent.jsx";
import {useState} from "react";
import ChoiceInputComponent from "./ChoiceInputComponent.jsx";
import {addPrivilegedUser} from "../Resources/ApiResources.js";

const AddUserComponent = () =>{
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [role, setRole] = useState(null);
    const [notification, setNotification] = useState(null);


    const handleAddUser = () =>{
        console.log("adding the new user");

        if(role === "Chief")
            setRole(2);
        else if (role === "Viewer")
            setRole(3);

        addPrivilegedUser(username,email,role).then((res) => {
                console.log(res.status)
                setNotification(<Alert className={"bg-success bg-transparent"}>
                {res.data.message}</Alert>);

                setEmail("");
                setRole(null);
                setUsername("");
            })
            .catch(error => {
            // Handle login error
            if (error.response) {
                // The request was made and the server responded with a status code
                // that falls out of the range of 2xx
                console.error('Server responded with an error:', error.response.data);
                console.log('Error Message:', error.response.data.message);
                // You can set the error message state here if needed
                setNotification(<Alert className={"shadow-danger bg-opacity-25 bg-danger"}>
                    {error.response.data.message}</Alert>);
                setEmail("");
                setRole("");
                setUsername("");
            } else if (error.request) {
                // The request was made but no response was received
                console.error('No response received from server:', error.request);
            } else {
                // Something happened in setting up the request that triggered an Error
                console.error('Error setting up the request:', error.message);
            }
        })
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
                {notification}
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
