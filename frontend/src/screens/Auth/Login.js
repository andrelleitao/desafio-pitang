import React, { useEffect, useState } from "react";
import api from "../../common/api";
import ENDPOINT from "../../common/constants/endpoint";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { resetTemporary, setAuthStorage } from '../../common/localStorage';
import { useTranslation } from 'react-i18next';
import { useNavigate } from 'react-router-dom';

function Login() {
    const { t } = useTranslation();
    const navigate = useNavigate();
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    
    /**
     * Monta o payload que será enviado para o serviço. 
     */
    function payload() {
        const obj = {
            login: login,
            password: password
        }

        return obj;
    }

    /** 
     * Responsável por validar os campos do formulário.
    */
    function validate() {
        if (!login) {            
            toast.warn(t('validation_required_login'));
            return false;
        } else if (!password) {
            toast.warn(t('validation_required_password'));
            return false;
        }

        return true;
    }

    /**
     * Responsável por redirecionar para a página principal da aplicação.
     */
    function redirectToMain() {        
        navigate('dashboard');
    }

    /**
     * Envia os dados do form para a API.
     */
    function handleSubmit(event) {
        event.preventDefault();
        
        // Responsável por validar o formulário.
        if (validate()) {            
            api.post(ENDPOINT.AUTH, payload())
                .then(response => {                    
                    // Grava o retorno da autenticação.
                    setAuthStorage(response.data);

                    // Redireciona para a página principal.
                    redirectToMain();
                })
                .catch(() => {});
        }
    }

    useEffect(() => {
         resetTemporary();
    }, []);

    return (
            <div id="wrapper" className="theme-black">
                <div className="vertical-align-wrap">
                    <div className="vertical-align-middle auth-main">
                        <div className="auth-box">
                            <div className="top">
                                <h2 style={{display:"block", "color": "#FFF"}}>Desafio Pitang</h2>
                                <small className="version" style={{display:"block", "color": "#FFF"}}>v{process.env.REACT_APP_VERSION}</small>
                            </div>
                            <div className="card">
                                <div className="header">
                                    <p className="lead">{t('msg_sign_in_your_account')}</p>
                                </div>
                                <div className="body">
                                    <form className="form-auth-small" action="index.html">
                                        <div className="form-group">
                                            <label htmlFor="signin-email" className="control-label sr-only">{t("login")}</label>
                                            <input name="login" className="form-control" id="signin-email" value={login} placeholder={t("login")}
                                                onChange={e => setLogin(e.target.value)} />
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="signin-password" className="control-label sr-only">{t('password')}</label>
                                            <input name="password" type="password" className="form-control" id="signin-password" value={password} placeholder={t('password')}
                                                onChange={e => setPassword(e.target.value)} />
                                        </div>                                        
                                        <button type="submit" className="btn btn-primary btn-lg btn-block" onClick={handleSubmit}>{t('action_login')}</button>                                        
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                
            </div>
        );
}

export default Login;