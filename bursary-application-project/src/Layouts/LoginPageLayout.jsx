import {Container, Button, NavLink} from 'react-bootstrap';
import InputComponent from "../Components/InputComponent.jsx";
import {useState} from "react";


// eslint-disable-next-line react/prop-types
const LoginPageLayout = ({ username, setUsername, password, setPassword, onChange }) => {

    const [alert, setAlert] = useState("");

    const handleChangePassword = () =>{
        setAlert("Check your email, for instructions to change your password");
        console.log("here connect with the forgotten password API");
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
                backgroundColor: 'rgba(15,235,202,0.5)',
                maxWidth: '400px'
            }}>

                <h2>User Login</h2>
                {alert && <div className="alert alert-danger">{alert}</div>}
                <InputComponent
                    type={"text"}
                    filedName="User Name : "
                    placeHolder={"Enter Your Name"}
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <InputComponent
                    type={"password"}
                    filedName="Password : "
                    placeHolder={"Password "}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <Button
                    style={{
                        borderRadius: '8px',
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        background: 'rgb(137,156,158)',
                    }}
                    type={"submit"}
                    className={"m-0 p-2 justify-content-center container-fluid"}
                    variant={"none"}
                    onClick={onChange}
                >
                    Login
                </Button>
                <NavLink className={"m-3 p-2 my-5 m-md-auto fw-bolder btn-small"}
                     onClick={handleChangePassword}>forgot password</NavLink>
            </Container>
        </div>
    );
};

export default LoginPageLayout;