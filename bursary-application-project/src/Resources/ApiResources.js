import axios from "axios";

const opeApis= axios.create(
    {
        baseURL:"http://localhost:8080/api/v0"
    }
)

export function register(username, email,phoneNumber, password)  {

    //Create the registration model
    const registrationModel = {
        userName : username,
        userEmail: email,
        userPhoneNumber : phoneNumber,
        userPassword : password
    }


    //sending the registration model to the backend
    const response = opeApis.post("/register", registrationModel)

    /*evaluating the response to the jwt token or return an error*/
}


export function login(username, password){

    /*login model*/
   const loginModel = {
        userName : username,
        userPassword: password
    }

    const response = opeApis.post("/login", loginModel);

   /*Evaluate the response here */

}