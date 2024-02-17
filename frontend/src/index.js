import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
// import { Provider } from 'react-redux'
// import { createStore,applyMiddleware } from 'redux'
// import thunk from 'redux-thunk';
// import reducers from './reducers';

import './asset/scss/main.scss';
import './asset/vendor/font-awesome/v6/css/all.css';
import './asset/css/main.css';

import './common/i18n'; // Responsável pela tradução.
import { ToastContainer } from 'react-toastify'; // Responsável pelas notificações.
import { BrowserRouter } from "react-router-dom"; // Responsável pela rota.

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
    <ToastContainer />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
