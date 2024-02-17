import { Navigate } from "react-router-dom";
import { isAuthenticated } from "../../common/localStorage";

/**
 * Responsável por criar o componente que restringe acesso a uma rota caso
 * o usuário não esteja autenticado. 
 */
export const ProtectedRoute = ({ children }) => {
    const authenticated = isAuthenticated();
    
    // Se o usuário não estiver autenticado, retorna para 
    // a tela de login.
    if (!authenticated) {
        return <Navigate to="/" />;
    }

    return children;
};