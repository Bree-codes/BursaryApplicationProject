import {Col, Container, Row} from "react-bootstrap";
import PageTemplateNavigationBar from "../../Components/FormFunctions/<PageTemplateNavigationBar.jsx";
import MenuButton from "../../Components/MenuButton.jsx";
import {useState} from "react";

const ViewerIndexPage = () => {
    const [mainContain, setMainConsent] = useState(null);





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
                        <MenuButton title={"Create Form"} onclick={() => {
                            setMainConsent();
                        }}/>
                        <hr style={{color: 'white'}}/>
                    </Col>
                    <Col
                        xs={12}
                        md={{ span: 9, offset: 3 }}
                        style={{
                            paddingTop: "4rem",
                            zIndex: 0,}}>

                    </Col>
                </Row>
            </Container>
        </>
    );
};

export default ViewerIndexPage;
