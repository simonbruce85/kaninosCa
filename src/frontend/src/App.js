import "./App.css";
import React from "react";
import { UserAuth } from './auth/AuthContext'
import AdminHome from "./AdminHome";
import UserView from "./pages/userView/UserView";
import { Route, Routes } from "react-router-dom";
import RedirectRoute from "./auth/RedirectRoute";
import Login from "./pages/Login";
import Protectedroute from "./auth/Protectedroute";
import Dashboard from "./pages/Dashboard";

const App = () => {
  
 const {user} = UserAuth();

  return (
    <>
    <Routes>
        <Route
          path="/login"
          element={
            <RedirectRoute>
              <Login />
            </RedirectRoute>
          }
        />
        <Route
          path="/*"//* used to let router know that inside "/" path it will find more subroutes
          element={
            <Protectedroute>
              <AdminHome />
            </Protectedroute>
          }
        >
        </Route>
        <Route
          path="/userView/*"
          element={
            <Protectedroute>
              <UserView />
            </Protectedroute>
          }
        />
        <Route path="*" element={<RedirectRoute />} />
      </Routes>
    </>
  );
};

export default App;
