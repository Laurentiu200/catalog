import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import {Link, useParams} from 'react-router-dom';
import axios from 'axios';

const Container = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 20px;
  gap: 20px;
`;

const Box = styled.div`
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 20px;
  text-align: center;
  flex: 1 1 calc(50% - 40px);
  box-sizing: border-box;

  @media (max-width: 1200px) {
    flex: 1 1 calc(33.333% - 40px);
  }

  @media (max-width: 900px) {
    flex: 1 1 calc(50% - 40px);
  }

  @media (max-width: 600px) {
    flex: 1 1 100%;
  }
`;

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



const ResponsiveBoxes = () => {
    const { id } = useParams();
    const [listQuiz, setQuiz] = useState([]);

    useEffect(() => {
        loadQuiz();
    }, []);

    const loadQuiz = async () => {
        try {
            console.log(id)
            const result = await axios.get(`http://localhost:8080/quiz/getQuizAll/${id.substring(3)}`);
            setQuiz(result.data);
        } catch (error) {
            console.error('Error fetching quiz data:', error);
        }
    };

    return (
<DIV>
    <h2>CHOOSE ONE QUIZ</h2>
        <Container>
            {listQuiz.map((quiz) => (
                <Box key={quiz.id}>
                    <Title>{quiz.name}</Title>
                    <Link to={`/quiz/${quiz.id}/${id}`} >
                        <Button>Start Quiz</Button>
                    </Link>
                </Box>
            ))}
        </Container>
    <DIV>
        <Link to={`/${id}`} >
            <Button>Home Page</Button>
        </Link>
    </DIV>
</DIV>
    );
};

export default ResponsiveBoxes;