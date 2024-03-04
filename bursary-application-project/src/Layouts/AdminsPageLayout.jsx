import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import {useState} from "react";
import {useNavigate} from "react-router";

const AdminsPageLayout = () =>{
    const [pageData, setPageData] = useState(null);
    const navigate = useNavigate();


    const handleAddUser = () =>{
        console.log("navigate to the add user page");
        navigate("/admin/add-user")
    }


    return(
        <>
            <PageTemplateNavigationBar handleApply={handleAddUser} action={"Add User"}/>
        </>
    )
}

export default AdminsPageLayout;