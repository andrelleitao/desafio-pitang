/**
 * Contém métodos que validam campos de formulário. Ex.: Email, CEP, etc.
 */
/** 
 * Responsável por validar um email.
 * @param {*} email - Email que deve ser validado.
 */
export const emailIsValid = (email) => {
    return /\S+@\S+\.\S+/.test(email);
};

/**
 * Verifica se um objeto está vazio.
 * @param {array} object 
 */
export const checkObjectIsEmpty = (object) => {    
    return !Object.keys(object).length > 0;
}

/**
 * Valida o CPF informado.
 */
export const cpfIsValid = (cpf) => {
    cpf = cpf.replace(/\D/g, '');
    if (cpf.toString().length != 11 || /^(\d)\1{10}$/.test(cpf)) return false;
    var result = true;
    [9, 10].forEach(function (j) {
        var soma = 0, r;
        cpf.split(/(?=)/).splice(0, j).forEach(function (e, i) {
            soma += parseInt(e) * ((j + 2) - (i + 1));
        });
        r = soma % 11;
        r = (r < 2) ? 0 : 11 - r;
        if (r != cpf.substring(j, j + 1)) result = false;
    });
    return result;
}