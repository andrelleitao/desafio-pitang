import React, { useState } from "react";

import { useTranslation } from 'react-i18next';
import ENDPOINT from "../../../common/constants/endpoint";
import Fieldset from "../../../Components/Form/Fieldset";
import BaseContentCreate from "../../../Components/Form/BaseContentCreate";
import { MENU_ROUTE } from "../../../Components/Common/menu/constants/menuRoute";
import { warn } from "../../../Components/Common/Message";

const Form = (props) => {
    const { t } = useTranslation();

    /**
     * Gerencia a data de adesão do plano a partir da seleção do plano.
     */
    function onChangePlano(value) {        
        if(value == null) {            
            props.setDataAdesaoPlano('');
        }
    }

    return (
        <form>
            <Fieldset text={t('car_data')} />
            <hr />
            <div className="row clearfix">
                <div className="col-2">
                    <div className="form-group">
                        <label>{t('license_plate')}*</label>
                        <input type="text" className="form-control" value={props.licensePlate} onChange={(e) => { props.setLicensePlate(e.target.value) }} maxLength={10}/>
                    </div>
                </div>
                <div className="col-2">
                    <div className="form-group">
                        <label>{t('model')}*</label>
                        <input type="text" className="form-control" value={props.model} onChange={(e) => { props.setModel(e.target.value) }} maxLength={30}/>
                    </div>
                </div>
                <div className="col-2">
                    <div className="form-group">
                        <label>{t('color')}*</label>
                        <input type="text" className="form-control" value={props.color} onChange={(e) => { props.setColor(e.target.value) }} maxLength={30}/>
                    </div>
                </div>
                <div className="col-2">
                    <div className="form-group">
                        <label>{t('year')}*</label>
                        <input type="number" className="form-control" value={props.year} onChange={(e) => { props.setYear(e.target.value) }} maxLength={30}/>
                    </div>
                </div>                
            </div>            
        </form>
    );
};

function CarNew() {
    const { t } = useTranslation();
    const [licensePlate, setLicensePlate] = useState('');
    const [model, setModel] = useState('');
    const [color, setColor] = useState('');
    const [year, setYear] = useState('');

    function payload() {
        const obj = {
            licensePlate: licensePlate,
            model: model,
            color: color,
            year: year
        }

        return obj;
    }

    function onValidate() {
        if (!licensePlate) {
            warn(t('validation_required_license_plate'));
            return false;
        } else if (!model) {
            warn(t('validation_required_model'));
            return false;
        } else if (!color) {
            warn(t('validation_required_color'));
            return false;
        } else if (!year) {
            warn(t('validation_required_year'));
            return false;
        }

        return true;
    }

    function onReset() {
        setLicensePlate('');
        setModel('');
        setColor('');
        setYear('');
    }

    function onFill(data) {
        setLicensePlate(data.licensePlate);
        setModel(data.model);
        setColor(data.color);
        setYear(data.year);
    }

    const config = {
        header: {
            icon: "fa fa-car",
            text: t('car_new'),
            mainNavigate: t('cars'),
            currentPage: t('new'),
            route: MENU_ROUTE.CAR
        },
        navigate: {
            onClickEdit: MENU_ROUTE.CAR_NEW,
            onClickCancel: MENU_ROUTE.CAR,
        },
        child:
            <Form
                licensePlate={licensePlate}
                setLicensePlate={setLicensePlate}
                model={model}
                setModel={setModel}
                color={color}
                setColor={setColor}
                year={year}
                setYear={setYear}
            />
        ,
        payload: payload,
        onValidate: onValidate,
        onReset: onReset,
        onFill: onFill,
        baseUrl: ENDPOINT.CAR
    };

    return (
        <BaseContentCreate {...config} />
    );
}

export default CarNew;