import {createBrowserRouter, createRoutesFromElements, Route} from "react-router-dom";
import {RouterProvider} from "react-router";
import LoginPage from "../Pages/LoginPage.jsx";
import HomePageLayout from "../Layouts/HomePageLayout.jsx";
import RegistrationPage from "../Pages/RegistrationPage.jsx";

const  IndexRouting = () => {


    const routes = createBrowserRouter(
        createRoutesFromElements(
            <Route path={"/"}>
                <Route path={""} element={<HomePageLayout /> } />
                <Route path={"login"} element={<LoginPage />} />
                <Route path={"register"} element={<RegistrationPage /> } />
            </Route>
        )
    );


    return(<RouterProvider router={routes} />);
}

export default IndexRouting;