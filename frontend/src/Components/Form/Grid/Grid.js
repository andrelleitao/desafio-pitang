import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import api from '../../../common/api';
import { formatDateToDDMMYYYY } from '../../../util/apiUtils';
import { BackendErrorMessage } from '../../Common/Backend';
import { DATA_TYPE } from './constants/dataType';
import TotalPerPageSelect from './TotalPerPageSelect';
import Pagination from './Pagination';
import Table from './Table';
import Loading from '../Loading';
import ModalRemove from './ModalRemove';
import CheckIcon from '../../Icon/CheckIcon';
import EditButton from '../Button/EditButton';
import TrashButton from '../Button/TrashButton';
import SearchButton from '../Button/SearchButton';
import UncheckIcon from '../../Icon/UncheckIcon';

/**
 * Responsável por exibir registros no GRID.
 */
function Grid(props) {
    const { t } = useTranslation();
    const [showLoading, setShowLoading] = useState(false);

    // Atributos do Grid.
    const [columns] = useState(props.columns);
    const [rows, setRows] = useState([]);
    const [data, setData] = useState(null);

    // Atributos para a pesquisa padrão.
    const [baseUrl] = useState(props.baseUrl);
    const [search, setSearch] = useState('');

    // Páginas
    const [start, setStart] = useState(1);
    const [end, setEnd] = useState(10);
    const [total, setTotal] = useState();

    // Payload
    const [filters] = useState(props.filters);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(10);
    const [orders] = useState(props.filters);

    // Modal Remover
    const [show, setShow] = useState(false);
    const onClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [rowId, setRowId] = useState(null);

    /**
     * Payload utilizado na requisição com a API.
     * @returns 
     */
    function payload() {
        const obj = {
            filters: filters,
            page: page,
            size: size,
            orders: orders
        };

        return obj;
    }

    /**
     * Responsável por realizar a busca de dados a partir da API informada.
     */
    function loadData() {
        setShowLoading(true);

        api.post(baseUrl + "/pageable", payload())
            .then((response) => {
                // Objeto pageable é retornado no serviço.
                // Ele contém dados da paginação.
                let data = response.data;

                if (data && !data.empty) {
                    setData(data);

                    // Adiciona os objetos retornados nas linhas.                   
                    setRows(data.content);
                    setStart(data.number + 1);
                    setEnd(data.totalPages);
                    setTotal(data.totalElements);
                } else {
                    setRows([]);
                    setStart(0);
                    setEnd(0);
                    setTotal(0);
                }
            })
            .finally(() => {
                setShowLoading(false);
            });
    }

    /**
     * Responsável por configurar o filtro com o valor digitado no campo 'pesquisar' e 
     * em seguida realizar uma nova consulta com o valor informado.
     */
    function handleClickSearch() {
        if (filters != null) {
            filters[0].value = search;
            setPage(0);
        }

        loadData();
    }

    /**
     * Formata o valor do Grid de acordo com o tipo.
     */
    function formattedValue(value, type) {
        if (value != null) {
            if (type === DATA_TYPE.DATE) {
                return formatDateToDDMMYYYY(value);

            } else if (type === DATA_TYPE.BOOLEAN) {
                return value ? <CheckIcon/> : <UncheckIcon/>
            }
        }

        return value;
    }

    /**
     * Aciona o evento onClickEdit que foi passado.
     */
    function onClickEdit(id) {
        props.onClickEdit(id);
    }

    /**
     * Responsável por abrir o modal solicitando a 
     * confirmação de remoção do registro.
     */
    function handleClickRemove(id) {
        setRowId(id);
        handleShow();
    }

    /** 
     * Responsável por realizar a remoção do registro selecionado.
     */
    function onRemove() {
        api.delete(baseUrl + "/" + rowId)
            .then(() => {
                loadData();
                onClose();
            })
            .catch(error => {
                BackendErrorMessage(error);
            });
    }

    useEffect(() => {
        loadData();
    }, [size, page, props.refreshData]);

    return (
        <>
            { /** Modal de remoção de registro */}
            <ModalRemove show={show} onClose={onClose} onRemove={onRemove} />

            <div className="row mb-2">
                <div className="col-md-6">
                    { /* Guarda o número de registros a serem exibidos. */}
                    <TotalPerPageSelect className="form-control" onChange={(e) => {
                        setSize(e.target.value);
                    }} />
                </div>
                <div className="col-md-6">
                    <form className="form-inline my-2 my-lg-0 float-right">
                        <input className="form-control mr-sm-2" type="search" placeholder={t('action_pesquisar')} value={search} onChange={(e) => { setSearch(e.target.value) }} />
                        <SearchButton onClick={handleClickSearch}/>
                    </form>
                </div>
            </div>

            {
                (showLoading) ? <div className='d-flex justify-content-center'><Loading size="fa-5x" /></div> :
                    <Table>
                        <thead>
                            <tr role="row">
                                {
                                    columns.map((column, index) => {
                                        return (
                                            (column.width != null) ?
                                                <th key={index} style={{ width: column.width }} className={column.isTextCenter ? 'text-center' : ''}>{t(column.label)}</th> :
                                                <th key={index}>{t(column.label)}</th>
                                        );
                                    })
                                }
                                <th className="text-center" style={{ width: "5%" }}>{t('acoes')}</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                rows.map((row, index) => {
                                    let cells = [];
                                    let rowsArray = Object.entries(row);
                                    let columnsArray = Object.entries(columns);

                                    let isObject = false;
                                    let attr = null;
                                    let parts = null;
                                    let object = null;
                                    let value = null;

                                    // Atributos do objeto.
                                    let objAttrs = null;

                                    // Monta as colunas de acordo com o array columns 
                                    // obecendo a ordem da key.
                                    let key = 0;
                                    for (var i = 0; i < columnsArray.length; i++) {
                                        for (var x = 0; x < rowsArray.length; x++) {
                                            // Verifica se existe o "." na String. Esse ponto
                                            // indica que deve exibir a propriedade do objeto.
                                            isObject = columnsArray[i][1].key.includes(".");
                                            if (isObject) {
                                                // Separa o atributo do objeto.
                                                parts = columnsArray[i][1].key.split("\.");
                                                attr = parts[0];
                                            } else {
                                                // O atributo não está dentro de um objeto
                                                // e portanto assume o próprio atributo.
                                                attr = columnsArray[i][1].key;
                                            }

                                            if (attr === rowsArray[x][0]) {
                                                key++;

                                                // Quando for objeto é necessário extrair
                                                // o dado do atributo dele.
                                                if (isObject) {
                                                    object = rowsArray[x][1];
                                                    objAttrs = Object.entries(object);

                                                    for (var xh = 0; xh < objAttrs.length; xh++) {
                                                        // A posição '0' contém o nome do atributo e
                                                        // na posição '1' contém o valor. 
                                                        // O parts[1] contém o nome do atributo extraído do 'entity.id'.
                                                        if (objAttrs[xh][0] === parts[1]) {
                                                            value = objAttrs[xh][1];
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    value = rowsArray[x][1];
                                                }

                                                cells.push(
                                                    <td key={key} className={columnsArray[i][1].isTextCenter ? 'text-center': ''}>{formattedValue(value, columnsArray[i][1].type)}</td>
                                                );
                                            }
                                        }
                                    }

                                    return (
                                        <tr key={index}>
                                            {cells}

                                            <td className="text-center">
                                                {/*<button className="btn btn-sm round btn-outline-success"> Accept</button>*/}
                                                { /* Contém as ações da linha. */}
                                                <EditButton
                                                    onClick={() => {
                                                        onClickEdit(row.id);
                                                    }}
                                                />                                                
                                                <TrashButton onClick={() => handleClickRemove(row.id)}/>
                                            </td>
                                        </tr>
                                    );
                                })
                            }
                        </tbody>
                    </Table>
            }
            <Pagination start={start} end={end} total={total} data={data} setPage={setPage} page={page} />

        </>
    );
}

export default Grid;