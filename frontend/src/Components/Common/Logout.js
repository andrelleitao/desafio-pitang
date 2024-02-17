import { useNavigate } from "react-router-dom";
import LogoutIcon from "../Icon/LogoutIcon";
import LogoutIconText from '../Icon/LogoutIconText';
import { useTranslation } from "react-i18next";

function Logout({onlyIcon}) {    
    const { t } = useTranslation();
    const navigate = useNavigate();

    function turnOff() {
        navigate("/");
    }

    return (
        <>
            <span title={t('action_sair')} onClick={() => {turnOff()}}>
                {
                    onlyIcon ? <LogoutIcon/> : <LogoutIconText/>
                }
            </span> 
        </>
    );
}

export default Logout;