import React, {
    useEffect,
    useState,
} from "react";
import axios from "axios";
import {
    FaEdit,
    FaEye,
    FaTrashAlt,
} from "react-icons/fa";
import { Link } from "react-router-dom";
import Search from "../common/Search";

const StudentsView = () => {
    const [students, setStudents] = useState([]);
    const [search, setSearch] = useState("");
    const [isPopupOpen, setPopupOpen] = useState(false)

    useEffect(() => {
        loadStudents();
    }, []);

    const loadStudents = async () => {
        const result = await axios.get(
            "http://localhost:8080/students",
            {
                validateStatus: () => {
                    return true;
                },
            }
        );
        if (result.status === 302) {
            setStudents(result.data);
        }
    };

    const handleDelete = async (id) => {
        await axios.delete(
            `http://localhost:8080/students/delete/${id}`
        );
        loadStudents();
    };

    const handleOpenPopup = () => {
        setPopupOpen(true);
    }

    const handleClosePopup = () => {
        setPopupOpen(false);
    }

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
                    <th>Email</th>
                    <th colSpan="3">Actions</th>
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
                            <td>{student.email}</td>
                            <td className="mx-2">
                                <Link
                                    to={`/student-profile/${student.id}`}
                                    className="btn btn-info">
                                    <FaEye />
                                </Link>
                            </td>
                            <td className="mx-2">
                                <Link
                                    to={`/courses/${student.id}`}
                                    className="btn btn-warning">
                                    <FaEdit />
                                </Link>
                            </td>
                            <td className="mx-2">
                                <button
                                    className="btn btn-danger"
                                    onClick={() =>
                                        handleDelete(student.id)
                                    }>
                                    <FaTrashAlt />
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </section>
    );
};

export default StudentsView;