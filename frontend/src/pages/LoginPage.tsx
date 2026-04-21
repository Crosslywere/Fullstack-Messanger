import {
  useState,
  type ChangeEvent,
  type ReactNode,
  type SubmitEvent,
} from "react";
import { BsEye, BsEyeSlash } from "react-icons/bs";
import { useNavigate } from "react-router-dom";
import { type AuthRequest, type AuthResponse } from "../Types";
import api from "../utilities/BackendApi";

export function LoginPage(): ReactNode {
  const [authData, setAuthData] = useState<AuthRequest>({
    username: "",
    password: "",
  });
  const [showPassword, setShowPassword] = useState(false);
  const navigateTo = useNavigate();
  const submit = (event: SubmitEvent) => {
    event.preventDefault();
    api
      .post<AuthResponse>("/api/public/auth/login", authData)
      .then((resp) => {
        if (resp.data.success) {
          navigateTo("/chat", {
            replace: true,
            state: {
              message: resp.data.message,
            },
          });
        }
      })
      .catch(console.error);
  };
  const handleInput = (event: ChangeEvent<HTMLInputElement>) => {
    setAuthData({ ...authData, [event.target.name]: event.target.value });
  };
  return (
    <div className="min-h-dvh container flex place-content-center items-center mx-auto">
      <form onSubmit={submit} className="border bg-white dark:bg-gray-900 p-6">
        <h1 className="font-bold text-center text-2xl my-4">Login</h1>
        <div className="mb-4">
          <label htmlFor="username" className="ms-2">
            Username
          </label>
          <input
            type="text"
            id="username"
            name="username"
            onChange={handleInput}
            value={authData.username}
            placeholder="Enter username..."
            className="border-b-2 border-emerald-500 px-4 py-2 focus:outline-0 w-full"
          />
        </div>
        <div className="mb-4 relative">
          <label htmlFor="password" className="ms-2">
            Password
          </label>
          <input
            type={showPassword ? "text" : "password"}
            name="password"
            id="password"
            onChange={handleInput}
            value={authData.password}
            placeholder="Enter your password..."
            className="border-b-2 border-emerald-500 ps-4 pe-8 py-2 focus:outline-0 w-full"
          />
          <label className="absolute bottom-3 right-1.5">
            {showPassword ? (
              <BsEye className="size-5" />
            ) : (
              <BsEyeSlash className="size-5" />
            )}
            <input
              type="checkbox"
              id="showPassword"
              className="hidden"
              name="showPassword"
              checked={showPassword}
              onChange={(ev) => {
                setShowPassword(ev.target.checked);
              }}
            />
          </label>
        </div>
        <p className="text-center mb-4">
          Don't have an account?
          <br />
          Register{" "}
          <a href="/register" className="font-medium text-emerald-500">
            here
          </a>
          .
        </p>
        <button
          type="submit"
          className="bg-emerald-500 font-medium py-2 rounded w-full"
        >
          Login
        </button>
      </form>
    </div>
  );
}
