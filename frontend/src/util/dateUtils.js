import COMMON from "../common/constants/common";

/**
 * 
 * @param {object} date Objeto Date.
 * @param {*} format Estrutura de formatação. Ex.: dd/mm/yyyy
 */
export const formatDate = (date, format) => {
    let month = date.getMonth() + 1;

    if (month < 10) {
        month = "0" + month;
    }

    return format.replace('mm', month)
        .replace('yyyy', date.getFullYear())
        .replace('dd', date.getDate());
}

/**
 * Responsável por retornar o última dia do mês.
 * @param {*} year Ano desejado.
 * @param {*} month Mês desejado.
 * @returns O último dia referente ao mês e ano informados.
 */
export const lastMonthDay = (year, month) => {
    return new Date(year, month, 0).getDate()
}

/**
 * Retorna a última data do mês.
 */
export const lastMonthDate = () => {
    const obj = new Date();
    const year = obj.getFullYear();
    const month = obj.getMonth() + 1;
    const lastMonthDayTmp = lastMonthDay(year, month);
    const formattedMonth = (month >= 10) ? month : "0" + month;

    return year + "-" + formattedMonth + "-" + lastMonthDayTmp;
}

/**
 * Retorna a primeira data do mês.
 */
export const firstMonthDate = () => {
    const obj = new Date();
    const year = obj.getFullYear();
    const month = obj.getMonth() + 1;
    const formattedMonth = (month >= 10) ? month : "0" + month;

    return `${year}-${formattedMonth}-01`;
}

/**
 * Retorna a primeira data do mês.
 */
export const today = () => {
    const obj = new Date();
    const year = obj.getFullYear();
    const month = obj.getMonth() + 1;
    const formattedMonth = (month >= 10) ? month : "0" + month;
    const date = obj.getDate();
    const formattedDate = (date >= 10) ? date : "0" + date;

    return year + "-" + formattedMonth + "-" + formattedDate;
}

/**
 * Retorna a primeira data do mês.
 */
export const formattedToday = () => {
    const obj = new Date();
    const year = obj.getFullYear();
    const month = obj.getMonth() + 1;
    const formattedMonth = (month >= 10) ? month : "0" + month;
    const date = obj.getDate();

    return date + "/" + formattedMonth + "/" + year;
}

/**
 * Retorna a data formatada a partir de uma data do banco.
 */
export const formattedDb = (date) => {
    const arrDate = date.split("-");
    return arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];
}
export const converDatetimeToDateDb = (date) => {
    const arrDatetime = date.split("T");    
    return arrDatetime[0];
}

/**
 * Retorna a data formatada a partir de uma data do banco.
 */
export const formattedDatetimeDb = (date) => {
    if (date !== null && date !== '') {
        let arrDatetime = date.split("T");
        let arrDate = arrDatetime[0].split("-");
        let arrHour = arrDatetime[1].split(":");
        return arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0] + " " + arrHour[0] + ":" + arrHour[1];
    }

    return date;
}
export const formattedDatetimeDbExt = (date) => {
    if (date !== null && date !== '') {
        date = formattedDatetimeDb(date);
        if (date != null) {
            let arr = date.split(" ");
            return arr[0] + " às " + arr[1];
        }
    }
    return date;
}

/**
 * Calcula a diferença de dias entre as datas.
 */
export const diffDays = (date1, date2) => {
    let difference = date1.getTime() - date2.getTime();
    let totalDays = Math.ceil(difference / (1000 * 3600 * 24));
    return totalDays;
}

/**
 * Responsável por converter uma data em String para um objeto Date.
 */
export const stringToDate = (dateStr) => {
    let date = new Date(dateStr);
    return date;
}

/**
 * Retorna um datetime formatado para ser enviado para a API. 
 * Todo datetime deve utilizar esse método para montar o payload.
 */
export const formattedLocalDatetimeToJson = (dateStr) => {
    if (dateStr != null) {
        let parts = dateStr.split("T");
        return parts[0] + " " + parts[1] + ":00";
    }

    return dateStr;
}

/**
 * Retorna a data e hora atual.
 */
export const now = () => {
    const obj = new Date();
    const year = obj.getFullYear();
    const month = obj.getMonth() + 1;
    const formattedMonth = (month >= 10) ? month : "0" + month;
    const date = obj.getDate();
    const formattedDate = (date >= 10) ? date : "0" + date;

    const hours = obj.getHours();
    const formattedHours = (hours >= 10) ? hours : "0" + hours;
    const minutes = obj.getMinutes();
    const formattedMinutes = (minutes >= 10) ? minutes : "0" + minutes;

    return formattedDate + "/" + formattedMonth + "/" + year + " " + formattedHours + ":" + formattedMinutes;
}

/**
 * Retorna a data e hora atual formatada com texto base.
 */
export const nowFormatted = () => {
    const partsNow = now().split(" ");

    return partsNow[0] + " às " + partsNow[1];
}

/**
 * Retorna o ano atual.
 */
export const year = () => {
    return new Date().getFullYear();
}

/**
 * Retorna o mês atual.
 */
export const month = () => {
    return new Date().getMonth();
}

/**
 * Retorna o nome do mÊs a partir do número.
 */
export const getMonthName = (month) => {
    let months = COMMON.MONTHS;
    let selectedMonth = null;

    for (var i = 1; i <= months.length; i++) {
        if (i === month) {
            selectedMonth = months[i];
            break;
        }
    }

    return selectedMonth;
}

/**
 * Retorna a hora atual.
 */
export const timeNow = () => {
    const obj = new Date();
    const hours = obj.getHours();
    const formattedHours = (hours >= 10) ? hours : "0" + hours;
    const minutes = obj.getMinutes();
    const formattedMinutes = (minutes >= 10) ? minutes : "0" + minutes;
    const seconds = obj.getSeconds();
    const formattedSeconds = (seconds >= 10) ? seconds : "0" + seconds;

    return formattedHours + ":" + formattedMinutes + ":" + formattedSeconds;
}