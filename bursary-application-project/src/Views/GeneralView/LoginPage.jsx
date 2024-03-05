import LoginPageLayout from "../../Layouts/LoginPageLayout.jsx";
import {useState} from "react";
import {login, updateJwt} from "../../Resources/ApiResources.js";
import {useNavigate} from "react-router";
const LoginPage = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = () => {
        console.log("login function called.")

        // Pass the credentials to the backend
        login(username, password)
            .then(res => {
                // Handle successful login
                updateJwt(res.data.token);

                /* Storing data to the browser's storage. */
                localStorage.setItem('role', res.data.role);
                localStorage.setItem('jwt', res.data.token);
                localStorage.setItem('username', res.data.username);
                localStorage.setItem('id', res.data.id);
                localStorage.setItem('isLoggedIn', 'true');

                // Navigate based on the role after successful login
                switch (res.data.role) {
                    case "user":
                        navigate("/applicant");
                        break;
                    case "admin":
                        navigate("/admin");
                        break;
                    case "viewer":
                        navigate("/viewer/home");
                        break;
                    default:
                        console.error("Unknown role:", res.data.role);
                }

                console.log("The Role Is:", res.data.role);
            })
            .catch(error => {
                // Handle login error
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
            })
            .finally(() => {
                // Perform cleanup
                setUsername("");
                setPassword("");
            });
    }


    return(
        <>
            <LoginPageLayout username={username}
                             setUsername={setUsername}
                             password={password}
                             setPassword={setPassword}
                             onChange={handleLogin}/>
        </>
    );
}

export default LoginPage;
