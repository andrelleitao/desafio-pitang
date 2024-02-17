import { Button, Modal } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import TrashIcon from "../../Icon/TrashIcon";

/**
 * Estrutura padrão do modal de exclusão.
 */
function ModalRemove(props) {
    const { t } = useTranslation();

    return (
        <Modal show={props.show} onHide={props.onClose}>
            <Modal.Header>
                <Modal.Title><TrashIcon />{t('exclusaoRegistro')}</Modal.Title>
            </Modal.Header>
            <Modal.Body>{t('msg_confirma_remocao_registro')}</Modal.Body>
            <Modal.Footer>
                <Button variant="btn btn-danger" onClick={props.onRemove}>
                    {t('sim')}
                </Button>
                <Button variant="btn btn-outline-secondary" onClick={props.onClose}>
                    {t('nao')}
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default ModalRemove;