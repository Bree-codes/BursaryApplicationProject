import { Container, Button } from 'react-bootstrap';
import InputComponent from "../Components/InputComponent.jsx";
import {useState} from "react";

const LoginPageLayout = () => {
    /*{ userEmail, setUserEmail, userPassword, setUserPassword, handleLogin }*/

    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = () =>
    {

    }



    return (
        <div
            style={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                height: '100vh',
                background: 'rgba(255, 255, 255, 0.5)',
            }}
        >
            <Container style={{
                borderRadius: '10px',
                padding: '20px',
                boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
                backdropFilter: 'blur(5px)',
                backgroundColor: 'rgba(235, 235, 15, 0.5)',
                maxWidth: '400px' }}>

                <h2>User Login</h2>
                <InputComponent
                    type={"text"}
                    filedName="User Email : "
                    placeHolder={"Enter Your Email"}
                    value={userName}
                    onChange={(e) => setUserName(e.target.value)}
                    required={"true"}
                />
                <InputComponent
                    type={"password"}
                    filedName="Password : "
                    placeHolder={"Password "}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required={"true"}
                />
                <Button
                    style={{
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
                    onClick={handleLogin}
                    required={"true"}
                >
                    Login
                </Button>
            </Container>
        </div>
    );
};

export default LoginPageLayout;