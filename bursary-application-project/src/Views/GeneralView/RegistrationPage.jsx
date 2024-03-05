import {Container} from "react-bootstrap";
import CreateAccountLayout from "../../Layouts/CreateAccountLayout.jsx";
import { useState } from "react";
import {login, register, updateJwt} from "../../Resources/ApiResources.js";
import {useNavigate} from "react-router";

// eslint-disable-next-line react/prop-types
const RegistrationPage = ({setRenderApp}) => {
    const [userName, setUserName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [userPhoneNumber, setUserPhoneNumber] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const navigate = useNavigate();

    //method to handle the registration
    const handleRegister = () => {
        register(userName, userEmail, userPhoneNumber, userPassword)
            .then(res => {
                // Handle successful registration
                updateJwt(res.data.token);
                login(userName,userPassword).then( res => {
                    /* Storing data to the browser's storage. */
                    localStorage.setItem('jwt', res.data.token);
                    localStorage.setItem('username', res.data.username);
                    localStorage.setItem('id', res.data.id);
                    localStorage.setItem('isLoggedIn', 'true');
                    localStorage.setItem('role', res.data.role);
                });
                // Navigate based on the role after successful login
                switch (res.data.role) {
                    case "user":
                        navigate("/applicant");
                        break;
                    case "admin":
                        navigate("/admin");
                        break;
                    default:
                        console.error("Unknown role:", res.data.role);
                }
            })
            .catch(error => {
                // Handle registration error
                if (error.response) {

                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.error('Server responded with an error:', error.response.data);
                    console.log('Error Message:', error.response.data.message);
                    // You can set the error message state here if needed
                } else if (error.request) {

                    // The request was made but no response was received
                    console.error('No response received from server:', error.request);
                } else {

                    // Something happened in setting up the request that triggered an Error
                    console.error('Error setting up the request:', error.message);
                }
            });
    };


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
                handleRegister={handleRegister}
            />
        </Container>
    );
};

export default RegistrationPage;
