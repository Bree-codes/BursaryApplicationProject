import {Button, Card} from "react-bootstrap";
import {useEffect, useState} from "react";
import {getQualifiedStudents} from "../../Resources/ApiResources.js";

const ViewQualifiedApplicants = () =>{

    const [qualifiedUsers, setQualifiedUsers] = useState({});

    useEffect(() => {

        getQualifiedStudents().then(
            res =>{
                setQualifiedUsers(res.data);
            }).catch(
                error =>{

                }
        )

    }, []);



    return (
        <div className="d-flex justify-content-around">
            {}
            <Card style={{width: '18rem'}}>
                <Card.Img variant="top" src="holder.js/100px180"/>
                <Card.Body>
                    <Card.Title>Card Title</Card.Title>
                    <Card.Text>
                        Some quick example text to build on the card title and make up the
                        bulk of the card's content.
                    </Card.Text>
                    <Button variant="primary">Go somewhere</Button>
                </Card.Body>
            </Card>
            <Card style={{width: '18rem'}}>
                <Card.Img variant="top" src="holder.js/100px180"/>
                <Card.Body>
                    <Card.Title>Card Title</Card.Title>
                    <Card.Text>
                        Some quick example text to build on the card title and make up the
                        bulk of the card's content.
                    </Card.Text>
                    <Button variant="primary">Go somewhere</Button>
                </Card.Body>
            </Card>

        </div>
    );
}
export default ViewQualifiedApplicants;