import React, {useState} from "react";
import PropTypes from "prop-types";
import {json, useNavigate} from 'react-router-dom';
import {addStudentCourse, loginUser, registerUser} from "../api";

export const AddStudentCourse = (props) =>
{
    const [studentEmail, setEmail] = useState('')
    const [nameMaterie, setNameMaterie] = useState('')
    const [teacherEmail, setTeacherEmail] = useState('')
    const [error, setError] = useState();
    const navigate = useNavigate();

    const handleSubmit = async (e) =>
    {
        e.preventDefault()
        const formData =  {
            "studentEmail": studentEmail,
            "nameMaterie": nameMaterie,
            "teacherEmail": teacherEmail
        }

        const loginResponse = await addStudentCourse(formData)
        const json_response = await loginResponse.json()
        console.log(json_response.status)
        if(json_response.status === true)
        {
            console.log(json_response)
            console.log("Student added!")
            navigate("/view-students")
        }
        else
        {
            console.log("Register failed")
            setError(json_response['message'])
        }

    }


    return (
        <div className={"auth-form"}>
            <h2>Add Student Course</h2>
            <form className={"register-form"} onSubmit={handleSubmit} noValidate={true}>
                    <label htmlFor={"firstName"}>Student Email</label>
                    <input value={studentEmail} onChange={(e) => setEmail(e.target.value)}
                           placeholder={"First"} id={"firstName"} name={"firstName"}/>
                    <label htmlFor={"lastName"}>Course Name</label>
                    <input value={nameMaterie} onChange={(e) => setNameMaterie(e.target.value)}
                           placeholder={"Last"} id={"lastName"} name={"lastName"}/>
                    <label htmlFor={"email"}>Teacher Email</label>
                    <input value={teacherEmail} onChange={(e) => setTeacherEmail(e.target.value)} type={"email"}
                           placeholder={"something@mail.com"} id={"email"} name={"email"}/>
                    <button class="btn btn-primary" type={"submit"} onClick={handleSubmit}>Add Student</button>
                    {error ? <label color={'red'}>{error}</label> : null}
            </form>
        </div>
)
}