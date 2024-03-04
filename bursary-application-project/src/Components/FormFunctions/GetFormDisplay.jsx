import FieldDisplayComponent from "./FieldDisplayComponent.jsx";
import {Button} from "react-bootstrap";
import {useState} from "react";
import {store} from "../../Resources/ApiResources.js";

// eslint-disable-next-line react/prop-types
const GetFormDisplay = ({studentForm}) =>{

    const [form, setForm] = useState({});


    const storeFormToDatabase = () =>{
        store(form).then(
            res => console.log(res.status)
        ).catch(error => {
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
    }




    return(
        <>
            {studentForm && (
                <div >
                    <h1><u>CDF Bursary {studentForm[0][0].bursaryMonth}</u></h1>
                    {/* eslint-disable-next-line react/prop-types */}
                    {studentForm.map((section, index) => (
                        <div key={index}>
                            <h2>{section[0].section}</h2>
                            {section.map((field) => (
                                // eslint-disable-next-line react/jsx-key
                                <FieldDisplayComponent field={field} form={form} setForm={setForm}/>
                            ))}
                        </div>
                    ))}
                    <Button onClick={storeFormToDatabase}
                            className={"justify-content-end align-content-end"} >Submit</Button>
                </div>
            )}
        </>
    )
}

export default GetFormDisplay;