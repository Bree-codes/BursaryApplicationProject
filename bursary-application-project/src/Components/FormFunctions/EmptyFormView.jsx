import {getForm} from "../../Resources/ApiResources.js";
import {useEffect, useState} from "react";
import {Alert, Container} from "react-bootstrap";
import InputComponent from "../InputComponent.jsx";
import ConsentInput from "../ConsentInput.jsx";
import GenderInputComponent from "../GenderInputComponent.jsx";

const EmptyFormView = () =>{
    const [form, setForm] = useState(null);
    const [error, setError] = useState("");
    const [bursaryMonth, setBursaryMonth] = useState("");
    const [view, setView] = useState(null);

    useEffect(() => {

        getForm().then((res) =>{
            if(res.data.length !== 0){
                setForm(res.data);
                console.log(res.data)
                setBursaryMonth(res.data[0][0].bursaryMonth);
                console.log(res.data[0][0].bursaryMonth)
                return;
            }
            setError("There Is Not Form Available");
        }).catch((error) => {
            setError(error.response.data.message);
        })
    }, []);


    useEffect(() => {
        if (form) {
            setView(form.map((field, index) => {
                return(
                    <div key={index}>
                        <h3>{field[0].section}</h3>
                        {field.map((input) =>{
                            console.log(input.fieldInputType);
                            return(
                                <div key={input.fieldId}>
                                    {input.fieldInputType === "text" &&
                                        <InputComponent
                                            filedName={input.fieldName}
                                            type={input.fieldInputType}
                                        />}
                                    {input.fieldInputType === "checkbox" &&
                                        <ConsentInput
                                        />}
                                    {input.fieldInputType === "file" &&
                                        <InputComponent
                                            filedName={input.fieldName}
                                            type={input.fieldInputType}
                                        />}
                                    {input.fieldInputType === "gender" &&
                                        <GenderInputComponent
                                        />}
                                    {input.fieldInputType === "password" &&
                                        <InputComponent
                                            filedName={input.fieldName}
                                            type={input.fieldInputType}
                                        />}
                                </div>
                            )
                        })}
                    </div>
                )
            }));
        }
    }, [form]);


    return(
        <>
            {error && <Alert>{error}</Alert>}
            <Container className={"align-content-center justify-content-center"}>
                <h2> CDF Bursary {bursaryMonth && bursaryMonth} - {bursaryMonth && new Date().getFullYear()}</h2>
                {view && view}
            </Container>
        </>
    );
}
export default EmptyFormView;