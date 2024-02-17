import React, { useState } from "react";

import { withTranslation } from 'react-i18next';

import MenuItem from "./menu/MenuItem";
import { menuClienteConfig } from "./menu/menuCarConfig";

/**
 * Responsável por exibir e controlar o menu principal.
 */
function Mainmenu() {    
    
    const [selectedMenu, setSelectedMenu] = useState(null);

    /**
     * Responsável por realizar uma ação quando é realizado um clique em algum menu.
     */
    function onSelectedMenu(menu) { 
        // Caso o usuário clique novamente no mesmo menu, ele deverá ser fechado.
        if(menu === selectedMenu) {
            menu = null;
        }
        
        setSelectedMenu(menu);
    }
    
    return (
        <>
            <nav id="left-sidebar-nav" className="sidebar-nav">
                <ul id="main-menu" className="metismenu">
                    
                    { /** Carros */}
                    <MenuItem config={menuClienteConfig} selectedMenu={selectedMenu} onSelectedMenu={onSelectedMenu}/>

                </ul>
            </nav>
        </>
    );
}

export default withTranslation()(Mainmenu);
