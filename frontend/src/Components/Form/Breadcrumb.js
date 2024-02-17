import React from 'react';
import { useTranslation } from 'react-i18next';

function Breadcrumb() {
    const { t } = useTranslation();

    return (
        <>
        {
            (false) ?
            <ul className="breadcrumb">
                <li className="breadcrumb-item">{t('dashboard')}</li>
                <li className="breadcrumb-item">{t('menu.customer.main')}</li>
                <li className="breadcrumb-item active">{t('menu.customer.title_registration')}</li>
            </ul>
            : <span></span>
        }
        </>
    );
}

export default Breadcrumb;