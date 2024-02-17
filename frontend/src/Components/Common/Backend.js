import { toast } from "react-toastify";
import HTTP_STATUS from "../../common/constants/httpStatus";

/**
 * Responsável por tratar o objeto error retornado da API.
 * Trata tanto o objeto de erro customizado, enviado pela API, e também
 * a estrutura padrão das requisiões HTTP.
 * @param {object} error Objeto error da biblioteca AXIOS.
 */
export const BackendErrorMessage = (error) => {
    let status = error.response.status;
    
    if(status === HTTP_STATUS.BAD_REQUEST) {
        toast.warn(error.response.data.message);        
    } else if(status === HTTP_STATUS.INTERNAL_SERVER_ERROR) {        
        toast.error(error.message);        
    }
}