import { useNavigate } from "react-router-dom";
import Grid from "./Grid/Grid";
import Header from "./Header";

/**
 * Estrutura padrão para a tela de lista. Essa estrutura já consta o Header e o Grid que 
 * deverão ser customizados através dos atributos passados no props.
 */
function  BaseContentList(props) {
    const navigate = useNavigate();

    /**
     * Responsável por guardar o id do cliente que será passado para o ClienteCreate e por
     * exibir o modal na tela.
     */
    function onClickEdit(id) {
        navigate(props.navigate.onClickEdit + "/" + id);        
    }

    return (
        <> 
            <div className="col-lg-12 col-md-12 col-sm-12 mt-4">
                <div className="card">
                    <div className="header">
                        <Header
                            icon={props.header.icon}
                            headerText={props.header.text}
                            mainNavigate={props.header.mainNavigate}
                            currentPage={props.header.currentPage}
                            childNav={props.header.childNav}
                            showAdd={props.showAdd || true}
                            addRoute={props.header.route}
                        />
                    </div>

                    <div className="body">
                        <Grid
                            columns={props.grid.columns}
                            baseUrl={props.grid.baseUrl}
                            onClickEdit={onClickEdit}
                            refreshData={false}
                            filters={props.grid.filters}
                            orders={props.grid.orders}
                        />
                    </div>
                </div>
            </div>
        </>
    )
}

export default BaseContentList;