import React, { useState } from "react";
import "./Auth.css";
import Signin from "./Signin";
import Signup from "./Signup";

const Auth = () => {
  const [isRegister, setIsRegister] = useState(false);

  const togglePanel = () => {
    setIsRegister(!isRegister);
  };

  return (
    <div className="auth-container">
      <div className={`auth-box ${isRegister ? "rotate-active" : ""}`}>
        <div className="auth-cover">
          <div className="front">
            <img src="images/login-page2.png" alt="Login" />
            <div className="text">
              <span className="text-1">
                Organize Your Tasks, Simplify Your Life
              </span>
              <span className="text-xs">
                Join us and take control of your tasks today!
              </span>
            </div>
          </div>
          <div className="back">
            <img src="images/register-page2.png" alt="Register" />
            <div className="text">
              <span className="text-2">Join Us Today!</span>
              <span className="text-xs">
                Create an account and start managing your tasks.
              </span>
            </div>
          </div>
        </div>
        <div className="forms">
          <div className="form-content">
            {!isRegister ? (
              <Signin togglePanel={togglePanel} />
            ) : (
              <Signup togglePanel={togglePanel} />
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Auth;
