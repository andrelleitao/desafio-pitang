import { useTranslation } from "react-i18next";
import EditIcon from "../../Icon/EditIcon";

function EditButton(props) {
    const { t } = useTranslation();

    return (
        <button
            {...props}
            className="btn btn-primary btn-sm"
            title={t('msg_edita_item')}>
            <EditIcon />
        </button>
    );
}

export default EditButton;