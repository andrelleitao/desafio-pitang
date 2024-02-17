import axios from "axios";
import { BackendErrorMessage } from "../Components/Common/Backend";
import HTTP_STATUS from "./constants/httpStatus";

import { getAuthStorage } from './localStorage';

const api = axios.create({
    baseURL: process.env.REACT_APP_API_URL,
});

/**
 * Adiciona o token nas requisições.
 */
api.interceptors.request.use(async config => {
    const auth = getAuthStorage();

    if (auth) {
        let token = auth.token;
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

/**
 * Caso o token tenha expirado, o serviço retornará 401 e 
 * portanto a aplicação deverá redirecionar o usuário para a página de login.
 */
api.interceptors.response.use(response => {
    return response;
}, error => {
    let status = error.response.status;

    // Caso o acesso não esteja permitido, a aplicação
    // redireciona o usuário para a página de login.
    if (status === HTTP_STATUS.UNAUTHORIZED) {
        window.location.href = "/";
    } else {
        BackendErrorMessage(error);
        return Promise.reject(error);
    } 

    return error;
});

export default api;