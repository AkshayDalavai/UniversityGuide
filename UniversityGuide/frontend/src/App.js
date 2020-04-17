import React from 'react';
import Header from './components/Header/Header';
import SUForum from './components/SUForum/SUForum';

import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';


function App() {
  return (
    <div className="App">
      <Header/>
        {/* <h1>Welcome to University Guide</h1> Add a welcome image with login page*/}
      <SUForum />
    </div>
  );
}

export default App;
