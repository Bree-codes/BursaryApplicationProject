import InputComponent from "../InputComponent.jsx";
import ChoiceInputComponent from "../ChoiceInputComponent.jsx";
import {Button, Col, Row} from "react-bootstrap";


const CreateFormSection = ({
                               // eslint-disable-next-line react/prop-types
                               fieldName,
                               // eslint-disable-next-line react/prop-types
                               setFieldName,
                               // eslint-disable-next-line react/prop-types
                               fieldType,
                               // eslint-disable-next-line react/prop-types
                               setFieldType,
                               // eslint-disable-next-line react/prop-types
                               section,
                               // eslint-disable-next-line react/prop-types
                               setSection,
                               // eslint-disable-next-line react/prop-types
                                month,
                               // eslint-disable-next-line react/prop-types
                                setMonth,
                               // eslint-disable-next-line react/prop-types
                               onAddForm,
                               // eslint-disable-next-line react/prop-types
                               onRemoveForm,
                               // eslint-disable-next-line react/prop-types
                               sectionField,
                               // eslint-disable-next-line react/prop-types
                               setModalMessage
                                }) =>{
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
                                    onChange={(e) => {
                                        if(Object.keys(sectionField).length === 0)
                                            setSection(e.target.value);
                                        else {
                                            console.log(sectionField);
                                            setModalMessage("You Must Submit The " +
                                                "Section Before Inserting A new One");
                                            setSection(section);
                                        }
                                    }}
                                    placeHolder={"e.g Section A"}
                                    value={section}/>
                </Col>
            </Row>
            <Row>
                <Col md={"auto"} className={"m-auto"}>
                    <ChoiceInputComponent  onChange={(e) => setFieldType(e.target.value)}
                                           options={["password", "checkbox", "text", "file", "consent", "gender"]}
                                           value={fieldType}
                    fieldName={"Select Field Input Type : "}/>
                </Col>
                <Col md={"auto"} className={"m-auto"}>
                    <InputComponent filedName={"Bursary Month : "}
                                    type={"text"}
                                    onChange={(e) => setMonth(e.target.value)}
                                    placeHolder={"e.g August"}
                                    value={month}/>
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
