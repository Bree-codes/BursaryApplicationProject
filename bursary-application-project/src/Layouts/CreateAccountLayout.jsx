import InputComponent from "../Components/InputComponent.jsx";
import { Button, Container } from "react-bootstrap";

// eslint-disable-next-line react/prop-types
const CreateAccountLayout = ({ userName, userEmail, userPhoneNumber, userPassword, confirmPassword,
                                 // eslint-disable-next-line react/prop-types
                                 setUserName, setUserEmail, setUserPhoneNumber, setUserPassword, setConfirmPassword,
                                 // eslint-disable-next-line react/prop-types
                                    handleRegister}) => {

    return (
        <div
            style={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                height: '100vh',
                background: 'rgba(255, 255, 255, 0.5)',
            }}>
            <Container style={{
                borderRadius: '10px',
                padding: '20px',
                maxWidth: '450px',
                boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
                backdropFilter: 'blur(5px)',
                backgroundColor: 'rgba(250, 250, 10, 0.5)'
            }}>

                <h2 className={"px-3 mx-2"}>
                    User Registration
                </h2>
                <InputComponent
                    type={"text"}
                    filedName="User Name : "
                    placeHolder={"Enter Your Username"}
                    value={userName}
                    onChange={(e) => setUserName(e.target.value)}
                />
                <InputComponent
                    type={"text"}
                    filedName="User Email : "
                    placeHolder={"Enter Your Email"}
                    value={userEmail}
                    onChange={(e) => setUserEmail(e.target.value)}
                />
                <InputComponent
                    type={"text"}
                    filedName="Phone No : "
                    placeHolder={"Enter Your Phone Number"}
                    value={userPhoneNumber}
                    onChange={(e) => setUserPhoneNumber(e.target.value)}
                />
                <InputComponent
                    type={"password"}
                    filedName="Password : "
                    placeHolder={"Password "}
                    value={userPassword}
                    onChange={(e) => setUserPassword(e.target.value)}
                />
                <InputComponent
                    type={"password"}
                    filedName="Confirm Password : " // Adjusted field name
                    placeHolder={"Confirm Password"}
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                />
                <Button style={{
                    borderRadius: '10px',
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '10vh',
                    background: 'rgba(255, 255, 255, 0.5)',
                }}
                        type={"submit"}
                        className={"m-0 p-2 justify-content-center container-fluid"}
                        variant={"none"}
                        onClick={handleRegister}
                >
                    Register
                </Button>
            </Container>
        </div>
    );
};

export default CreateAccountLayout;
