import { useTranslation } from "react-i18next";
import TrashIcon from "../../Icon/TrashIcon";

function TrashButton(props) {
    const { t } = useTranslation();

    return (
        <button
            {...props}
            className="btn btn-danger btn-sm ml-1" 
            title={t('msg_remove_item')}>
            <TrashIcon />
        </button>
    );
}

export default TrashButton;