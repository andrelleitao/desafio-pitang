/**
 * Responsável por verificar se o acesso é através de um 
 * dispositivo móvel.
 */
export const detectMobile = () => {
    if (/Android|iPhone/i.test(navigator.userAgent)) {
        return true;
    }

    return false;
}

/**
 * Realiza o refresh da página.
 */
export const refresh = () => window.location.reload(true);