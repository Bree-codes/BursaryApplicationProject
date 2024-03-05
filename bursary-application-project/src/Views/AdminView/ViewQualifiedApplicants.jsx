import {Alert, Button, Card, Container} from "react-bootstrap";
import {useEffect, useState} from "react";
import {getQualifiedStudents} from "../../Resources/ApiResources.js";
import image9 from './../../Images/image9.jpg';
import image10 from './../../Images/image10.jpg';
import {useNavigate} from "react-router";

const ViewQualifiedApplicants = () =>{

    const [qualifiedUsers, setQualifiedUsers] = useState({});
    const [bursaryTitle, setBursaryTitle] = useState(null);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    useEffect(() => {
        getQualifiedStudents().then(
            res =>{
                setQualifiedUsers(res.data);

                const number = Object.keys(qualifiedUsers).length;
                setBursaryTitle(number+" Student(s) Qualified");

            }).catch(
                error =>{
                    setError(error.response.data.message)
                }
        );

    }, [qualifiedUsers]);

    const viewQualified = () =>{
        console.log("moving to view resent qualified students");
        navigate("")
    }



    return (
        <Container style={{background:"ghostwhite", }} className={"z-1 shadow"}>
            <div className={"align-content-center justify-content-center"}>{error && <Alert
                style={{color:"wheat", border:"none"}}
                className={"p-2 mt-3 m-2 bg-danger"}>{
                error}</Alert>}</div>
            <div className="d-flex justify-content-around m-4 p-4">
                <Card style={{width: '18rem', background:"gold"}} className={"shadow z-3"}>
                    <Card.Img variant="top" src={image9}/>
                    <Card.Body>
                        <Card.Title>Resent Bursary</Card.Title>
                        <Card.Text>
                            {bursaryTitle ? <div>{bursaryTitle}</div> : <div>
                                Get The Names Of Qualified Students In The Resent Bursary
                            </div>}
                        </Card.Text>
                        <Button variant="primary" onClick={}>View Qualified</Button>
                    </Card.Body>
                </Card>
                <Card style={{width: '18rem', background:"gold"}} className={"shadow z-3 "}>
                    <Card.Img variant="top" src={image10}/>
                    <Card.Body>
                        <Card.Title>Past Bursaries</Card.Title>
                        <Card.Text>
                           Get The Past Qualified Students By Bursary Month and Year.
                        </Card.Text>
                        <Button variant="primary">Get Past Qualified</Button>
                    </Card.Body>
                </Card>
            </div>

            <hr />
        </Container>
    );
}
export default ViewQualifiedApplicants;