import React from "react";
import { Navigate, useLocation } from "react-router-dom";
import { hasAnyRoles, isAuthenticated, Role } from "util/auth";

const PrivateRoute: React.FC<{ children: JSX.Element; roles?: Role[] }> = ({
  children,
  roles = [],
}) => {
  const location = useLocation();
  return !isAuthenticated() ? (
    <Navigate to={"/"} state={{ from: location }} replace />
  ) : !hasAnyRoles(roles) ? (
    <Navigate to={"/admin/tarjetas"} state={{ from: location }} replace />
  ) : (
    children
  );
};

export default PrivateRoute;
