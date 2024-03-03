import OffCanvas from "../Components/OffCanvas.jsx";
import {Container, Nav, Navbar} from "react-bootstrap";

const IndexViewLayout = () =>{
    return(
        <>
            <Navbar bg="dark" data-bs-theme="dark">
                <Container>
                    <Navbar.Brand><OffCanvas  placement={"start"} name={"Dashboard "} /></Navbar.Brand>
                    <Nav className="me-auto">

                    </Nav>
                </Container>
            </Navbar>
            <br />
        </>
    );
}

export default IndexViewLayout;
