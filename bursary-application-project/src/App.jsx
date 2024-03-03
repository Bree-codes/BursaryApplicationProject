import IndexRouting from "./RoutingPages/IndexRouting.jsx";


import {useEffect, useState} from "react";

function App() {
   const [renderApp, setRenderApp] = useState(<></>);

   useEffect(() => {
      setRenderApp(<IndexRouting setRenderApp={setRenderApp}/>)
   }, []);


return <>
   {renderApp}
</>
}

export default App
