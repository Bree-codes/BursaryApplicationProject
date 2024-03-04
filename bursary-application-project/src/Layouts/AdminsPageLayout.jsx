import { useState } from "react";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";

const AdminsPageLayout = () => {
    const [pageData, setPageData] = useState(null);

    const handleAddUser = () => {

    }

    return (
        <>
            <PageTemplateNavigationBar handleApply={handleAddUser} action={"Add User"} />

        </>
    );
}

export default AdminsPageLayout;
