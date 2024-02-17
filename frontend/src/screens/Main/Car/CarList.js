import React, { useState } from "react";

import { useTranslation } from 'react-i18next';
import ENDPOINT from "../../../common/constants/endpoint";
import { MENU_ROUTE } from "../../../Components/Common/menu/constants/menuRoute";
import BaseContentList from "../../../Components/Form/BaseContentList";
import { DATA_TYPE } from "../../../Components/Form/Grid/constants/dataType";

function CarList() {
    const { t } = useTranslation();
  
    const [columns] = useState([
        {
            key: "id",
            label: "id",
            width: null,
            type: DATA_TYPE.NUMBER
        },
        {
            key: "licensePlate",
            label: "license_plate",
            width: null,
            type: DATA_TYPE.STRING
        },
        {
            key: "model",
            label: "model",
            width: null,
            type: DATA_TYPE.STRING
        },
        {
            key: "color",
            label: "color",
            width: null,
            type: DATA_TYPE.STRING
        },
        {
            key: "year",
            label: "year",
            width: null,
            type: DATA_TYPE.NUMBER
        }
    ]);
    
    const [filters] = useState([
        {
            field: "licensePlate",
            value: "",
            operation: "MATCH"
        }
    ]);

    const [orders] = useState([
        {
            field: "licensePlate",
            direction: "ASC"
        }
    ]);

    // Dados de configuração da tela.
    const config = {
        header: {
            icon: "fa fa-car",
            text: t('car_list'),
            mainNavigate: t('cars'),
            currentPage: t('list'),
            route: MENU_ROUTE.CAR_NEW
        },
        grid: {
            columns: columns,
            baseUrl: ENDPOINT.CAR,
            filters: filters,
            orders: orders
        },
        navigate: {
            onClickEdit: MENU_ROUTE.CAR_NEW
        },
    };

    return (
        <BaseContentList {...config} />
    );
}

export default CarList;