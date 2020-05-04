import React from 'react';
import Header from './components/Header/Header';
import SUForum from './components/SUForum/SUForum';
import SUSports from './components/SUSports/SUSports';
import {Jumbotron} from 'reactstrap';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import axios from 'axios';
import {GET_CATEGORIES, LOGIN} from './constants'; 
import logo from './SUImage.jpg';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-progress-2/main.css';
import Progress from 'react-progress-2';
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

  items = {
      src: logo,
      altText: 'Welcome to University Guide',
      caption: 'Welcome to University Guide'
    }
  
  // Get accesstoken and userdetails from localstorage
  componentDidMount(){
    //Get config values on load
    Progress.show();
    axios.get(GET_CATEGORIES)
         .then(res => {
            //Get accessToken if available
            Progress.hide();
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
           Progress.hide();
           console.log(err);
         });
  }
  //All the toggle methods in the project helps in closing and opening the modals.
  toggle = () => {
    this.setState((prevState) => {
      return  {
          loginModal: !prevState.loginModal
      }
    });
  }

  //accept the user config and check if the credentials are right, if yes: store the token and user details in localstorage
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

  //Code for User logout -- Clear the accesstoken and user details 
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
        <div className="App" style={{background: '#eff2f6'}}> 
        <Progress.Component />
        {this.state.categories && this.state.categories.length > 0? 
        <React.Fragment>
          <Header isAuthenticated={this.state.isAuthenticated} loginToggle={this.toggle} login={this.login}
                  loginErrorMessage={this.state.loginErrorMessage} showLogin={this.state.loginModal} 
                  logout={this.logout} loggedinUser={this.state.loggedinUser}/>
            <Route exact path="/">
              <div id="welcomeImage"> 
                <Jumbotron style={{opacity: '0.5', width: '90%'}} >
                    <div className="d-none d-sm-block display-3">Welcome to Univer[c]ity Guide</div>
                    <div className="d-block d-sm-none pl-5" style={{fontSize: '1.2rem'}}> Welcome to Univer[c]ity Guide</div>
                </Jumbotron>
                &nbsp;
              </div>
            </Route>
            <Route exact path="/suforum">
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
