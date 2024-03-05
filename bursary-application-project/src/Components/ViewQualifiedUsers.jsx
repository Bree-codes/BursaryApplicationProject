import {useEffect, useState} from "react";
import {Alert, Col, Container, Row, Stack} from "react-bootstrap";
import {getQualifiedStudents} from "../Resources/ApiResources.js";

// eslint-disable-next-line react/prop-types
const ViewQualifiedUsers = () => {

    const [qualifiedUsers,setQualifiedUsers] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        getQualifiedStudents().then(
            res =>{
                setQualifiedUsers(res.data);

            }).catch(
            error =>{
                setError(error.response.data.message)
            }
        );
    }, [qualifiedUsers]);


    return (
        <Container fluid style={{ background: "wheat" }} className={"d-flex"}>
            <Stack direction={"vertical"} className={"m-2"}>
            {qualifiedUsers ? Object.entries(qualifiedUsers).map(([name, phoneNumber], index) => (
                // eslint-disable-next-line react/jsx-key
                    <Row key={index}>
                        <Col className={"p-3 m-2 "}>{name}</Col>
                        <Col className={"p-3 p-2"}>{phoneNumber}</Col>
                    </Row>
            )) : <Alert className={"alert-block"}>{error}</Alert>}
            </Stack>
        </Container>
    );
};

export default ViewQualifiedUsers;
