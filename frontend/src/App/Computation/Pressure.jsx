import React, {useEffect, useState} from 'react';
import styled from "styled-components";
import {Button, InputGroup} from "react-bootstrap";
import {Link, useParams} from "react-router-dom";



export const Pressure = () => {
    const { id } = useParams();
    const [val1, setVal1] = useState();
    const [val2, setVal2] = useState();
    const [result, setResult] = useState("");

    const handleVal1Change = (e)  => {
        setVal1(Number(e.taget.value))
    }

    const handleVal2Change = (e)  => {
        setVal2(Number(e.taget.value))
    }

    const calculate = ()  => {
        setResult("RESULT: " + (val1 / val2).toFixed(2).toString() + "pa")
    }


    const Title = styled.h2`
  font-size: 1.5em;
  margin-bottom: 20px;
`;
    const DIV = styled.div `
    padding-top: 50px;
`;
    const Button = styled.button`
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;

    return (
        <DIV>
            <h2>PRESSURE</h2>
            <DIV>
                <input
                    id={"val1"}
                    type="number"
                    value={val1}
                    onChange={(e) => setVal1(e.target.value)}
                    placeholder={"Force"}/>
            </DIV>

            <DIV>
                <input
                    id={"val2"}
                    type="number"
                    value={val2}
                    onChange={(e1) => setVal2(e1.target.value)}
                    placeholder={"Aria"}/>
            </DIV>
            <DIV>
                <Title>{result}</Title>
            </DIV>
            <DIV>
                <div className="row">
                    <div className="col-sm">
                        <Button onClick={calculate}>Calculate</Button>
                    </div>
                    <div className="col-sm">
                        <Link to={`/computations/${id}`}>
                            <Button>Back</Button>
                        </Link>
                    </div>
                    </div>
            </DIV>


        </DIV>
    );

}

export default Pressure