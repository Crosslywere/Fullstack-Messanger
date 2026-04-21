import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../utilities/BackendApi";

function LogoutPage() {
  const navigate = useNavigate();
  const [completed, setCompleted] = useState(false);
  useEffect(() => {
    api
      .post("/api/public/auth/logout")
      .then(() => setCompleted(true))
      .finally(() => setTimeout(() => navigate("/"), 5000));
  });
  return (
    <div className="min-h-dvh container flex place-content-center items-center mx-auto">
      <h1 className="text-4xl font-black">
        Logg{completed ? "ed" : "ing"} Out
      </h1>
    </div>
  );
}

export default LogoutPage;
