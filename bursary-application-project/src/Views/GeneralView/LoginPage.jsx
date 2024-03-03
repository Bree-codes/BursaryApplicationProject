import LoginPageLayout from "../../Layouts/LoginPageLayout.jsx";
import {useState} from "react";
import {login} from "../../Resources/ApiResources.js";

const LoginPage = () =>{

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
   /* const [loginStatus, setLoginStatus] = useState(<></>)*/

    const handleLogin = () =>{
        //pass the credentials to the backend
        const response = login(username,password);

        //performing the cleanUp
        setUsername("");
        setPassword("");

        //check the response from the backend
        const status =
            response.then(response => response.status);

        if(status!==200){

            return;
        }


    }

    return(
        <>
            <LoginPageLayout username={username}
                             setUsername={setUsername}
                             password={password}
                             setPassword={setPassword}
                             onChange={handleLogin}/>
        </>
    )
}

export default LoginPage;