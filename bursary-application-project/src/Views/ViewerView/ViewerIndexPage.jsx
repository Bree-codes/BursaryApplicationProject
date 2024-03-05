import { Col, Container, Row } from "react-bootstrap";
import PageTemplateNavigationBar from "../../Components/FormFunctions/<PageTemplateNavigationBar.jsx";

const ViewerIndexPage = () => {
    return (
        <>
            <PageTemplateNavigationBar />
            <Container fluid>
                <Row>
                    <Col
                        xs={12}
                        md={3}
                        className={"bg-dark"}
                        style={{
                            backgroundColor: "#f8f9fa",
                            minHeight: "100vh",
                            position: "fixed",
                            left: 0,
                            top: "4rem",
                            zIndex: 1,
                        }}
                    >

                        Side Menu
                    </Col>
                    <Col
                        xs={12}
                        md={{ span: 9, offset: 3 }}
                        style={{
                            paddingTop: "4rem",
                            zIndex: 0,}}>
                        Main Content

                    </Col>
                </Row>
            </Container>
        </>
    );
};

export default ViewerIndexPage;
