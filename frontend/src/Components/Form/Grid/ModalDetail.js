import { Button, Modal } from "react-bootstrap";
import { useTranslation } from "react-i18next";

/**
 * Estrutura padrão do modal de detalhe.
 */
function ModalDetail(props) {
    const { t } = useTranslation();

    return (
        <Modal show={props.show}>
            <Modal.Header>
                <Modal.Title>{t('detalhe')}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {props.children}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="btn btn-outline-secondary" onClick={props.onClose}>
                    {t('action_fechar')}
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default ModalDetail;