import { useTranslation } from "react-i18next";

function LogoutIconText() {
    const { t } = useTranslation();

    return (
        <>
            <i className="icon-power"></i>{t('action_sair')}
        </>
    );
}

export default LogoutIconText;