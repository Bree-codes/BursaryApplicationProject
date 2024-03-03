import InputComponent from "../InputComponent.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";
import ConsentInput from "../ConsentInput.jsx";

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
                            {section.map((field, fieldIndex) => (
                                <div key={fieldIndex}>
                                    {/* Render form fields based on fieldInputType */}
                                    {field.fieldInputType === "text" && (
                                        <InputComponent filedName={field.fieldName} type={field.fieldInputType}
                                        value={field.fieldValue}/>
                                    )}
                                    {field.fieldInputType === "gender" && (
                                        <GenderInputComponent/>
                                    )}
                                    {field.fieldInputType === "consent" && (
                                        <ConsentInput />
                                    )}
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