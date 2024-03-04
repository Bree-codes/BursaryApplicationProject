import ChoiceInputComponent from "./ChoiceInputComponent.jsx";


// eslint-disable-next-line react/prop-types
const GenderInputComponent = ({gender, setGender, onChange}) =>{

    const genderOptions = ["Male", "Female", "Others"];
    const fieldName = "Gender : ";
    return(
            <ChoiceInputComponent options={genderOptions} value={gender}
                                  onChange={(e) => {
                                      setGender(e.target.value);
                                      onChange();
                                  }
            }
                                  fieldName={fieldName}/>
    )
}

export default GenderInputComponent;