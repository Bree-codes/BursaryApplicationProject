import {Button, Container, Nav, Navbar} from "react-bootstrap";
import OffCanvas from "../OffCanvas.jsx";
import {Link} from "react-router-dom";

// eslint-disable-next-line react/prop-types
const PageTemplateNavigationBar = ({action, handleApply, viewQualified}) =>{


    return(
        <>
            <Navbar style={{
                backgroundColor: 'rgba(15,235,202,0.5)',
            }} data-bs-theme="dark" className={"align-content-end"} >
                <Container className={"align-content-end"} >
                    <Navbar.Brand className={"align-content-end"}>
                                    <OffCanvas
                                        placement={"start"}
                                        name={(localStorage.getItem('role')).
                                        toUpperCase()+"'S  Dashboard "}
                    /></Navbar.Brand>
                    <Nav className="me-auto">
                        {action && <Button className={"fw-bolder p-2 m-2 "} onClick={handleApply} style={{
                            backgroundColor: 'rgba(0,0,0,0.3)',
                            border:'none',
                        }}>{action}</Button>}
                        {viewQualified && <>{viewQualified}</>}
                    </Nav>
                </Container>
            </Navbar>
            <br />
        </>
    )
}
export default PageTemplateNavigationBar;