import React, {useState} from "react";
import PropTypes from "prop-types";
import {json, useNavigate} from 'react-router-dom';
import {loginUser, registerUser} from "../api";

export const RegisterComp = (props) =>
{
    const [email, setEmail] = useState('')
    const [pass, setPass] = useState('')
    const [fName, setFName] = useState('')
    const [lName, setLName] = useState('')
    const [error, setError] = useState();
    const navigate = useNavigate();

    const handleSubmit = async (e) =>
    {
        e.preventDefault()
        const formData =  {
            "firstName": fName,
            "lastName": lName,
            "email": email,
            "password": pass
        }

        const loginResponse = await registerUser(formData)
        const json_response = await loginResponse.json()
        console.log(json_response.status)
        if(json_response.status === true)
        {
            console.log(json_response)
            console.log("Register success!")
            navigate("/dashboard")
        }
        else
        {
            console.log("Register failed")
            setError(json_response['message'])
        }

    }


    return (
        <div className={"auth-form"}>
            <h2>Register</h2>
            <form className={"register-form"} onSubmit={handleSubmit} noValidate={true}>
                <label htmlFor={"firstName"}>First Name</label>
                <input value={fName} onChange={(e) => setFName(e.target.value)}
                       placeholder={"First"} id={"firstName"} name={"firstName"}/>
                <label htmlFor={"lastName"}>Last Name</label>
                <input value={lName} onChange={(e) => setLName(e.target.value)}
                       placeholder={"Last"} id={"lastName"} name={"lastName"}/>
                <label htmlFor={"email"}>Email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type={"email"}
                       placeholder={"something@mail.com"} id={"email"} name={"email"}/>
                <label htmlFor={"password"}>Password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type={"password"}
                       placeholder={"*******"} id={"password"} name={"password"}/>
                <button class="btn btn-primary" type={"submit"} onClick={handleSubmit}>Register</button>
                {error ? <label color={'red'}>{error}</label> : null}
            </form>
        </div>
    )
}