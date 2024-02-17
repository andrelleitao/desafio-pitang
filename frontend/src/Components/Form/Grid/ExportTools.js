import React from 'react';
import { useTranslation } from 'react-i18next';

function ExportTools(props) {
    const { t } = useTranslation();

    return (
        <>
            {
                (false) ?
                    <ul className="header-dropdown">
                        <li><a href="#" title={t('action_send_email')}><i className="fa fa-envelope"></i></a></li>
                        <li><a href="#" title={t('action_export')}><i className="fa fa-download"></i></a></li>
                    </ul>
                    : <span></span>
            }
        </>
    );
}

export default ExportTools;