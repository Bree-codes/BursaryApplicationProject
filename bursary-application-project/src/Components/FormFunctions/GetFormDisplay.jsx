import FieldDisplayComponent from "./FieldDisplayComponent.jsx";
import {Button} from "react-bootstrap";
import {useState} from "react";

// eslint-disable-next-line react/prop-types
const GetFormDisplay = ({studentForm}) =>{

    const [form, setForm] = useState({});

    return(
        <>
            {studentForm && (
                <div >
                    <h1><u>CDF Bursary {studentForm[0][0].bursaryMonth}</u></h1>
                    {/* eslint-disable-next-line react/prop-types */}
                    {studentForm.map((section, index) => (
                        <div key={index}>
                            <h2>{section[0].section}</h2>
                            {section.map((field) => (
                                // eslint-disable-next-line react/jsx-key
                                <FieldDisplayComponent field={field} form={form} setForm={setForm}/>
                            ))}
                        </div>
                    ))}
                    <Button className={"justify-content-end align-content-end"} >Submit</Button>
                </div>
            )}
        </>
    )
}

export default GetFormDisplay;