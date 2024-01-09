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


const AddGrade = () => {

    const queryParameters = new URLSearchParams(window.location.search);
    const id = queryParameters.get("id")
    const courseId = queryParameters.get("courseId")
    const [search, setSearch] = useState("");
    const [students, setStudents] = useState([]);
    const [grade, setGrade] = useState("");
    const [addGrade, setAddGrade] = useState("");
    const [isPopupOpen, setPopupOpen] = useState(false)
    const [isAddPopupOpen, setAddPopupOpen] = useState(false)
    const [materieName, setMaterieName] = useState("Materie");
    const [materieId, setMaterieId] = useState("");
    const [gradeId, setGradeId] = useState("");



    useEffect(() => {
        loadStudents();
    },[] );

    const handleOpenPopup = (gradeId, grade) => {
        setGrade(grade);
        setGradeId(gradeId)

        setPopupOpen(true);
    }

    const handleClosePopup = () => {
        setPopupOpen(false);
    }
    const handleAddOpenPopup = (grade) => {
        setMaterieId(materieId);
        setGradeId(gradeId)

        setAddPopupOpen(true);
    }
    const handleAddClosePopup = () => {
        setAddPopupOpen(false);
    }
    const loadStudents = async () => {
        const result = await axios.get(
            `http://localhost:8080/students/getCourse/${id}/${courseId}`,
            {
                validateStatus: () => {
                    return true;
                },
            }
        );
            setStudents(result.data);
        const result1 = await axios.get(
            `http://localhost:8080/students/getCourseName/${id}/${courseId}`,
            {
                validateStatus: () => {
                    return true;
                },
            }
        );
        setMaterieName(result1.data);

    };


    const handleDelete = async ( gradeId) => {
        await axios.patch(
            `http://localhost:8080/students/deleteGrade/${id}/${courseId}/${gradeId}`
        );
        loadStudents();
    };

    const handleEdit = async (gradeId) => {
        await axios.patch(
            `http://localhost:8080/materie/editGrade?id=${id}&&courseId=${courseId}&&gradeId=${gradeId}&&grade=${grade}`
        );
        handleClosePopup();
        loadStudents();

    };

    const handleAddGrade = async (addingGrade) => {
        await axios.patch(
            `http://localhost:8080/students/addGrade/${id}/${courseId}/${addingGrade}`
        );
        handleAddClosePopup();
        setAddGrade("");
        loadStudents();

    };

    return (
        <div>
            <section>
                <h2>{materieName.toUpperCase()}</h2>
                <table className="table table-bordered table-hover shadow">
                    <thead>
                    <tr className="text-center">
                        <th>Id</th>
                        <th>Grade</th>
                        <th>Date</th>
                        <th colSpan="2">Actions</th>
                    </tr>
                    </thead>

                    <tbody className="text-center">
                    {
                        students
                            .map((grade, index) =>
                                <tr key={grade.id}>
                                    <th scope="row" key={index}>
                                        {index + 1}
                                    </th>
                                    <td>{grade.grade}</td>
                                    <td>{grade.date}</td>
                                    <td className="mx-2">
                                        <button onClick={() => handleOpenPopup(grade.id, grade.grade)}
                                                className="btn btn-warning">
                                            <FaEdit/>
                                        </button>
                                    </td>
                                    <td className="mx-2">
                                        <button
                                            className="btn btn-danger"
                                            onClick={() =>
                                                handleDelete( grade.id)
                                            }>
                                            <FaTrashAlt/>
                                        </button>
                                    </td>
                                </tr>
                            )}
                    </tbody>

                </table>

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
                    <h2>{materieName.toUpperCase()}</h2>
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
                <div className="row text-end" style={{marginTop: '2em'}}>

                    <div className="col-sm text-center">
                        <button onClick={() => handleEdit(gradeId)} className="btn btn-success">Save</button>
                    </div>
                    <div className="col-sm text-center">
                        <button onClick={() => handleClosePopup()} className="btn btn-danger">Cancel</button>
                    </div>

                </div>
            </Modal>
            <div className="row text-end" style={{marginTop: '2em'}}>
                <div className="col-sm text-center">
                    <Link
                        to={`/courses/${courseId}`}
                        className="btn btn-warning">
                        Back
                    </Link>
                </div>
                <div className="col-sm text-center">
                    <button onClick={() => handleAddOpenPopup()} className="btn btn-success">Add Grade</button>
                </div>
            </div>
            <Modal
                isOpen={isAddPopupOpen}
                onRequestClose={handleAddClosePopup}
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
                <table className="table table-bordered table-hover shadow">
                    <thead>
                    <tr className="text-center">
                        <th>Grade</th>
                    </tr>
                    </thead>
                    <tbody className="text-center">
                    <tr>
                        <td>
                            <input value={addGrade} onChange={(i) => setAddGrade(i.target.value)} type={"number"}
                                   placeholder={""} id={"grade-add"} name={"grade"} min={1} max={10}/>
                        </td>
                    </tr>
                    </tbody>
                </table>

                {/* eslint-disable-next-line react/style-prop-object */}
                <div className="row text-end" style={{marginTop: '2em'}}>

                    <div className="col-sm text-center">
                    <button onClick={() => handleAddGrade(addGrade)} className="btn btn-success">Save</button>
                    </div>
                    <div className="col-sm text-center">
                        <button onClick={() => handleAddClosePopup()} className="btn btn-danger">Cancel</button>
                    </div>

                </div>
            </Modal>
        </div>


    );
};
export default AddGrade;