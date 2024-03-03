import InputComponent from "../InputComponent.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";
import ConsentInput from "../ConsentInput.jsx";
import FieldDisplayComponent from "./FieldDisplayComponent.jsx";

// eslint-disable-next-line react/prop-types
const GetFormDisplay = ({studentForm}) =>{
    return(
        <>
            {studentForm && (
                <div>
                    <h1><u>CDF Bursary {studentForm[0][0].bursaryMonth}</u></h1>
                    {/* eslint-disable-next-line react/prop-types */}
                    {studentForm.map((section, index) => (
                        <div key={index}>
                            <h2>{section[0].section}</h2>
                            {section.map((field) => (
                                // eslint-disable-next-line react/jsx-key
                                <FieldDisplayComponent field={field} />
                            ))}
                        </div>
                    ))}
                </div>
            )}
        </>
    )
}

export default GetFormDisplay;