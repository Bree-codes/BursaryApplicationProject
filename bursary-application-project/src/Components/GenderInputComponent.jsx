import ChoiceInputComponent from "./ChoiceInputComponent.jsx";
import {Col} from "react-bootstrap";

// eslint-disable-next-line react/prop-types
const GenderInputComponent = ({gender, setGender}) =>{

    const genderOptions = ["Male", "Female", "Others"];

    return(
        <Col md={"auto"}>
            <ChoiceInputComponent options={genderOptions} value={gender}
                                  onChange={(e) => setGender(e.target.value)} />
        </Col>
    )
}

export default GenderInputComponent;