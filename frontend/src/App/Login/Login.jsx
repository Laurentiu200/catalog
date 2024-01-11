import React, {useState} from "react";
import PropTypes from "prop-types";
import {json, useNavigate} from 'react-router-dom';
import {loginUser} from "../api";
import Select from 'react-select'

export const LoginComp = (props) =>
{
    const [email, setEmail] = useState('')
    const [pass, setPass] = useState('')

    const [error, setError] = useState();
    const navigate = useNavigate();

    const getInitialState = () => {
        const value = "0";
        return value;
    };

    const [value, setValue] = useState(getInitialState);

    const handleChange = (e) => {
        setValue(e.target.value);
    };


    const groupStyles = {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
    };




    const handleSubmit = async (e) =>
    {
        e.preventDefault()
        const formData =  {
            "email": email,
            "password": pass,
            "role": value
        }

        const loginResponse = await loginUser(formData)
        const json_response = await loginResponse.json()
        console.log(json_response.status)
        if(json_response.status === "Success" && value === "1")
        {
            console.log(json_response)
            console.log("log in success")
            navigate("/view-students")
        }
        else if(json_response.status === "Success" && value === "0")
        {
            console.log(json_response)
            console.log("log in success")
            navigate("/student-grades/1")
        }
        else
        {
            console.log("log in failed")
            setError(json_response['message'])
        }

    }

    const goNext = (e) => {
        navigate("/register")
    }


    return (
        <div className={"auth-form"}>
            <h2>Login</h2>
            <form className={"login-form"} onSubmit={handleSubmit} noValidate={true}>
                <label htmlFor={"email"}>Email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type={"email"}
                       placeholder={"something@mail.com"} id={"email"} name={"email"}/>
                <label htmlFor={"password"}>Password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type={"password"}
                       placeholder={"*******"} id={"password"} name={"password"}/>
                <div>
                    <select value={value} onChange={handleChange}>
                        <option value="0">Student</option>
                        <option value="1">Teacher</option>
                    </select>
                </div>
                {error ? <label color={'red'}>{error}</label> : null}
                <div className="row">
                    <div className="pull-left">
                        <button class="btn btn-primary" type={"submit"} onClick={handleSubmit}>Log In</button>
                    </div>

                    <div className="pull-right">
                        <button class="btn btn-primary" className={"link-btn"} onClick={goNext}>Register now</button>
                    </div>
                </div>
            </form>
        </div>
    )
}