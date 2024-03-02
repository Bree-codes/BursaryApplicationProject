import { Container, Button } from 'react-bootstrap';
import InputComponent from "../Components/InputComponent.jsx";

// eslint-disable-next-line react/prop-types
const LoginPageLayout = ({ username, setUsername, password, setPassword, onChange }) => {
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
                maxWidth: '400px'
            }}>

                <h2>User Login</h2>
                <InputComponent
                    type={"text"}
                    filedName="User Name : "
                    placeHolder={"Enter Your Name"}
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
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
                    onClick={onChange}
                    required={"true"}
                >
                    Login
                </Button>
            </Container>
        </div>
    );
};

export default LoginPageLayout;