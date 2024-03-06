import {getForm} from "../../Resources/ApiResources.js";
import {useEffect, useState} from "react";
import {Alert, Container} from "react-bootstrap";

const EmptyFormView = () =>{
    const [form, setForm] = useState(null);
    const [error, setError] = useState("");
    const [bursaryMonth, setBursaryMonth] = useState("");

    useEffect(() => {

        getForm().then((res) =>{
            if(res.data.length !== 0){
                setForm(res.data);
                setBursaryMonth(res.data[0][0].bursaryMonth);
                return;
            }
            setError("There Is Not Form Available");
        }).catch((error) => {
            setError(error.response.data.message);
        })
    }, []);


    useEffect(() => {
        form.map((field) =>{
            return(
                <>
                <h3>{field[0].section}</h3>

                </>
            );
        })
    }, []);





    return(
        <>
            {error && <Alert>{error}</Alert>}
            <Container fluid={"xl"} className={"align-content-center justify-content-center"}>
                <h2>Hello </h2>
            </Container>
        </>
    );
}
export default EmptyFormView;