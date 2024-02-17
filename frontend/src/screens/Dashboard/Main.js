import React from "react";
import Navbar from "../../Components/Common/Navbar";
import Leftbar from "../../Components/Common/Leftbar";

import { Outlet } from 'react-router-dom';

class Main extends React.Component {
  
  render() {
    const { navigation } = this.props;

    return (
      <div id="wrapper" className="theme-purple">
        <Navbar />        
        <Leftbar navigation={navigation} />
        <div id="main-content">          
          <Outlet />
        </div>
      </div>
    );
  }
}

export default Main;