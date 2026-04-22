import { useEffect, type ReactNode } from "react";
import { useNavigate } from "react-router-dom";
import api from "../utilities/BackendApi";

function ChatPage(): ReactNode {
  const navigate = useNavigate();
  useEffect(() => {
    setInterval(() => {
      api.post("/api/public/auth/validate");
    }, 120_000);
  });
  return (
    <div className="min-h-dvh flex flex-col justify-center items-center">
      <h1 className="font-bold text-2xl my-4">There's no content here!</h1>
      <button
        onClick={() => navigate("/logout", { replace: true })}
        className="bg-emerald-500 px-8 py-2 rounded focus:outline-0"
      >
        Logout
      </button>
    </div>
  );
}

export default ChatPage;
