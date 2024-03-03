import InputComponent from "../InputComponent.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";

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
                            <h2>Section: {section[0].section}</h2>
                            {section.map((field, fieldIndex) => (
                                <div key={fieldIndex}>
                                    {/* Render form fields based on fieldInputType */}
                                    {field.fieldInputType === "text" && (
                                        <InputComponent/>
                                    )}
                                    {field.fieldInputType === "gender" && (
                                        <GenderInputComponent/>
                                    )}
                                    {/* Add handling for other field types as needed */}
                                </div>
                            ))}
                        </div>
                    ))}
                </div>
            )}
        </>
    )
}

export default GetFormDisplay;