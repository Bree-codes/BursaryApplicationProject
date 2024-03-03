import IndexRouting from "./RoutingPages/IndexRouting.jsx";


import {useEffect, useState} from "react";

function App() {
   const [renderApp, setRenderApp] = useState(<></>);

   useEffect(() => {
      setRenderApp(<IndexRouting />)
   }, []);


return <>
   {renderApp}
</>
}

export default App
