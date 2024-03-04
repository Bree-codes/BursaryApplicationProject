import {Button, Container, Nav, Navbar} from "react-bootstrap";
import OffCanvas from "../OffCanvas.jsx";

// eslint-disable-next-line react/prop-types
const PageTemplateNavigationBar = ({action, handleApply}) =>{


    return(
        <>
            <Navbar bg="primary" data-bs-theme="dark" className={"align-content-end"} >
                <Container >
                    <Navbar.Brand ><OffCanvas  placement={"start"}
                                              name={(localStorage.getItem('role')).toUpperCase()+"'S  Dashboard "}
                    /></Navbar.Brand>
                    <Nav className="me-auto">
                        <Button className={"fw-bolder px-5 mx-5 "} onClick={handleApply}>{action}</Button>
                    </Nav>
                </Container>
            </Navbar>
            <br />
        </>
    )
}
export default PageTemplateNavigationBar;