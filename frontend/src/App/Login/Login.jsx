import React, {useState} from "react";
import PropTypes from "prop-types";
import {json, useNavigate} from 'react-router-dom';
import {loginUser} from "../api";

export const LoginComp = (props) =>
{
    const [email, setEmail] = useState('')
    const [pass, setPass] = useState('')
    const [error, setError] = useState();
    const navigate = useNavigate();

    const handleSubmit = async (e) =>
    {
        e.preventDefault()
        const formData =  {
            "email": email,
            "password": pass
        }

        const loginResponse = await loginUser(formData)
        const json_response = await loginResponse.json()
        console.log(json_response.status)
        if(json_response.status === true)
        {
            console.log(json_response)
            console.log("log in success")
            navigate("/dashboard")
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
                <button type={"submit"} onClick={handleSubmit}>Log In</button>
                {error?<label color={'red'}>{error}</label>:null }
            </form>
            <button className={"link-btn"} onClick={goNext}>Register now</button>
        </div>
    )
}