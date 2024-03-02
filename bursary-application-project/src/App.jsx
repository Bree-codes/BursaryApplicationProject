/*import IndexRouting from "./RoutingPages/IndexRouting.jsx";*/

import GenderInputComponent from "./Components/GenderInputComponent.jsx";
import {useEffect, useState} from "react";

function App() {

    const [gender, setGender] = useState("");

    useEffect(() => {
        console.log(gender);
    }, [gender]);

return <>
 {/* <IndexRouting />*/}
    <GenderInputComponent gender={gender} setGender={setGender}/>
</>
}

export default App
