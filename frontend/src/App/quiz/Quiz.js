import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './quiz.css';
import {Link, useParams} from "react-router-dom";
import {Button} from "react-bootstrap";

const Quiz = () => {
    const { id } = useParams();
    const [activeQuestion, setActiveQuestion] = useState(0);
    const [selectedAnswer, setSelectedAnswer] = useState('');
    const [showResult, setShowResult] = useState(false);
    const [selectedAnswerIndex, setSelectedAnswerIndex] = useState(null);
    const [results, setResult] = useState({
        score: 0,
        correctAnswers: 0,
        wrongAnswers: 0,
    });

    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        loadQuiz();
    }, []);

    const loadQuiz = async () => {
        try {
            const result = await axios.get(`http://localhost:8080/quiz/getQuiz/${id}`);
            setQuestions(result.data.questions);
        } catch (error) {
            console.error('Error fetching quiz data:', error);
        }
    };

    const onClickNext = () => {
        setSelectedAnswerIndex(null);
        setResult((prev) =>
            selectedAnswer
                ? {
                    ...prev,
                    score: prev.score + 5,
                    correctAnswers: prev.correctAnswers + 1,
                }
                : { ...prev, wrongAnswers: prev.wrongAnswers + 1 }
        );

        if (activeQuestion !== questions.length - 1) {
            setActiveQuestion((prev) => prev + 1);
        } else {
            setShowResult(true);
        }
    };

    const onAnswerSelected = (answer, index) => {
        setSelectedAnswerIndex(index);
        setSelectedAnswer(answer === questions[activeQuestion].answer);
    };

    const addLeadingZero = (number) => (number > 9 ? number : `0${number}`);

    if (questions.length === 0) {
        return <div>Loading...</div>;
    }

    return (
        <div className="quiz-container">
            {!showResult ? (
                <div>
                    <div>
                        <span className="active-question-no">{addLeadingZero(activeQuestion + 1)}</span>
                        <span className="total-question">/{addLeadingZero(questions.length)}</span>
                    </div>
                    <h2>{questions[activeQuestion].question}</h2>
                    <ul>
                        {[questions[activeQuestion].var1, questions[activeQuestion].var2, questions[activeQuestion].var3, questions[activeQuestion].var4].map((answer, index) => (
                            <li
                                onClick={() => onAnswerSelected(answer.id, index)}
                                key={answer.id}
                                className={selectedAnswerIndex === index ? 'selected-answer' : null}
                            >
                                {answer.answer}
                            </li>
                        ))}
                    </ul>
                    <div className="flex-right">
                        <button onClick={onClickNext} disabled={selectedAnswerIndex === null}>
                            {activeQuestion === questions.length - 1 ? 'Finish' : 'Next'}
                        </button>
                    </div>
                </div>
            ) : (
                <div className="result">
                    <h3>Result</h3>
                    <p>
                        Total Questions: <span>{questions.length}</span>
                    </p>
                    <p>
                        Total Score: <span>{results.score}</span>
                    </p>
                    <p>
                        Correct Answers: <span>{results.correctAnswers}</span>
                    </p>
                    <p>
                        Wrong Answers: <span>{results.wrongAnswers}</span>
                    </p>

                    <Link to={`/`} >
                        <Button>Back</Button>
                    </Link>
                </div>
            )}
        </div>
    );
};

export default Quiz;
