import i18n from 'i18next';
import { error } from "../Components/Common/Message";
import COMMON from '../common/constants/common';

/**
 * Valida um arquivo selecionado 'FILE'.
 */
export const validateSelectedFile = (selectedFile) => {
    const MIN_FILE_SIZE = COMMON.IMAGE_MIN_FILE_SIZE;
    const MAX_FILE_SIZE = COMMON.IMAGE_MAX_FILE_SIZE;

    if (!selectedFile) {
        error(i18n.t("msg_selecione_um_arquivo"));
        return false;
    }

    const fileSizeKiloBytes = selectedFile.size / 1024

    if (fileSizeKiloBytes < MIN_FILE_SIZE) {
        error(i18n.t("msg_tamanho_arquivo_menor_limite_minimo"));   
        return false;
    }
    if (fileSizeKiloBytes > MAX_FILE_SIZE) {
        error(i18n.t("msg_tamanho_arquivo_maior_limite_maximo"));
        return false;
    }

    return true;
};

/**
 * Retorna a extensão do arquivo.
 */
export const filenameExtension = (filename) => {
    return filename.split('.').pop();
}

/**
 * Retorna o preview de uma image.
 */
export const imagePreview = (file) => {
    return URL.createObjectURL(file);
}

/** 
 * Libera memória do objeto quando ele é desmontado.
 */
export const imagePreviewRevoke = (objectUrl) => {
    URL.revokeObjectURL(objectUrl);
}