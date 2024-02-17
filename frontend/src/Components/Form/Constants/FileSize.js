import { useTranslation } from "react-i18next";
import COMMON from "../../../common/constants/common";

/**
 * Responsável por exibir o tamanho do arquivo baseando-se nos valores de constantes.
 */
function FileSize() {
    const { t } = useTranslation();

    return (
        <h6 className="mb-0"><strong>{t('min')}:</strong> {COMMON.IMAGE_MIN_FILE_SIZE} Kb / <strong>{t('max')}:</strong> {COMMON.IMAGE_MAX_FILE_SIZE} Kb</h6>
    );
}

export default FileSize;