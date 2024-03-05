import { useState} from "react";
import { Container } from "react-bootstrap";
import { getApplicationForm } from "../Resources/ApiResources.js";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import GetFormDisplay from "../Components/FormFunctions/GetFormDisplay.jsx";

const IndexViewLayout = () => {
    const [studentForm, setStudentForm] = useState(null);

    const handleApply = () => {
        getApplicationForm()
            .then((res) => {
                setStudentForm(res.data);
            })
            .catch((error) => {
                // Handle error
                console.error('Error fetching form:', error);
            });
    };

   /* useEffect(() => {
        handleApply(); // Fetch form data when component mounts
    }, []); // Empty dependency array to ensure it only runs once
*/
    return (
        <>
            <PageTemplateNavigationBar handleApply={handleApply} action={"Apply"} />
            <Container style={{
                background:'grey'
            }}>
                <GetFormDisplay studentForm={studentForm}/>
            </Container>
        </>
    );
};

export default IndexViewLayout;
