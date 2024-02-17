/**
 * responsável por armazenar dados no LocalStorage.
 */
import LOCALSTORAGE from './constants/localStorage';

/**
 * Responsável por armazenar/recuperar o body enviado pelo backend.
 */
export const setAuthStorage = (value) => {
    localStorage.setItem(LOCALSTORAGE.AUTH, JSON.stringify(value));
};
export const getAuthStorage = () => {    
    return localStorage.getItem(LOCALSTORAGE.AUTH) != null ? JSON.parse(localStorage.getItem(LOCALSTORAGE.AUTH)) : null;
}

/**
 * Verifica se o usuário está autenticado.
 */
export const isAuthenticated = () => {
    let obj = getAuthStorage();
    
    if(obj != null) {        
        if(obj.token != null) {
            return true;
        }
    }

    return false;
};

/**
 * Responsável por armazenar o menu selecionado. Isso é utilizado quando o usuário realizar uma 
 * refresh na tela. Fazendo uso do LocalStorage conseguimos recuperar o último menu selecionado e 
 * marcar como selecionado.
 */
export const setLastSelectedMenuStorage = (value) => {    
    localStorage.setItem(LOCALSTORAGE.LAST_SELECTED_MENU, value);
}
export const getLastSelectedMenuStorage = () => {
    return localStorage.getItem(LOCALSTORAGE.LAST_SELECTED_MENU);
}

export const setLastSelectedSubmenuStorage = (value) => {
    localStorage.setItem(LOCALSTORAGE.LAST_SELECTED_SUBMENU, value);
}
export const getLastSelectedSubmenuStorage = () => {
    return localStorage.getItem(LOCALSTORAGE.LAST_SELECTED_SUBMENU);
}

/**
 * Limpa os storages que são considerados como temporários.
 */
export const resetTemporary = () => {
    setAuthStorage(null);
    setLastSelectedMenuStorage(null);
    setLastSelectedSubmenuStorage(null);
}