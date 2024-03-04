import {useEffect, useState} from "react";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import {useNavigate} from "react-router";
import {Col, Stack} from "react-bootstrap";
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
            <Stack direction={"horizontal"}>
                {pageData && <Col>{ pageData }</Col>}

                {    userCreationRules && <Col className={"shadow z-2 "} md={"auto"} style={{
                    background:"wheat",
                    padding:'10px'}}>{userCreationRules}</Col>}
            </Stack>
        </>
    );
}

export default AdminsPageLayout;
