import { browserSessionPersistence, setPersistence } from "firebase/auth";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { UserAuth } from "../auth/AuthContext";
import { auth } from "../firebase";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { user, logIn } = UserAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('')
    try {
      setPersistence(auth, browserSessionPersistence)
      await logIn(email, password);
      navigate("/");
    } catch (error) {
      console.log(error);
      setError(error.message)
    }
  };

  return (
    <>
      <div className="w-full h-screem">
        <img
          className="block absolute w-full h-full object-cover"
          src="https://cdn.pixabay.com/photo/2015/11/03/12/58/dalmatian-1020790_960_720.jpg"
          alt="background"
        />
        <div className="bg-black/60 fixed top-0 left-0 w-full h-screen"></div>
        <div className="fixed w-full px-4 py-24 z-50">
          <div className="max-w-[450px] md:h-[600px] mx-auto bg-black/75 text-white">
            <div className="max-w-[320px] mx-auto py-16">
              <h1 className="text-3x font-bold text-white">Log In</h1>
              {error ? <p className="p-3 bg-red-400 my-2">{error}</p>:null}
              <form
                onSubmit={handleSubmit}
                className="w-full flex flex-col py-4"
              >
                <input
                  onChange={(e) => setEmail(e.target.value)}
                  className="p-3 my-2 bg-gray-700 rounded"
                  type="email"
                  placeholder="Email"
                  autoComplete="email"
                />
                <input
                  onChange={(e) => setPassword(e.target.value)}
                  className="p-3 my-2 bg-gray-700 rounded"
                  type="password"
                  placeholder="Password"
                  autoComplete="current-password"
                />
                <button className="bg-gradient-to-r from-[#F06CA6] via-[#F58352] to-[#F06CA6] py-3 my-6 rounded-full font-bold hover:scale-105">
                  Ingresar
                </button>
                <div className="flex justify-between items-center text-sm text-gray-600">
                  
                  <p> Need Help?</p>
                </div>
                <div className="py-4">
                  <p>Test Credentials</p>
                  <p>Email: simon@simon.com</p>
                  <p>Password: simon12</p>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
