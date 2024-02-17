/**
 * Retira da String tudo que não for número.
 * @param {string} value 
 * @returns 
 */
export const onlyDigits = (value) => {
    return value.replace(/\D/g, '');
}

/**
 * Formata um valor em String para o formato Double.
 * @param {string} value 
 */
export const stringToDouble = (value) => {
    if (value != null) {
        value = String(value);
        return value.replace(".", "").replace(",", ".");
    }

    return null;
}

/**
 * Responsável por formatar um double no formato do país.
 */
export const doubleToStringPTBR = (value) => {
    if (value != null) {
        return value.toLocaleString('pt-br', { minimumFractionDigits: 2 });
    }

    return value;
}