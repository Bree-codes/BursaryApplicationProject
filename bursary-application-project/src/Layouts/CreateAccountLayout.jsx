import InputComponent from "../Components/InputComponent.jsx";
import {useState} from "react";
import {Button, Container} from "react-bootstrap";

const CreateAccountLayout = () => {
    const [userName, setUserName] = useState("");
    const [userEmail, setUserEmail] = useState("");
    const [userPhoneNumber, setUserPhoneNumber] = useState("");
    const [userPassword, setUserPassword] = useState("");


    /*This function will run when the user clicks register*/
    const handleRegister = (e) => {
        e.preventDefault()

    }


    return(
        <Container className={"bg-info"}>
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
                                value={userPassword}
                                onChange={(e) => setUserPassword(e.target.value)}
                                required={"true"}/>
                <td className={"px-5"}>
                    <Button className={"mx-5 px-5"}
                            type={"submit"}
                            onSubmit={handleRegister}>Register</Button>
                </td>
            </>
        </Container>
    )
}
export default CreateAccountLayout;