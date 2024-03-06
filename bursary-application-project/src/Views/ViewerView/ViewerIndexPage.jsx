import { Col, Container, Row} from "react-bootstrap";
import MenuButton from "../../Components/MenuButton.jsx";
import {useState} from "react";
import PageTemplateNavigationBar from "../../Components/FormFunctions/PageTemplateNavigationBar.jsx";
import FormCreationView from "../../Components/FormFunctions/FormCreationView.jsx";
import {useNavigate} from "react-router";

const ViewerIndexPage = () => {
    const [mainContent, setMainContent] = useState( null);
    const navigate = useNavigate();
    const handleCreateForm = () =>{
        setMainContent(<FormCreationView />);
    }
    const handleViewResentForm = () =>{
        navigate("view-form")
    }


    return (
        <>
            <PageTemplateNavigationBar />
            <Container fluid>
                <Row>
                    <Col xs={12} md={3} className={"bg-dark"}
                         style={{
                             backgroundColor: "#f8f9fa",
                             minHeight: "100vh",
                             position: "fixed",
                             left: 0,
                             top: "4rem",
                             zIndex: 1,
                         }}>
                        <hr style={{color: 'white'}}/>
                        <MenuButton title={"Create Form"} onclick={handleCreateForm}/>
                        <hr style={{color: 'white'}}/>
                        <MenuButton title={"View Resent Form"} onclick={handleViewResentForm}/>
                        <hr style={{color: 'white'}}/>
                    </Col>
                    <Col
                        xs={12}
                        md={{ span: 9, offset: 3 }}
                        style={{
                            paddingTop: "4rem",
                            zIndex: 0,
                        }}>
                        {mainContent && <>{mainContent}</>}
                    </Col>
                </Row>
            </Container>
        </>
    );
};

export default ViewerIndexPage;
