import { useState } from "react";
import { Button, Container } from "react-bootstrap";
import InputComponent from "../Components/InputComponent.jsx";

// eslint-disable-next-line react/prop-types
const CreateAccountLayout = ({userName, userEmail, userPhoneNumber, userPassword, confirmPassword, setUserName, setUserEmail,
                                 // eslint-disable-next-line react/prop-types
                                 setUserPhoneNumber, setUserPassword, setConfirmPassword, handleRegister,
                             }) => {
    const [alert, setAlert] = useState(""); // State for alert message

    const checkValidity = () => {
        // Check if any of the fields are null
        if (!userName || !userPhoneNumber || !userPassword) {
            setAlert("The username, phone number, and password cannot be empty");
            return; // Exit function early if any field is empty
        }

        // Check if phone number contains only numbers
        if (isNaN(userPhoneNumber)) {
            setAlert("Phone number must contain numbers only");
            return; // Exit function early if phone number is not numeric
        }

        // Additional checks for password pattern or other conditions
        const passwordRegex = /^(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{N})(?=.*\\p{S}).*$/;
        if (passwordRegex.test(userPassword)) {
            setAlert("The password should have the following criteria:\n" +
                " - At least 8 characters\n" +
                " - A lowercase character\n" +
                " - An uppercase character\n" +
                " - A digit\n" +
                " - A special character\n" +
                " - No 3 consecutive repeating characters");
            return;
        }

        // Regular expression to check if the number has only 10 numbers
        const phoneNumberRegex = /^\d{10}$/;
        if (!phoneNumberRegex.test(userPhoneNumber)) {
            setAlert("Phone Number Should Contain Ten Number.");
            return;
        }

        // Check if the two passwords match
        if (userPassword !== confirmPassword) {
            setAlert("The Passwords Do Not Match");
            return;
        }

        // If all conditions are met, proceed with registration
        handleRegister();
    };

    return (
        <div
            style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                height: "100vh",
                background: "rgba(255, 255, 255, 0.5)",
            }}
        >
            <Container
                style={{
                    borderRadius: "10px",
                    padding: "20px",
                    maxWidth: "450px",
                    boxShadow: "0 0 10px rgba(0, 0, 0, 0.1)",
                    backdropFilter: "blur(5px)",
                    backgroundColor: "rgba(250, 250, 10, 0.5)",
                }}
            >
                <h2 className={"px-3 mx-2"}>User Registration</h2>
                {alert && <div className="alert alert-danger">{alert}</div>}
                <InputComponent
                    type={"text"}
                    filedName="User Name : "
                    placeHolder={"Enter Your Username"}
                    value={userName}
                    onChange={(e) => setUserName(e.target.value)}
                    required={true}
                />
                <InputComponent
                    type={"text"}
                    filedName="User Email : "
                    placeHolder={"Enter Your Email"}
                    value={userEmail}
                    onChange={(e) => setUserEmail(e.target.value)}
                    required={false}
                />
                <InputComponent
                    type={"text"}
                    filedName="Phone No : "
                    placeHolder={"Enter Your Phone Number"}
                    value={userPhoneNumber}
                    onChange={(e) => setUserPhoneNumber(e.target.value)}
                    required={true}
                />
                <InputComponent
                    type={"password"}
                    filedName="Password : "
                    placeHolder={"Password "}
                    value={userPassword}
                    onChange={(e) => setUserPassword(e.target.value)}
                    required={true}
                />
                <InputComponent
                    type={"password"}
                    filedName="Password : "
                    placeHolder={"Confirm Password"}
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required={true}
                />
                <Button
                    style={{
                        borderRadius: "10px",
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        height: "10vh",
                        background: "rgba(255, 255, 255, 0.5)",
                    }}
                    type={"submit"}
                    className={"m-0 p-2 justify-content-center container-fluid"}
                    variant={"none"}
                    onClick={checkValidity}
                >
                    Register
                </Button>
            </Container>
        </div>
    );
};

export default CreateAccountLayout;
