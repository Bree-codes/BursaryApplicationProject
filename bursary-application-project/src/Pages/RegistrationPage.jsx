import { Container } from "react-bootstrap";
import CreateAccountLayout from "../Layouts/CreateAccountLayout.jsx";
import { useState } from "react";

const RegistrationPage = () => {
    const [userName, setUserName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [userPhoneNumber, setUserPhoneNumber] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    return (
        <Container>
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
            />
        </Container>
    );
};

export default RegistrationPage;
