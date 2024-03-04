import { useState } from "react";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import {useNavigate} from "react-router";
import {Container} from "react-bootstrap";
import AddUserComponent from "../Components/AddUserComponent.jsx";

const AdminsPageLayout = () => {
    const [pageData, setPageData] = useState(null);
    const navigate = useNavigate();

    const handleAddUser = () => {
        console.log("Navigating to add user...")
        navigate("/admin/add-user")

        setPageData(<AddUserComponent />)

    }

    return (
        <>
            <PageTemplateNavigationBar handleApply={handleAddUser} action={"Add User"} />
            <Container className={"align-content-center justify-content-center "}>{ pageData }</Container>
        </>
    );
}

export default AdminsPageLayout;
