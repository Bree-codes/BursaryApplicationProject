import axios from "axios";


const opeApis= axios.create(
    {
        baseURL:"http://192.168.198.194:8080/api/v0"
    }
)

export async function register(username, email,phoneNumber, password)  {


    //Create the registration model
    const registrationModel = {
        userName : username,
        userEmail: email,
        userPhoneNumber : phoneNumber,
        userPassword : password
    }

    const response =
        (await opeApis.post("/users/register", registrationModel));

    return response;
}


export async function login(username, password){

    /*login model*/
   const loginModel = {
        username : username,
        password: password
    }

   /*Return the response for evaluations*/
    const response  = await opeApis.post("/auth/login", loginModel);

    //update the axios api header.
    updateJwt(response.data.token);

    //return object for evaluation
    return response;
}

export function updateJwt(token){
    console.log(token);
}


const securedApi = axios.create(
    {
        baseURL: "http://192.168.198.194:8080/api/v0",
        header : {"Authorization":"Bearer "+localStorage.getItem('jwt')}
    }

);

export async function getApplicationForm(){
   const userId = localStorage.getItem('id');
    console.log(userId)
   return await securedApi.get("/student/get-user-values?userId="+userId);
}

export async function store(form){
    const userId = localStorage.getItem('id');
    const fieldIdAndValue = form;

    return await securedApi.post("/student/save-form/"+userId, fieldIdAndValue);
}


export async function addPrivilegedUser(username, email, role){
    const user = {
        username:username,
        phoneNumberOrEmail:email,
        role:1
    }

    return await securedApi.post("/admin/create-higher-users", user);
}

export async function getQualifiedStudents(){
    console.log("getting qualified students.")
    return await securedApi.get("/get-qualified-students");
}
