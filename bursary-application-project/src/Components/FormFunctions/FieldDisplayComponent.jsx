import InputComponent from "../InputComponent.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";
import ConsentInput from "../ConsentInput.jsx";

// eslint-disable-next-line react/prop-types
const FieldDisplayComponent = ({field}) =>{
    return(
        <>
            <div key={field.fieldId} className={"m-2 p-3"}>
                {/* Render form fields based on fieldInputType */}
                {field.fieldInputType === "text" && (
                    <InputComponent filedName={field.fieldName} type={field.fieldInputType}
                                    value={field.fieldValue}/>
                )}
                {field.fieldInputType === "gender" && (
                    <GenderInputComponent/>
                )}
                {field.fieldInputType === "consent" && (
                    <ConsentInput/>
                )}
                {field.fieldInputType === "password" && (
                    // eslint-disable-next-line react/prop-types
                    <InputComponent filedName={field.fieldName} type={field.fieldInputType}
                                    value={field.fieldValue}/>
                )}
            </div>
        </>
    )
}

export default FieldDisplayComponent;