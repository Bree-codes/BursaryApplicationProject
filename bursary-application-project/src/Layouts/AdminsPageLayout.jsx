import { useState } from "react";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import {useNavigate} from "react-router";
import {Col, Stack} from "react-bootstrap";
import AddUserComponent from "../Components/AddUserComponent.jsx";
import UserCreationNotes from "../Components/UserCreationNotes.jsx";

const AdminsPageLayout = () => {
    const [pageData, setPageData] = useState(null);
    const navigate = useNavigate();
    const [userCreationRules, setUserCreationRules] = useState(null);

    const handleAddUser = () => {
        console.log("Navigating to add user...")
        navigate("add-user")

        setPageData(<AddUserComponent />)
        setUserCreationRules(<UserCreationNotes />)
    }

    return (
        <>
            <PageTemplateNavigationBar handleApply={handleAddUser} action={"Add User"} />
            <Stack direction={"horizontal"}>
                <Col>
                    { pageData }
                </Col>
                <Col className={"shadow z-2 "} md={"auto"} style={{
                    background:"wheat",
                    padding:'10px',
                }}>
                    {userCreationRules}
                </Col>
            </Stack>
        </>
    );
}

export default AdminsPageLayout;
