import React from "react";
import Mainmenu from './Mainmenu';

function Leftbar(props) {
    const { navigation } = props;

    return (
        <div className="sidebar">
            <div className="sidebar-scroll">
                <div className="user-account">

                </div>
                <Mainmenu navigation={navigation} />
            </div>
        </div>
    );
}
export default Leftbar;
