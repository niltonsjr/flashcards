import React from "react";
import { Navigate, useLocation } from "react-router-dom";
import { isAuthenticated } from "util/requests";

const PrivateRoute: React.FC<{ children: JSX.Element }> = ({ children }) => {
  const location = useLocation();
  return isAuthenticated() ? (
    children
  ) : (
    <Navigate to={"/"} state={{ from: location }} replace />
  );
};

export default PrivateRoute;
