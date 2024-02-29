import InputComponent from "../Components/InputComponent.jsx";
import {useState} from "react";
import {Button} from "react-bootstrap";

const CreateAccountLayout = () => {
    const [userName, setUserName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [userPhoneNumber, setUserPhoneNumber] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const {confirmPassword, setConfirmPassword} = useState("");


    /*This function will run when the user clicks register*/
    const handleRegister = (e) => {
        e.preventDefault()

    }

    return(
        <>
            <InputComponent type={"text"}
                            filedName="User Name : "
                            placeHolder={"Enter Your Username"}
                            value={userName}
                            onChange={(e) => setUserName(e.target.value)}
                            required={"true"}/>
            <InputComponent type={"text"}
                            filedName="User Email : "
                            placeHolder={"Enter Your Email"}
                            value={userEmail}
                            onChange={(e) => setUserEmail(e.target.value)}
                            required={"false"}/>
            <InputComponent type={"text"}
                            filedName="Phone No : "
                            placeHolder={"Enter Your Phone Number"}
                            value={userPhoneNumber}
                            onChange={(e) => setUserPhoneNumber(e.target.value)}
                            required={"true"}/>
            <InputComponent type={"password"}
                            filedName="Password : "
                            placeHolder={"Password "}
                            value={userPassword}
                            onChange={(e) => setUserPassword(e.target.value)}
                            required={"true"}/>
            <InputComponent type={"password"}
                            filedName="Password : "
                            placeHolder={"Confirm Password"}
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                            required={"true"}/>
        <Button type={"submit"}
                className={"m-0 p-2 justify-content-center container-fluid"}
                variant={"none"}
                        onSubmit={handleRegister}
                        required={"true"}>Register</Button>
     </>
    )
}
export default CreateAccountLayout;