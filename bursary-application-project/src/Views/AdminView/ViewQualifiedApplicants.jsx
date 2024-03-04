import {Alert, Button, Card} from "react-bootstrap";
import {useEffect, useState} from "react";
import {getQualifiedStudents} from "../../Resources/ApiResources.js";

const ViewQualifiedApplicants = () =>{

    const [qualifiedUsers, setQualifiedUsers] = useState({});
    const [bursaryTitle, setBursaryTitle] = useState(null);
    const [error, setError] = useState(null);
    useEffect(() => {

        getQualifiedStudents().then(
            res =>{
                setQualifiedUsers(res.data);
                if(qualifiedUsers === {}){
                    setBursaryTitle("No Qualified Applicants Yet")
                }else
                {
                    const number = (qualifiedUsers.key.length);
                    setBursaryTitle(number+" Student(s) Qualified");
                }

            }).catch(
                error =>{
                    setError(error.response.data)
                }
        );

    }, []);



    return (
        <div className="d-flex justify-content-around">
            {error && <Alert>{error}</Alert>}
            <Card style={{width: '18rem'}}>
                <Card.Img variant="top" src="holder.js/100px180"/>
                <Card.Body>
                    <Card.Title>Resent Bursary</Card.Title>
                    <Card.Text>
                        {bursaryTitle && <div>{bursaryTitle}</div>}
                    </Card.Text>
                    <Button variant="primary">View Qualified</Button>
                </Card.Body>
            </Card>
            <Card style={{width: '18rem'}}>
                <Card.Img variant="top" src="holder.js/100px180"/>
                <Card.Body>
                    <Card.Title>Past Bursaries</Card.Title>
                    <Card.Text>
                        Some quick example text to build on the card title and make up the
                        bulk of the card's content.
                    </Card.Text>
                    <Button variant="primary">Get Past Qualified</Button>
                </Card.Body>
            </Card>

        </div>
    );
}
export default ViewQualifiedApplicants;