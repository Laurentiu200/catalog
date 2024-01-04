import React, {
    useEffect,
    useState,
} from "react";
import {
    FaEdit,
    FaEye,
    FaTrashAlt,
} from "react-icons/fa";
import axios from "axios";
import {Link, useParams} from "react-router-dom";
import Search from "../common/Search";


const AddGrade = () => {
    const { id } = useParams();
    const [search, setSearch] = useState("");
    const [students, setStudents] = useState([]);

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
    const handleDelete = async (materieId, gradeId) => {
        await axios.patch(
            `http://localhost:8080/students/deleteGrade/${id}/${materieId}/${gradeId}`
        );
        loadStudents();
    };

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
                    <th>Course</th>
                    <th>Grade</th>
                    <th colSpan="2">Actions</th>
                </tr>
                </thead>

                <tbody className="text-center">
                {students
                    .filter((st) =>
                        st.name
                            .toLowerCase()
                            .includes(search)
                    )
                    .map((materie, index) => (
                        materie.grades
                            .map((grade) =>
                                <tr key={materie.id}>
                                    <th scope="row" key={index}>
                                        {index + 1}
                                    </th>
                                    <td>{materie.name}</td>
                                    <td>{grade.grade}</td>
                                    <td className="mx-2">
                                        <Link
                                            to={`/edit-student/${materie.id}`}
                                            className="btn btn-warning">
                                            <FaEdit/>
                                        </Link>
                                    </td>
                                    <td className="mx-2">
                                        <button
                                            className="btn btn-danger"
                                            onClick={() =>
                                                handleDelete(materie.id, grade.id)
                                            }>
                                            <FaTrashAlt/>
                                        </button>
                                    </td>
                                </tr>
                            )))}
                </tbody>
            </table>
        </section>
    );
};
export default AddGrade;