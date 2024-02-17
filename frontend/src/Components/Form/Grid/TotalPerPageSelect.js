import React from 'react';
import { useTranslation } from 'react-i18next';

/**
 * Componente que os totais de registros a serem listados em um grid.
 */
function TotalPerPageSelect(props) {
    const { t } = useTranslation();

    return (
        <>
            <form className="form-inline">
                <label>
                    { /* Guarda o número de registros a serem exibidos. */}
                    <select
                        {...props}>
                        <option value="10">10</option>
                        <option value="25">25</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>
                    
                    <span className='ml-2'>{t('msg_registros_por_pagina')}</span>
                </label>
            </form>
        </>
    );
}

export default TotalPerPageSelect;