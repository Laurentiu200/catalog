import React, {
    useEffect,
    useState,
} from "react";
import {
    FaEdit,
    FaTrashAlt,
} from "react-icons/fa";
import Modal from 'react-modal';
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import Search from "../common/Search";


const ViewAllCourses = () => {
    const { id } = useParams();
    const [search, setSearch] = useState("");
    const [students, setStudents] = useState([]);
    const [grade, setGrade] = useState("");
    const [isPopupOpen, setPopupOpen] = useState(false)
    const [materieName, setMaterieName] = useState("");
    const [materieId, setMaterieId] = useState("");
    const [gradeId, setGradeId] = useState("");



    useEffect(() => {
        loadStudents();
    }, []);


    const loadStudents = async () => {
        const result = await axios.get(
            `http://localhost:8080/students/getCourses/${id}`,
            {
                validateStatus: () => {
                    return true;
                },
            }
        );
        setStudents(result.data);
    };


    return (
        <div>

            <section>



                    {students
                        .map((materie, index) => (
                            <table className="table table-bordered table-hover shadow">
                                <thead>
                                <tr className="text-center">
                                    <th>Id</th>
                                    <th>Grade</th>
                                    <th>Date</th>
                                </tr>
                                </thead>

                                <tbody className="text-center">{

                                    materie.grades.map((grade, index) => (
                                        <tr key={grade.id}>
                                        <th scope="row" key={index}>
                                                {index + 1}
                                            </th>
                                            <td>{grade.grade}</td>
                                            <td>{grade.date}</td>
                                        </tr>

                                    ))
                                }
                                </tbody>
                            </table>

                        ))}
                <Link
                    to={`/view-students`}
                    className="btn btn-warning">
                    Back To Student
                </Link>
            </section>
        </div>


    );
};
export default ViewAllCourses;