import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

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
  flex: 1 1 calc(100% - 40px);
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

const MainPage = () => {
    return (
        <DIV>
            <h2>DASHBOARD</h2>
            <Container>
                    <Box>
                        <Title>Do Quiz</Title>
                        <Link to={`/quiz`} >
                            <Button>Start</Button>
                        </Link>
                    </Box>

                <Box>
                    <Title>Learn Something</Title>
                    <Link to={`/learning`} >
                        <Button>Start</Button>
                    </Link>
                </Box>

                <Box>
                    <Title>Leader Board</Title>
                    <Link to={`/quiz`} >
                        <Button>GO</Button>
                    </Link>
                </Box>

                <Link to={`/login`} >
                    <Button>LogOut</Button>
                </Link>

            </Container>
        </DIV>
    );
};

export default MainPage;