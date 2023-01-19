import { browserSessionPersistence, setPersistence } from "firebase/auth";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { UserAuth } from "../auth/AuthContext";
import { auth } from "../firebase";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Checkbox, Form, Input } from "antd";
import KaninosLogo from "../assets/kaninoslogoBackground.png";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { user, logIn } = UserAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    // e.preventDefault();
    setError("");
    try {
      setPersistence(auth, browserSessionPersistence);
      await logIn(email, password);
      navigate("/");
    } catch (error) {
      console.log(error);
      setError(error.message);
    }
  };

  const onFinish = (values) => {
    console.log("Received values of form: ", values);
  };

  return (
    <>
      <div className="w-full h-screem ">
        {/* <img
          className="block absolute w-full h-full object-cover"
          src="https://cdn.pixabay.com/photo/2015/11/03/12/58/dalmatian-1020790_960_720.jpg"
          alt="background"
        /> */}
        <div className=" fixed top-0 left-0 w-full bg-gray-300 h-screen"></div>
        <div className="fixed w-full px-4 py-24 z-50">
          <div className="max-w-[450px] md:h-[600px] mx-auto bg-white text-white">
            <div className="max-w-[320px] mx-auto py-8 flex flex-col justify-center">
              <div className="flex justify-center">
                <img className="" src={KaninosLogo} />
              </div>
              <h1 className=" text-black">Log In</h1>
              {error ? <p className="p-3 bg-red-400 my-2">{error}</p> : null}
              <Form
                name="normal_login"
                className="login-form"
                initialValues={{
                  remember: true,
                }}
                onFinish={handleSubmit}
              >
                <Form.Item
                  name="username"
                  rules={[
                    {
                      required: true,
                      message: "Please input your Username!",
                    },
                  ]}
                >
                  <Input
                    onChange={(e) => setEmail(e.target.value)}
                    prefix={<UserOutlined className="site-form-item-icon" />}
                    placeholder="Username"
                  />
                </Form.Item>
                <Form.Item
                  name="password"
                  rules={[
                    {
                      required: true,
                      message: "Please input your Password!",
                    },
                  ]}
                >
                  <Input
                    onChange={(e) => setPassword(e.target.value)}
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    placeholder="Password"
                  />
                </Form.Item>
                <button className="bg-gradient-to-r w-full from-[#39b89e] via-[#39b89e] to-[#39b89e] p-3 my-2 rounded-lg font-bold hover:scale-105">
                  Log in
                </button>
              </Form>
              <div className="text-black">
                <p>
                  Username = simon@simon.com
                </p>
                <p>
                  password = kaninos12345
                </p>
              </div>

            </div>

          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
