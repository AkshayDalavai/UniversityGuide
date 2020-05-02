import React from 'react';
import Header from './components/Header/Header';
import SUForum from './components/SUForum/SUForum';
import SUSports from './components/SUSports/SUSports';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import axios from 'axios';
import {GET_CATEGORIES, LOGIN} from './constants'; 
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';

class App extends React.Component {

  state = {
    loginModal: false,
    categories: [],
    accessToken: null,
    isAuthenticated: false,
    loggedinUser: {},
    loginErrorMessage: null
  };
  
  componentDidMount(){
    //Get config values on load
    axios.get(GET_CATEGORIES)
         .then(res => {
            //Get accessToken if available
            const token = localStorage.getItem('accessToken');
            const user = JSON.parse(localStorage.getItem('loggedinUser'));
            this.setState({
              accessToken: token,
              isAuthenticated: !!token,
              loggedinUser: user,
              categories: res.data
            });
         })
         .catch(err => {
           console.log(err);
         });
  }

  toggle = () => {
    this.setState((prevState) => {
      return  {
          loginModal: !prevState.loginModal
      }
    });
  }

  login = (event, userDetails) => {
      event.preventDefault();
      const user = {
          userName: userDetails.email,
          password: userDetails.password,
      }
      /**
       * @todo: REST Call here
       * @todo: Handle invalid credentials error once REST is implemented
       */
      axios.post(LOGIN, user)
           .then(res => {
             //Store the token in local storage and also set it on state.
             const {token, user} = res.data;
             localStorage.setItem('accessToken', token);
             localStorage.setItem('loggedinUser', JSON.stringify(user));
              this.setState({
                accessToken: token,
                loggedinUser: user,
                isAuthenticated: true
              });
              this.toggle();
           })
           .catch(err => {
              alert(err);
              this.toggle();
           })
  }

  logout = () => {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('loggedinUser');
    this.setState({
      accessToken: null,
      isAuthenticated: false,
      loggedinUser: {},
    })
  }

  render(){
    return (
      <Router>
        <div className="App"> 
        {this.state.categories ? 
        <React.Fragment>
          <Header isAuthenticated={this.state.isAuthenticated} loginToggle={this.toggle} login={this.login}
                  loginErrorMessage={this.state.loginErrorMessage} showLogin={this.state.loginModal} 
                  logout={this.logout} loggedinUser={this.state.loggedinUser}/>
            <Route exact path="/">
              <SUForum categories={this.state.categories} isAuthenticated={this.state.isAuthenticated} accessToken={this.state.accessToken} loggedinUser={this.state.loggedinUser}/>
            </Route>
            <Route path="/susports">
              <SUSports />
            </Route>
          </React.Fragment> : null}
        </div>
      </Router>
    );
  }
}

export default App;
