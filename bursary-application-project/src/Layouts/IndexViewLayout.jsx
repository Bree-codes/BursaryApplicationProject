
import {Container} from "react-bootstrap";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import {getApplicationForm} from "../Resources/ApiResources.js";

const IndexViewLayout = () =>{
    const handleApply = () =>{
        //getting the form from the backend.

    }


    return(
        <>
            <PageTemplateNavigationBar handleApply={handleApply()} action={"Apply"}/>
            <Container>

            </Container>


        </>
    );
}

export default IndexViewLayout;
