import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";

function Pagination(props) {
    const { t } = useTranslation();
    const [pagination, setPagination] = useState([]);
    const { data, start, end, total, setPage, page } = props;

    /**
     * Responsável por montar a paginação de acordo com o pageable fornecido.
     * @param {object} Retorno do serviço pageable do Spring Boot.
     */
    function createPagination() {        
        if (data !== null) {   
            // Cria a paginação.
            let pagination = [];
            pagination.push(
                <li key="first" className={data.first ? "page-item disabled" : "page-item"}>
                    <a href="#" className="page-link" onClick={(e) => { if (!data.first) { setPage(0); e.preventDefault(); } }}><i className="fas fa-chevron-double-left"></i></a>
                </li>
            );
            pagination.push(
                <li key="previous" className={data.first ? "page-item disabled" : "page-item"}>
                    <a href="#" className="page-link" onClick={(e) => { if (!data.first) { setPage(page - 1); e.preventDefault(); } }}><i className="fas fa-chevron-left"></i></a>
                </li>
            );

            // Fluxo que limita a exibição das páginas.
            // Limite de páginas que serão exibidas
            let limitPages = 5;

            // Total de páginas retornado no serviço pageable.
            let totalPages = data.totalPages;
            
            // Intervalo de páginas que é exibido para o usuário.
            let startPage = Math.floor(page / limitPages) * limitPages;
            let endPage = Math.min(startPage + limitPages, totalPages); 
                                    
            for (let i = startPage; i < endPage; i++) {
                pagination.push(
                    <li key={i} className={data.number == i ? "page-item active" : "page-item"}>
                        <a href="#" className="page-link" onClick={(e) => { setPage(i); e.preventDefault(); }}><strong>{i + 1}</strong></a>
                    </li>
                );
            }

            pagination.push(
                <li key="next" className={data.last ? "page-item disabled" : "page-item"}>
                    {
                        /** 
                         * Permite clicar quando não for a última página. 
                         * A próxima página deve ser a soma da página atual em 'page' + 1.
                         * */
                    }
                    <a href="#" className="page-link" onClick={(e) => { if (!data.last) { setPage(page + 1); e.preventDefault(); } }}><i className="fas fa-chevron-right"></i></a>
                </li>
            );
            pagination.push(
                <li key="last" className={data.last ? "page-item disabled" : "page-item"}>
                    {
                        /** 
                         * Permite clicar quando não for a última página. 
                         * A próxima página deve ser a soma da página atual em 'page' + 1.
                         * */
                    }
                    <a href="#" className="page-link" onClick={(e) => {if (!data.last) { setPage(data.totalPages - 1); e.preventDefault(); } }}><i className="fas fa-chevron-double-right"></i></a>
                </li>
            );

            setPagination(pagination);
        }
    }

    useEffect(() => {
        createPagination();
    }, [data]);

    return (
        <div className="row mt-5 mr-0">
            <div className="col-sm-4 pt-2">
                {t('msg_dados_navegacao_grid', { "start": start, "end": end, "total": total })}
            </div>
            <div className="col-sm-8">
                <ul className="pagination pagination-sm justify-content-end">                
                    {
                        pagination.map((item) => (
                            item
                        ))
                    }
                </ul>
            </div>
        </div>
    );
}

export default Pagination;