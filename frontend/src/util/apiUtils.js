/**
 * Contém tratativas de dados que são necessários na API.
 */

export const genderEnglishToPorgueseConvert = (value) => {
    return value === 'MALE' ? 'MASCULINO' : 'FEMININO';
}

export const genderPortugueseToEnglishConvert = (value) => {
    return value === 'MASCULINO' ? 'MALE' : 'FEMALE';
}

export const yesNoConvert = (value) => {
    return value === 'YES' ? true : false;
}

/**
 * Formata a data no padrão dd/mm/yyyy.
 * @param {string} value - Data no formato yyyy-mm-dd. 
 */
export const formatDateToDDMMYYYY = (value) => {
    if(value !== null) {
        let parts = value.split("-");
        return parts[2] + "/" + parts[1] + "/" + parts[0];
    }  
    
    return null;
}

/**
 * Formata a data no padrão dd/mm/yyyy.
 * @param {string} value - Data no formato yyyy-mm-dd. 
 */
export const formatDateToDDMMYYYY_HHMMSS = (value) => {
    if (value !== null && value !== '') {
        let parts = value.split(" ");
        return formatDateToDDMMYYYY(parts[0]) + " " + parts[1];
    }
    
    return '';
}