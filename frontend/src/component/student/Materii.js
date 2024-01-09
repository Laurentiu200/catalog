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


const Materii = () => {
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

    const handleOpenPopup = (materieName, materieId, gradeId, grade) => {
        setMaterieName(materieName.toUpperCase());
        setMaterieId(materieId);
        setGrade(grade);
        setGradeId(gradeId)

        setPopupOpen(true);
    }

    const handleClosePopup = () => {
        setPopupOpen(false);
    }

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

    const handleEdit = async (materieId, gradeId) => {
        await axios.patch(
            `http://localhost:8080/materie/editGrade?id=${id}&&courseId=${materieId}&&gradeId=${gradeId}&&grade=${grade}`
        );
        handleClosePopup();
        loadStudents();

    };

    return (
        <div>

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
                <th colSpan="1">Edit</th>
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
                                <tr key={materie.id}>
                                    <th scope="row" key={index}>
                                        {index + 1}
                                    </th>
                                    <td>{materie.name}</td>
                                    <td className="mx-2">
                                        <Link
                                            to={`/add-grade/?id=${id}&&courseId=${materie.id}`}
                                            className="btn btn-warning">
                                            <FaEdit />
                                        </Link>
                                        {/*<button*/}
                                        {/*    onClick={() => handleOpenPopup(materie.name, materie.id, grade.id, grade.grade)}*/}
                                        {/*    className="btn btn-warning">*/}
                                        {/*    <FaEdit/>*/}
                                        {/*</button>*/}
                                    </td>

                                </tr>
                            ))}
                </tbody>

            </table>
        <Link
            to={`/view-students`}
            className="btn btn-warning">
            Back To Student
        </Link>
        </section>
            <Modal
                isOpen={isPopupOpen}
                onRequestClose={handleClosePopup}
                style={{
                    overlay: {
                        backgroundColor: 'rgba(173, 216, 230, 0.75)',


                    },
                    content: {
                        padding: '20px',
                        top: '30%',
                        left: '30%',
                        bottom: '35%',
                        right: '30%',
                        alignItems: 'center',
                        position: 'absolute'
                    },
                }}
            >
                <div className={"text-center"}>
                    <h2>{materieName}</h2>
                </div>
                <table className="table table-bordered table-hover shadow">
                    <thead>
                    <tr className="text-center">
                        <th>Grade</th>
                    </tr>
                    </thead>
                    <tbody className="text-center">
                    <tr>
                        <td>
                            <input value={grade} onChange={(e) => setGrade(e.target.value)} type={"number"}
                                   placeholder={""} id={"grade-edit"} name={"grade"} min={1} max={10}/>
                        </td>
                    </tr>
                    </tbody>
                </table>
                {/* eslint-disable-next-line react/style-prop-object */}
                <div className="row text-end" style={{marginTop:'2em'}}>

                    <div className="col-sm text-center">
                        <button onClick={() => handleEdit(materieId, gradeId)} className="btn btn-success">Save</button>
                    </div>
                    <div className="col-sm text-center">
                        <button onClick={() => handleClosePopup()} className="btn btn-danger">Cancel</button>
                    </div>

                </div>
            </Modal>
        </div>


    );
};
export default Materii;