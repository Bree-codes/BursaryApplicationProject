import LoginPageLayout from "../../Layouts/LoginPageLayout.jsx";
import {useState} from "react";

const LoginPage = () =>{

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = () =>{
        console.log(username)
        console.log(password)

        //pass the

        //performing the cleanUp
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