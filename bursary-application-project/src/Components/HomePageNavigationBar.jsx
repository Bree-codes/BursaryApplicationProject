import {Container,Navbar} from "react-bootstrap";
import {Link, Outlet} from "react-router-dom";

const HomePageNavigationBar = () =>
{
    return(
        <>
        <Navbar style={{
            background: 'rgba(245, 230, 106, 0.6)',
        }}>
                <Container  fluid={"xxl"} className={"m-0"}>
                    <Navbar.Brand>Online Bursary Application</Navbar.Brand>
                    <Navbar.Toggle />
                    <Navbar.Collapse className="justify-content-end">
                        <Navbar.Text className={"px-2 mx-2 fw-bolder"}>
                            <Link to={"login"}>Login </Link>
                        </Navbar.Text>
                        <Navbar.Text className={"px-2 mx-2 fw-bolder"}>
                            <Link to={"register"}>Register </Link>
                        </Navbar.Text>
                    </Navbar.Collapse>
                </Container>
            </Navbar>

            <Outlet />
        </>
    );

}
export default HomePageNavigationBar;
