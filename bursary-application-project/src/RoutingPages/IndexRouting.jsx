import {createBrowserRouter, createRoutesFromElements, Route} from "react-router-dom";
import {RouterProvider} from "react-router";
import LoginPage from "../Views/GeneralView/LoginPage.jsx";
import HomePageLayout from "../Layouts/HomePageLayout.jsx";
import RegistrationPage from "../Views/GeneralView/RegistrationPage.jsx";

// eslint-disable-next-line react/prop-types
const  IndexRouting = ({setRenderApp}) => {


    const routes = createBrowserRouter(
        createRoutesFromElements(
            <Route path={"/"}>
                <Route path={""} element={<HomePageLayout /> } />
                <Route path={"login"} element={<LoginPage />} />
                <Route path={"register"} element={<RegistrationPage setRenderApp={setRenderApp}/> } />
            </Route>
        )
    );


    return(<RouterProvider router={routes} />);
}

export default IndexRouting;
