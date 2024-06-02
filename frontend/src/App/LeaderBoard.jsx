import React, {
    useEffect,
    useState,
} from "react";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import Search from "../component/common/Search";
import styled from "styled-components";
import {Button} from "react-bootstrap";



const LeaderBoard = () => {
    const { id } = useParams();
    const [students, setStudents] = useState([]);
    const [search, setSearch] = useState("");

    useEffect(() => {
        loadStudents();
    }, []);

    const loadStudents = async () => {
        const result = await axios.get(
            "http://localhost:8080/quiz/students"
        );

        setStudents(result.data.sort(function (a,b) {
            return b.result - a.result
        }))
    };
    console.log(students)

    return (
        <section>
            <Search
                search={search}
                setSearch={setSearch}
            />
            <table className="table table-bordered table-hover shadow">
                <thead>
                <tr className="text-center">
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Score</th>
                </tr>
                </thead>

                <tbody className="text-center">
                {students
                    .filter((st) =>
                        st.firstName
                            .toLowerCase()
                            .includes(search)
                    )
                    .map((student, index) => (
                        <tr key={student.id}>
                            <th scope="row" key={index}>
                                {index + 1}
                            </th>
                            <td>{student.firstName}</td>
                            <td>{student.lastName}</td>
                            <td>{student.result}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <Link to={`/${id}`} >
                <Button>Home Page</Button>
            </Link>
        </section>
    );
};

export default LeaderBoard;