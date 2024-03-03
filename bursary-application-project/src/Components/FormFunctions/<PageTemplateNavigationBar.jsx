import {Container, Nav, Navbar} from "react-bootstrap";
import OffCanvas from "../OffCanvas.jsx";

const PageTemplateNavigationBar = () =>{
    return(
        <>
            <Navbar bg="primary" data-bs-theme="dark">
                <Container>
                    <Navbar.Brand><OffCanvas  placement={"start"} name={"Dashboard "} /></Navbar.Brand>
                    <Nav className="me-auto">
                    </Nav>
                </Container>
            </Navbar>
            <br />
        </>
    )
}
export default PageTemplateNavigationBar;