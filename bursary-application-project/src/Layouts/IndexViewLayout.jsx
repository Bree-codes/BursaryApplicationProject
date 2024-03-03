import OffCanvas from "../Components/OffCanvas.jsx";
import {Col, Container, Nav, Navbar, NavLink} from "react-bootstrap";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";

const IndexViewLayout = () =>{
    return(
        <>
            <PageTemplateNavigationBar />
            <Container>
                <Col md={"auto"} className={"m-3 p-3 shadow "}
                style={{
                    background:'grey',
                    height:"4em"
                }}>
                    <NavLink></NavLink>
                </Col>
            </Container>


        </>
    );
}

export default IndexViewLayout;
