import React from 'react';
import Header from './components/Header/Header';
import SUForum from './components/SUForum/SUForum';
import SUSports from './components/SUSports/SUSports';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';

const categories = [
  {id: '1', name:'Housing'},
  {id: '2', name:'On-Campus Jobs'},
  {id: '3', name:'SU Sports'},
  {id: '4', name:'Classes and Subjects'},
  {id: '5', name:'Random'},
];
function App() {
  return (
    <Router>
      <div className="App"> 
        <Header/>
          {/* <h1>Welcome to University Guide</h1> Add a welcome image with login page*/}
        <Route exact path="/">
          <SUForum categories={categories} />
        </Route>
        <Route path="/SUSports">
          <SUSports />
        </Route>
      </div>
    </Router>
  );
}

export default App;
