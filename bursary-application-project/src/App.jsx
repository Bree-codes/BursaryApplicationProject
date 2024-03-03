import IndexRouting from "./RoutingPages/IndexRouting.jsx";
import { useEffect, useState } from "react";
import StudentView from "./Views/StudentView/StudentView.jsx";

function App() {
   const [renderApp, setRenderApp] = useState(null);
   const [indexPage, setIndexPage] = useState(null);

   useEffect(() => {
      setIndexPage(<IndexRouting setRenderApp={setRenderApp} />);
   }, []);


   useEffect(() => {
      console.log(renderApp)
      switch (renderApp) {
         case 'user':
            //setIndexPage(null)
            setIndexPage(<StudentView />);
            break;
         case 'admin':
            setIndexPage(<>Hello there 2</>);
            break;
         case 'chief':
            setIndexPage(<>Hello there 3</>);
            break;
         case 'viewers':
            setIndexPage(<>Hello there 4</>);
            break;
         case 'secretary':
            setIndexPage(<>Hello there 5</>);
            break;
         default:
            setIndexPage(<IndexRouting setRenderApp={setRenderApp} />);
      }
   }, [renderApp]);


   return (
       <>
          {indexPage}
       </>
   );
}

export default App;
