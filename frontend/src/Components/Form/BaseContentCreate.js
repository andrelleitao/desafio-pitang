import { useTranslation } from "react-i18next";
import { useNavigate, useParams } from "react-router-dom";
import api from "../../common/api";
import ButtonSaveCancel from "./ButtonSaveCancel";
import Header from "./Header";
import { checkObjectIsEmpty } from "../../util/validationUtils"
import { useEffect } from "react";
import { success } from "../Common/Message";

function BaseContentCreate(props) {
    const { t } = useTranslation();
    const navigate = useNavigate();
    
    // Utilizados no editar. O id vem na rota.
    const routeParams = useParams();    
    const id = !checkObjectIsEmpty(routeParams) ? routeParams.id : null;
    
    /**
     * Responsável por enviar os dados para a API.
     */
    function onSave() {  
        if (onValidate()) {
            // Quando for diferente de null, é uma alteração.
            // Caso contrário, é um novo registro.
            if (id !== null) {
                api.patch(props.baseUrl + "/" + id, payload())
                    .then(() => {
                        onSuccess();
                    });               
            } else {
                api.post(props.baseUrl, payload())
                    .then(() => {
                        onSuccess();
                    });                    
            }
        }
    }

    /**
     * Responsável por realizar a validação do formulário.
     * Esse método faz referência ao método passado no props.
     */
    function onValidate() {        
        return props.onValidate();
    }

    /**
     * Monta o payload que será enviado para API.
     * Esse método faz referência ao método passado no props.
     */
    function payload() {           
        return props.payload();
    }

    /**
     * Responsável por resetar os campos para o estado inicial.
     */
    function onReset() {
        props.onReset();
    }

    /**
     * Evento acionado quando a operação é realizada com sucesso.
     */
    function onSuccess() {
        if (id === null) {
            success(t('msg_sucesso_cadastro'));
            onReset();
        } else {
            success(t('msg_sucesso_atualizacao'));
            onCancel();
        }
    }

    /**
     * Responsável por retornar para lista.
     */
    function onCancel() {
        navigate(props.navigate.onClickCancel);
    }

    /**
     * Responsável por preencher o formulário com os dados retornados da API.
     * @param data Estrutura JSON retornado da API.
     */
    function onFill(data) {
        props.onFill(data);
    }

    /**
     * Realiza uma busca do recurso via ID.
     */
    function findById(id) {
        api.get(props.baseUrl + "/" + id)
            .then((response) => {
                onFill(response.data);
            });
    }

    useEffect(() => {
        // Caso identifique que o ID está preenchido, a aplicação entenderá 
        // que trata-se de uma edição de registro e portanto deve carregar todos os dados na tela.
        // Para isso é necessário uma consulta na API fazendo uso do ID do registro.
        if (id != null) {            
            findById(id);
        }
    }, []);

    return (
        <div className="col-lg-12 col-md-12 col-sm-12 mt-4">
            <div className="card">
                <div className="header">
                    {
                        <Header
                            icon={props.header.icon}
                            headerText={props.header.text}
                            mainNavigate={props.header.mainNavigate}
                            currentPage={props.header.currentPage}
                            childNav={props.header.childNav}
                            showGrid={true}
                            gridRoute={props.header.route}
                        />
                    }
                </div>
                <div className="body">
                    { props.child }
                    <ButtonSaveCancel onSave={onSave} onCancel={onCancel} />
                </div>
            </div>
        </div>
    );

}

export default BaseContentCreate;