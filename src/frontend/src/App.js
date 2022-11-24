import "./App.css";
import {
  PieChartOutlined,
  UserOutlined,
  CalendarOutlined,
} from "@ant-design/icons";
import { Layout, Menu, Drawer } from "antd";
import React, { useState } from "react";
import { Route, Routes, useLocation, useNavigate } from "react-router-dom";
import AppoinmentCalendar from "./Calendar/AppoinmentCalendar";
import Dogs from "./Dogs/Dogs";
import Dashboard from "./pages/Dashboard";
import DogDetails from "./Dogs/DogDetails";
import { FaDog } from "react-icons/fa";
import Owners from "./Owners/Owners";
import OwnerDetails from "./Owners/OwnerDetails";
import Logo from "./assets/logo.png";
import { Divide as Hamburger } from "hamburger-react";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import Protectedroute from "./auth/Protectedroute";
import RedirectRoute from "./auth/RedirectRoute";
import { UserAuth } from "./auth/AuthContext";
import { MdLogout } from "react-icons/md";

const { Content, Footer, Sider } = Layout;

function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}

const items = [
  getItem("Dashboard", "/", <PieChartOutlined />),
  getItem("Pets", "/petList", <FaDog />),
  getItem("Owners", "/ownerList", <UserOutlined />),
  getItem("Calendar", "/calendar", <CalendarOutlined />),
];

const items2 = [getItem("Log Out", null, <MdLogout />)];

const App = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const [open, setOpen] = useState(false);
  const { user, logIn, logOut } = UserAuth();
  const [collapsed, setCollapsed] = useState(true);

  const handleLogout = async () => {
    try {
      await logOut();
      navigate("/");
    } catch (error) {
      console.log(error);
    }
  };

  const onClose = () => {
    setOpen(false);
  };

  const handleDrawer = () => {
    setOpen(!open);
  };

  const handleDrawerLogoutTime = () => {
    handleDrawer();
    setTimeout(() => {
      handleLogout();
    }, 200); //used to give the drawer some time to fully close (pending revision to find a better way to logout)
  };

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
      </Routes>
      <Layout
        style={{
          minHeight: "100vh",
        }}
        className={`${!user ? "hidden" : ""}`} //hide the bar when use is not logged in
      >
        {/*Sidebar for compute  */}
        <Sider
          className="hidden md:block "
          trigger={null}
          collapsible
          collapsed={collapsed}
          onCollapse={(value) => setCollapsed(value)}
          collapsedWidth={75}
          width={151}
          breakpoint={"lg"}//when reaches lg the onbreakpoint is activated
          onBreakpoint={()=>setCollapsed(!collapsed)}//the function setCollapsed is activated when screen hits lg breakpoint
          style={{
            overflow: "auto",
            height: "100vh",
            position: "fixed",
            left: 0,
            top: 0,
            bottom: 0,
          }}
        >
          <div className=" h-[30px] m-2 flex justify-center items-center logo" />
          <div className="h-[90%] flex flex-col justify-between mb-4">
            <Menu
              theme="dark"
              defaultSelectedKeys={location.pathname} //used location from react router dom to pass the currently open path to the default selected key
              mode="inline"
              items={items}
              onClick={({ key }) => navigate(key)}
            />
            <Menu
              theme="dark"
              mode="inline"
              onClick={handleLogout}
              items={items2}
              className="mb-4 "
            ></Menu>
          </div>
        </Sider>
        {/*Sidebar for computer ends */}

        {/*Navbar for mobile */}
        <div className="md:hidden fixed w-full h-[80px] flex justify-between items-center px-4 bg-[#041F31] text-white z-10">
          <div className="">
            <img
              src={Logo}
              alt="Logo"
              style={{ width: "50px" }}
              className="cursor-pointer"
            />
          </div>

          {/* Hamburger */}
          <div
            onClick={handleDrawer}
            className="md:hidden  z-20 flex items-center text-gray-300"
          >
            {<Hamburger size={19} toggled={open} />}
          </div>
          {/* Mobile Menus*/}
          <Drawer
            placement="right"
            onClose={onClose}
            open={open}
            closable={false}
            width={250}
            zIndex={10}
            className="mt-[80px]"
          >
            <ul className="w-full justify-start text-black">
              <li
                className=" flex items-center py-2"
                onClick={() => {
                  navigate("/");
                  handleDrawer();
                }}
              >
                <PieChartOutlined className="px-2" /> Dashboard
              </li>
              <li
                className=" flex py-2 items-center"
                onClick={() => {
                  navigate("/petList");
                  handleDrawer();
                }}
              >
                <FaDog className="px-2 w-[32px] " /> Pets
              </li>
              <li
                className=" flex py-2 items-center"
                onClick={() => {
                  navigate("/ownerList");
                  handleDrawer();
                }}
              >
                <UserOutlined className="px-2 " /> Owners
              </li>
              <li
                className="flex py-2 items-center"
                onClick={() => {
                  navigate("/calendar");
                  handleDrawer();
                }}
              >
                <CalendarOutlined className="px-2" /> Calendar
              </li>
              <li
                className="flex py-2 items-center"
                onClick={() => {
                  handleDrawerLogoutTime();
                }}
              >
                <MdLogout className="px-2 w-[32px]" /> Log Out
              </li>
            </ul>
          </Drawer>
        </div>
        {/*Navbar for mobile ends*/}
        <Layout
          className={`site-layout ease-in-out duration-150 transition-all  ${
            collapsed ? "md:ml-[80px]" : "md:ml-[150px]"
          } `}
        >
          <Content
            style={{
              margin: "0 16px",
            }}
          >
            <div
              style={{
                margin: "32px 0",
              }}
            ></div>
            <div className="site-layout-background min-h-[360px] mt-[100px] md:mt-[0px]">
              {}
              <Routes>
                <Route
                  path="/"
                  element={
                    <Protectedroute>
                      <Dashboard />
                    </Protectedroute>
                  }
                />
                <Route path="/signup" element={<Signup />} />
                <Route
                  path="/petList"
                  element={
                    <Protectedroute>
                      <Dogs />
                    </Protectedroute>
                  }
                />
                <Route
                  path="/ownerList"
                  element={
                    <Protectedroute>
                      <Owners />
                    </Protectedroute>
                  }
                />
                <Route
                  path="/calendar"
                  element={
                    <Protectedroute>
                      <AppoinmentCalendar />
                    </Protectedroute>
                  }
                />
                <Route
                  path="/pet"
                  element={
                    <Protectedroute>
                      <DogDetails />
                    </Protectedroute>
                  }
                />
                <Route
                  path="/owner"
                  element={
                    <Protectedroute>
                      <OwnerDetails />
                    </Protectedroute>
                  }
                />
                <Route path="*" element={<RedirectRoute />} />
              </Routes>
            </div>
          </Content>
          <Footer
            style={{
              textAlign: "center",
            }}
          >
            Created by Simon Bruce
          </Footer>
        </Layout>
      </Layout>
    </>
  );
};

export default App;
