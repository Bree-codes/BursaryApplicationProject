import {useEffect, useState} from "react";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import {useNavigate} from "react-router";
import {Col, Container, Stack} from "react-bootstrap";
import AddUserComponent from "../Components/AddUserComponent.jsx";
import UserCreationNotes from "../Components/UserCreationNotes.jsx";
import {Link} from "react-router-dom";

const AdminsPageLayout = () => {
    const [pageData, setPageData] = useState(null);
    const navigate = useNavigate();
    const [userCreationRules, setUserCreationRules] = useState(null);
    const [qualifiedLink, setQualifiedLink] = useState(null);

    useEffect(() => {
        setQualifiedLink(<Link
            className={"fw-bolder m-2 p-2 rounded-3"}
            style={{color:'white', backgroundColor: 'rgba(0,0,0,0.3)',}}
            to={"/admin/qualified"}>Qualified Students</Link>)
        }, []);



    const handleAddUser = () => {
        console.log("Navigating to add user...")
        setPageData(<AddUserComponent />)
        setUserCreationRules(<UserCreationNotes />)
    }

    return (
        <>
            <PageTemplateNavigationBar handleApply={handleAddUser}
                                       action={"Add User"}
                                       viewQualified={qualifiedLink}/>
    <Container className={"shadow z-3 px-3 "}>
            <Stack direction={"horizontal"}>
                {pageData && <Col>{ pageData }</Col>}

                {    userCreationRules && <Col  className={"shadow z-2 col-lg-0"}  style={{
                    background:"wheat",
                    padding:'10px'}}>{userCreationRules}</Col>}
            </Stack>
    </Container>
        </>
    );
}

export default AdminsPageLayout;
