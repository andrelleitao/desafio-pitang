import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router-dom";

/**
 * Componente responsável por exibir um item no menu principal.
 */
function MenuItem(props) {
    const { t } = useTranslation();
    const navigate = useNavigate();

    return (
        <>
            <li className={props.selectedMenu === props.config.id ? `active` : ''}>
                <a href="/" className="main-menu has-arrow" onClick={(e) => {
                    // Impede que o evento padrão ocorra.                        
                    e.preventDefault();
                    // Responsável por controlar o clique nos menus e submenus.
                    props.onSelectedMenu(props.config.id);
                }}
                >
                    <i className={props.config.icon}></i><span>{t(props.config.trans)}</span>
                </a>
                <ul className={props.selectedMenu === props.config.id ? `collapse in` : 'collapse'}>
                    {
                        // Lista todos os menus baseando-se na configuração.
                        props.config.items.map((i, index) =>
                            <li key={index}>
                                <a href="/" onClick={(e) => {
                                    e.preventDefault();
                                    navigate(i.navigate);
                                }}>{t(i.trans)}</a>
                            </li>
                        )
                    }
                </ul>
            </li>
        </>
    );
}

export default MenuItem;