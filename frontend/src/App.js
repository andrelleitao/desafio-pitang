import { Routes, Route } from "react-router-dom";
import { ProtectedRoute } from "./Components/Route/ProtectedRoute";
import Login from "./screens/Auth/Login";
import Main from "./screens/Dashboard/Main";
import { MENU_ROUTE } from "./Components/Common/menu/constants/menuRoute";
import CarNew from "./screens/Main/Car/CarNew";
import Resumo from "./screens/Dashboard/Dashboard/Resumo";
import CarList from "./screens/Main/Car/CarList";

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Login />} />

      <Route element={<Main />}>

        { /** DASHBOARD */}
        <Route
          path={MENU_ROUTE.DASHBOARD}
          element={
            <ProtectedRoute>
              <Resumo />
            </ProtectedRoute>
          }
        />

        { /** Car */}
        <Route path={MENU_ROUTE.CAR}>
          <Route index element={<ProtectedRoute><CarList /></ProtectedRoute>} />
          <Route path={MENU_ROUTE.CAR_NEW} element={<ProtectedRoute><CarNew /></ProtectedRoute>} />
          <Route path={MENU_ROUTE.CAR_NEW + "/:id"} element={<ProtectedRoute><CarNew /></ProtectedRoute>} />
        </Route>

      </Route>

    </Routes>

  );
}