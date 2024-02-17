import { Button, Modal } from "react-bootstrap";
import { useTranslation } from "react-i18next";

/**
 * Estrutura padrão do modal de pesquisa.
 */
function ModalFilter(props) {
    const { t } = useTranslation();

    return (
        <Modal show={props.show}>
            <Modal.Header>
                <Modal.Title>{t('filtroAvancado')}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {props.children}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="btn btn-dark" onClick={props.onSearch}>
                    {t('action_pesquisar')}
                </Button>
                <Button variant="btn btn-outline-secondary" onClick={props.onClose}>
                    {t('action_fechar')}
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default ModalFilter;