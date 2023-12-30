
import './App.css';
import {useState} from "react";
import {BrowserRouter, Redirect, Route, Router, Routes} from 'react-router-dom';
import {LoginComp} from ".//Login/Login";
import {RegisterComp} from ".//Register/Register";
import {HomePage} from "./HomePage/HomePage";

function App() {
  const[currentForm, setCurrentForm] = useState('login');
  const toggleForm = (formName) => {
    setCurrentForm(formName)
  }
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='/login' element={<LoginComp/>} />
          <Route path='/register' element={<RegisterComp/>} />
          <Route path='/' element={<HomePage/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
