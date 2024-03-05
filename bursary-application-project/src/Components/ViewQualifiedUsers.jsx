import {useEffect, useState} from "react";
import {Alert, Col, Container, Row} from "react-bootstrap";
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
        <Container fluid={"md"} style={{ background: "wheat" }} className={"d-flex"}>
            {qualifiedUsers ? Object.entries(qualifiedUsers).map(([name, phoneNumber], index) => (
                <Row key={index}>
                    <Col>{name}</Col>
                    <Col>{phoneNumber}</Col>
                </Row>
            )) : <Alert className={"alert-block"}>{error}</Alert>}
        </Container>
    );
};

export default ViewQualifiedUsers;
