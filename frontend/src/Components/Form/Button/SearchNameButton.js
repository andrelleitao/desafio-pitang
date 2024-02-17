import { useTranslation } from "react-i18next";

function SearchNameButton(props) {
    const { t } = useTranslation();

    return (
        <a className="btn btn-primary" onClick={props.onClick}>
            {t('consultar')}
        </a>
    );
}

export default SearchNameButton;