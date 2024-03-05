import InputComponent from "../InputComponent.jsx";
import ConsentInput from "../ConsentInput.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";


const DisplayForm = () =>{
    return (
        <div>
            {theObjectFromTheBackend.form.map((section, index) => (
                <div key={index}>
                    <h3 className={"bg-primary"}>{section.section} <hr /></h3>
                    {/* Iterate through the fields in the section */}
                    {Object.keys(section).map((fieldName, fieldIndex) => (
                        fieldName !== 'section' && (
                            <div key={fieldIndex}>
                                {section[fieldName] === 'text' && (
                                    <InputComponent filedName={fieldName} type="text" />
                                )}
                                {section[fieldName] === 'password' && (
                                    <InputComponent filedName={fieldName} type="password" />
                                )}
                                {section[fieldName] === 'checkbox' && (
                                    <ConsentInput value={fieldName} />
                                )}
                                {section[fieldName] === 'gender' && (
                                    <GenderInputComponent />
                                )}

                            </div>
                        )
                    ))}
                </div>
            ))}
        </div>
    );
}

export default DisplayForm;