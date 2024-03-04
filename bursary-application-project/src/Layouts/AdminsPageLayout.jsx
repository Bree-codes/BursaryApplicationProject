import { useState } from "react";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import {useNavigate} from "react-router";
import {Col, Container, Stack} from "react-bootstrap";
import AddUserComponent from "../Components/AddUserComponent.jsx";
import {Route} from "react-router-dom";

const AdminsPageLayout = () => {
    const [pageData, setPageData] = useState(null);
    const navigate = useNavigate();
    const [userCreationRules, setUserCreationRules] = useState(null);

    const handleAddUser = () => {
        console.log("Navigating to add user...")
        navigate("add-user")

        setPageData(<AddUserComponent />)
    }

    return (
        <>
            <PageTemplateNavigationBar handleApply={handleAddUser} action={"Add User"} />
            <Stack direction={"horizontal"}
                   className={"align-content-center justify-content-center "}>
                <Col>
                    { pageData }
                </Col>

            </Stack>
        </>
    );
}

export default AdminsPageLayout;
