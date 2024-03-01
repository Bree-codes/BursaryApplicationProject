const DisplayForm = () =>{



    //call a function to get from the backend the form

    /*I will bw using demo data for testing purpose*/

    const theObjectFromTheBackend ={ form :
    [{
        section : "Section A",
        Student_FullName:"text",
        Student_Age:"text"
    },
    {
        section : "Section B",
            Student_FullName:"checkbox",
            Gender:"text"
    },{
        section : "Section A",
        Student_FullName:"text",
        Student_Age:"password"
    }]
    }



    return(
        <>
        </>
    );
}

export default DisplayForm;