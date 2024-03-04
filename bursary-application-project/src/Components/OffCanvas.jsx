import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';
import {Col, FormLabel, NavLink} from "react-bootstrap";
import {Image} from "react-bootstrap";
import Image8 from './../Images/image8.jpeg'
import {useNavigate} from "react-router";


// eslint-disable-next-line react/prop-types
const OffCanvas = ({name}) =>{
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const navigate = useNavigate();

    return (
        <>
            <Button variant="primary" onClick={handleShow} className="me-2">
                {name}
            </Button>
            <Offcanvas show={show} onHide={handleClose} >
                <Offcanvas.Header closeButton className={"bg-info px-3"}>
                    <Offcanvas.Title className={"fw-bolder"}>
                        {(localStorage.getItem('role').toUpperCase())+"'S Dashboard"}</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                    <Col xs={6} md={4} className={"p-2 m-2"}>
                        <Image src={Image8} height={"100px"} width={"100px"} roundedCircle />
                        <FormLabel className={"fw-bold"}>{localStorage.getItem('username')}</FormLabel>
                    </Col>
                    <hr />
                    <NavLink className={"fw-bolder m-2 p-2"} onClick={() =>{navigate("/")}}>Home</NavLink>
                </Offcanvas.Body>
            </Offcanvas>
        </>
    );
}

export default OffCanvas;






