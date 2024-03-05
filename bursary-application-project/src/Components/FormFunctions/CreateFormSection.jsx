import InputComponent from "../InputComponent.jsx";
import ChoiceInputComponent from "../ChoiceInputComponent.jsx";
import {Button, Col, Row} from "react-bootstrap";

// eslint-disable-next-line react/prop-types
const CreateFormSection = ({fieldName, setFieldName,fieldType, setFieldType, section, setSection, onAddForm, onRemoveForm}) =>{
    return(
        <div className={"bg-dark p-4 "}>
            <Row >
                <Col md={"auto"} className={"m-auto"}>
                    <InputComponent filedName={"Field Name : "}
                                    type={"text"}
                                    onChange={(e) => setFieldName(e.target.value)}
                                    placeHolder={"e.g Student Full Name"}
                                    value={fieldName}/>
                </Col>
                <Col md={"auto"} className={"m-auto"}>
                     <InputComponent filedName={"Field Section : "}
                                    type={"text"}
                                    onChange={(e) => setSection(e.target.value)}
                                    placeHolder={"e.g Section A"}
                                    value={section}/>
                </Col>
            </Row>
            <Row>
                <Col md={"auto"} className={"m-auto"}>
                    <ChoiceInputComponent  onChange={(e) => setFieldType(e.target.value)}
                                           options={["password", "checkbox", "text", "file", "consent"]}
                                           value={fieldType}
                    fieldName={"Select Field Input Type : "}/>
                </Col>
            </Row>
            <Row>
               <Col md={"auto"} className={"m-auto"}>
                   <Button onClick={onAddForm}
                       className="m-0 p-2 justify-content-center container-fluid"
                           style={{
                               borderRadius: '10px',
                               display: 'flex',
                               justifyContent: 'center',
                               alignItems: 'center',
                           }}>Add Field</Button>
               </Col>
                <Col md={"auto"} className={"m-auto"}>
                    <Button onClick={onRemoveForm}
                        className="m-0 p-2 justify-content-center container-fluid"
                            style={{
                                outline:"none",
                                borderRadius: '10px',
                                display: 'flex',
                                justifyContent: 'center',
                                alignItems: 'center',
                            }}>Remove Field</Button>
                </Col>
            </Row>
        </div>
    )
}
export default  CreateFormSection;
