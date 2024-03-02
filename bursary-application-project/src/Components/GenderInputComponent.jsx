import ChoiceInputComponent from "./ChoiceInputComponent.jsx";


// eslint-disable-next-line react/prop-types
const GenderInputComponent = ({gender, setGender}) =>{

    const genderOptions = ["Male", "Female", "Others"];
    const fieldName = "Gender : ";
    return(
            <ChoiceInputComponent options={genderOptions} value={gender}
                                  onChange={(e) => setGender(e.target.value)}
                                  fieldName={fieldName}/>
    )
}

export default GenderInputComponent;