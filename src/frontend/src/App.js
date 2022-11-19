import "./App.css";
import {
  PieChartOutlined,
  UserOutlined,
  CalendarOutlined
} from "@ant-design/icons";
import { Breadcrumb, Layout, Menu, Drawer } from "antd";
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

const App = () => {
  const navigate = useNavigate();
  const [collapsed, setCollapsed] = useState(false);
  const location = useLocation();

  const [open, setOpen] = useState(false);

  const onClose = () => {
    setOpen(false);
  };

  const handleDrawer = () => {
    setOpen(!open);
  };

  return (
    <Layout
      style={{
        minHeight: "100vh",
      }}
    >
      {/*Sidebar for compute  */}
      <Sider
        collapsible
        collapsed={collapsed}
        onCollapse={(value) => setCollapsed(value)}
        className="hidden md:block"
        collapsedWidth={75}
        width={151}
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
        <Menu
          theme="dark"
          defaultSelectedKeys={location.pathname}//used location from react router dom to pass the currently open path to the default selected key
          mode="inline"
          items={items}
          onClick={({ key }) => navigate(key)}
        />
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
          </ul>
        </Drawer>
      </div>
      {/*Navbar for mobile ends*/}
      <Layout className="site-layout md:ml-[150px]">
        <Content
          style={{
            margin: "0 16px",
          }}
        >
          <Breadcrumb
            style={{
              margin: "16px 0",
            }}
          ></Breadcrumb>
          <div className="site-layout-background min-h-[360px] mt-[100px] md:mt-[0px]">
            {}
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/petList" element={<Dogs />} />
              <Route path="/ownerList" element={<Owners />} />
              <Route path="/calendar" element={<AppoinmentCalendar />} />
              <Route path="/pet" element={<DogDetails />} />
              <Route path="/owner" element={<OwnerDetails />} />
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
  );
};

export default App;
