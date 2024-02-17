import { useTranslation } from 'react-i18next';

/**
 * Contém os botões de salvar e cancelar. Esses botões devem ser padrão para todos os formulários.
 */
function ButtonSaveCancel(props) {
    const { t } = useTranslation();
    const {onSave, onCancel} = props;

    return (
        <div className="m-t-30">
            <a className="btn btn-primary mr-1" onClick={onSave}><i className="fa-solid fa-floppy-disk"></i> {t('action_salvar')}</a>
            <a className="btn btn-primary" onClick={onCancel}><i className="fa-solid fa-xmark"></i> {t('action_cancelar')}</a>
        </div>
    );
}

export default ButtonSaveCancel;