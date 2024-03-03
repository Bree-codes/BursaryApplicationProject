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

        //check the response from the backend -> if the status is okay
        if(response.then(res => res.status).toString() === "200"){

            /*Make a request to get the user's role.*/



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