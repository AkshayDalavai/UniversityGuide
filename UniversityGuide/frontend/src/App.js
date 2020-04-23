import React from 'react';
import Header from './components/Header/Header';
import SUForum from './components/SUForum/SUForum';
import SUSports from './components/SUSports/SUSports';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import axios from 'axios';
import {GET_CATEGORIES} from './constants'; 
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';

class App extends React.Component {

  state = {
    categories: []
  };
  componentDidMount(){
    axios.get(GET_CATEGORIES)
         .then(res => {
            console.log(res.data)
            this.setState({
              categories: res.data
            });
         })
         .catch(err => {
           console.log(err);
         });
  }
  render(){
    return (
      <Router>
        <div className="App"> 
        {this.state.categories && this.state.categories.length > 0 ? 
        <React.Fragment>
          <Header/>
            <Route exact path="/">
              <SUForum categories={this.state.categories} />
            </Route>
            <Route path="/SUSports">
              <SUSports />
            </Route>
          </React.Fragment> : null}
    
        </div>
      </Router>
    );
  }
}

export default App;
