import "./App.css";
import {
  DesktopOutlined,
  FileOutlined,
  PieChartOutlined,
} from "@ant-design/icons";
import { Breadcrumb, Layout, Menu } from "antd";
import React, { useState } from "react";
import { Route, Routes } from "react-router-dom";
import AppoinmentCalendar from "./Calendar/AppoinmentCalendar";
import Home from "./pages/Home";
import { useNavigate } from "react-router-dom";
import Dogs from "./Dogs/Dogs";
import Dashboard from "./pages/Dashboard";
import DogDetails from "./Dogs/DogDetails";
import { FaUsers } from "react-icons/fa";
import Owners from "./Owners/Owners";
import OwnerDetails from "./Owners/OwnerDetails";

const { Header, Content, Footer, Sider } = Layout;

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
  getItem("Pets", "/petList", <DesktopOutlined />),
  getItem("Owners", "/ownerList", <FaUsers />),
  getItem("Calendar", "/calendar", <FileOutlined />),
];

const App = () => {
  const navigate = useNavigate();
  const [collapsed, setCollapsed] = useState(false);

  return (
    <Layout
      style={{
        minHeight: "100vh",
      }}
    >
       {/*Sidebar for computer ends  */}
      <Sider
        collapsible
        collapsed={collapsed}
        onCollapse={(value) => setCollapsed(value)}
        className="hidden md:block"
        width={150}
        style={{
          overflow: 'auto',
          height: '100vh',
          position: 'fixed',
          left: 0,
          top: 0,
          bottom: 0,
        }}
      >
        <div className=" h-[30px] m-2 flex justify-center items-center logo" />
        <Menu
          theme="dark"
          defaultSelectedKeys={["/"]}
          mode="inline"
          items={items}
          onClick={({ key }) => navigate(key)}
        />
      </Sider>
      {/*Sidebar for computer ends */}

      {/*Navbar for mobile */}
      <Header
      style={{
        position: 'fixed',
        zIndex: 1,
        width: '100%',
      }} className="md:hidden"
    >
      <div className="logo" />
      <Menu
        theme="dark"
        mode="horizontal"
        defaultSelectedKeys={['2']}
        items={new Array(3).fill(null).map((_, index) => ({
          key: String(index + 1),
          label: `nav ${index + 1}`,
        }))}
      />
    </Header>
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
          >
          </Breadcrumb>
          <div
            className="site-layout-background"
            style={{
              padding: 24,
              minHeight: 360,
            }}
          >
            {}
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/petList" element={<Dogs />} />
              <Route path="/ownerList" element={<Owners />} />
              <Route path="/calendar" element={<AppoinmentCalendar />} />
              <Route path="/pet" element={<DogDetails/>} />
              <Route path="/owner" element={<OwnerDetails/>} />
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
