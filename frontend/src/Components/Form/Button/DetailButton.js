import { useTranslation } from "react-i18next";
import DetailIcon from "../../Icon/DetailIcon";

function DetailButton(props) {
    const { t } = useTranslation();

    return (
        <button
            {...props}
            className="btn btn-secondary btn-sm ml-1"
            title={t('msg_detalhe_item')}>
            <DetailIcon />
        </button>
    );
}

export default DetailButton;