import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import './App/App.css';
import {useState} from "react";
import {BrowserRouter, Redirect, Route, Router, Routes} from 'react-router-dom';
import {LoginComp} from "./App/Login/Login";
import {RegisterComp} from "./App/Register/Register";
import {AddStudentCourse} from "./App/Register/AddStudentCourse";
import {HomePage} from "./App/HomePage/HomePage";
import StudentsView from "./component/student/StudentsView";
import AddGrade from "./component/student/AddGrade";
import AddStudent from "./component/student/AddStudent";
import EditStudent from "./component/student/EditStudent";
import StudentPofile from "./component/student/StudentProfile";
import Home from "./Home";
import Materii from "./component/student/Materii";
import ViewAllCourses from "./component/student/ViewAllCourses";

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
          <Route
              exact
              path="/"
              element={<Home />}></Route>
          <Route
              exact
              path="/view-students"
              element={<StudentsView />}></Route>
          <Route
              exact
              path="/add-students"
              element={<AddStudent />}></Route>
          <Route
              exact
              path="/student-grades/:id"
              element={<ViewAllCourses />}></Route>
          <Route
              exact
              path="/add-student/course"
              element={<AddStudentCourse/>}></Route>
          <Route
              exact
              path="/add-grade"
              element={<AddGrade />}></Route>
          <Route
              exact
              path="/courses/:id"
              element={<Materii />}></Route>
          <Route
              exact
              path="/edit-student/:id"
              element={<EditStudent />}></Route>
          <Route
              exact
              path="/student-profile/:id"
              element={<StudentPofile />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
