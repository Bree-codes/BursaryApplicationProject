// RegistrationPage.jsx

import {Alert, Container} from "react-bootstrap";
import CreateAccountLayout from "../Layouts/CreateAccountLayout.jsx";
import { useState } from "react";

const RegistrationPage = () => {
    const [userName, setUserName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [userPhoneNumber, setUserPhoneNumber] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [alert, setAlert] = useState("");

    /*This function will handle */
    const handleRegister = () => {

        /*check if any of the functions are null*/
        (userPassword === null || userName === null ||  userPhoneNumber === null)
        && setAlert("The username, phone number and password can not be empty")

        try {
            parseInt(userPassword)
        }catch (error){
            setAlert("phone number must be contain numbers only")
        }

        /*here we will handle the password pattern*/


    };

    return (
        <Container>
            <div>{alert}</div>
            <CreateAccountLayout
                userName={userName}
                setUserName={setUserName}
                userEmail={userEmail}
                setUserEmail={setUserEmail}
                userPhoneNumber={userPhoneNumber}
                setUserPhoneNumber={setUserPhoneNumber}
                userPassword={userPassword}
                setUserPassword={setUserPassword}
                confirmPassword={confirmPassword}
                setConfirmPassword={setConfirmPassword}
                handleRegister={handleRegister}
            />
        </Container>
    );
};

export default RegistrationPage;
