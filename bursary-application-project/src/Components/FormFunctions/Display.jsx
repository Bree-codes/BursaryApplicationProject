import InputComponent from "../InputComponent.jsx";
import ConsentInput from "../ConsentInput.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";

// eslint-disable-next-line react/prop-types
const Display = ({input}) =>{
    return(
        <>
            <div key={input.fieldId}>
                {input.fieldInputType === "text" &&
                    <InputComponent
                        filedName={input.fieldName}
                        type={input.fieldInputType}
                    />}
                {input.fieldInputType === "checkbox" &&
                    <ConsentInput
                    />}
                {input.fieldInputType === "file" &&
                    <InputComponent
                        filedName={input.fieldName}
                        type={input.fieldInputType}
                    />}
                {input.fieldInputType === "gender" &&
                    <GenderInputComponent
                    />}
                {input.fieldInputType === "password" &&
                    <InputComponent
                        filedName={input.fieldName}
                        type={input.fieldInputType}
                    />}
            </div>
        </>
    )
}
export default Display;