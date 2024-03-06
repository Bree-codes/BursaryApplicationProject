import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from '../Views/GeneralView/LoginPage.jsx';
import HomePageLayout from '../Layouts/HomePageLayout.jsx';
import RegistrationPage from '../Views/GeneralView/RegistrationPage.jsx';
import StudentView from '../Views/StudentView/StudentView.jsx';
import AdminsPageLayout from "../Layouts/AdminsPageLayout.jsx";
import AddUserComponent from "../Components/AddUserComponent.jsx";
import ViewQualifiedApplicants from "../Views/AdminView/ViewQualifiedApplicants.jsx";
import ViewQualifiedUsers from "../Components/ViewQualifiedUsers.jsx";
import ViewerIndexPage from "../Views/ViewerView/ViewerIndexPage.jsx";
import EmptyFormView from "../Views/ViewerView/EmptyFormView.jsx";

// eslint-disable-next-line react/prop-types
const IndexRouting = () => {
    // Check if the user is already logged in
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    return (
        <Router>
            <Routes>
                <Route path="/">
                    <Route index element={<HomePageLayout />} />
                    <Route path="login" element={<LoginPage />} />
                    <Route path="register" element={<RegistrationPage />} />
                    {/* Protected route for logged-in users */}
                    <Route path="applicant" element={isLoggedIn ? <StudentView /> : <Navigate to="/login" />} />
                    <Route path={"admin"} element={isLoggedIn ? <AdminsPageLayout /> : <Navigate to={"/login"} />} />
                    <Route path={"admin/add-user"} element={<AddUserComponent />} />
                    <Route path={"admin/qualified"} element={<ViewQualifiedApplicants />}/>
                    <Route path={"admin/qualified/view"} element={<ViewQualifiedUsers />} />
                    <Route path={"viewer/home"} element={<ViewerIndexPage />} />
                    <Route path={"viewer/home/view-form"} element={< EmptyFormView />} />
                </Route>
            </Routes>
        </Router>
    );
};
export default IndexRouting;

