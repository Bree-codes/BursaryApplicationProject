import { useState } from "react";
import PageTemplateNavigationBar from "../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import {useNavigate} from "react-router";
import {Col, Container, Stack} from "react-bootstrap";
import AddUserComponent from "../Components/AddUserComponent.jsx";
import {Route} from "react-router-dom";

const AdminsPageLayout = () => {
    const [pageData, setPageData] = useState(null);
    const navigate = useNavigate();

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
                <Col>
                    <Stack direction={"vertical"}>
                        <h2>User Creation Rules And Considerations</h2>
                        <ul>
                            <li className={"p-2"}>Chief's Names Slot Should Be The Chief Governing Location <br />
                                e.g Chief Kimathi Location</li>
                            <li className={"p-2"}>The Privileged Users' Default Passwords are Their Roles <br />
                                e.g viewer for A viewer user
                            form viewer.</li>
                            <li>

                            </li>

                        </ul>
                    </Stack>
                </Col>
            </Stack>
        </>
    );
}

export default AdminsPageLayout;
