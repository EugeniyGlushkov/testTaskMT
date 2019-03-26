import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import './user.css';
import Users from './components/users';
import Create from './components/create';
import Update from './components/update';
import * as serviceWorker from './serviceWorker';
ReactDOM.render(
    <Router>
        <div>
            <Route exact path='/api/users' component={Users} />
            <Route path='/api/user/add' component={Create} />
            <Route path='/api/user/update/:id' component={Update} />
        </div>
    </Router>,
    document.getElementById('root'));

serviceWorker.register();
