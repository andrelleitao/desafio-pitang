import { t } from "i18next";
import React from "react";
import { NavLink } from "react-router-dom";

function Header(props) {
    const { icon, mainNavigate, currentPage, headerText, childNav, showGrid, showAdd, gridRoute, addRoute } = props;
    
    return (
        <div className="block-header">
            <div className="row">
                <div className="col-lg-6 col-md-6 col-sm-12">
                    <h3 className="pb-2 font-weight-bold">{headerText}</h3>
                    <ul className="breadcrumb">
                        <li className="breadcrumb-item"><a href="/" onClick={(e) => {
                             e.preventDefault();
                        }}><i className={icon}></i></a></li>
                        <li className="breadcrumb-item">{mainNavigate}</li>
                        <li className="breadcrumb-item">{currentPage}</li>
                        {
                            childNav ? <li className="breadcrumb-item active">{childNav}</li> : null
                        }
                    </ul>
                </div>
                <div className="col-lg-6 col-md-6 col-sm-12">
                    <div className="d-flex flex-row-reverse">
                        <div>                            
                            {
                                /* Exibe o botão para linkar com a lista de registros quando for 'true'. */
                                showGrid ? 
                                    <NavLink className="btn btn-primary" to={gridRoute}>
                                        <i className="fa-solid fa-grid"></i> {t('action_exibir_lista_registros')}
                                    </NavLink> 
                                : null
                            }
                            {
                                /** Exibe o botão de adicionar quando o valor for 'true'. */
                                showAdd ? 
                                    <NavLink className="btn btn-primary" to={addRoute}>
                                        <i className="fa-solid fa-plus"></i> {t('action_criar')}
                                    </NavLink> 
                                : null
                            }

                        </div>
                        <div className="p-2 d-flex"></div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Header;
