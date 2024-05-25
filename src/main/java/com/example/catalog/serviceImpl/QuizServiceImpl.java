package com.example.catalog.serviceImpl;

import com.example.catalog.models.Question;
import com.example.catalog.models.Quiz;
import com.example.catalog.models.QuizType;
import com.example.catalog.repository.QuestionRepository;
import com.example.catalog.repository.QuizRepository;
import com.example.catalog.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;


    public Quiz getQuiz(int id)
    {
        return quizRepository.findById(id)
                .orElse(new Quiz());
    }

    @Override
    public List<QuizType> getAll() {
        List<QuizType> quizTypes = new ArrayList<>();
        List<Quiz> quizList = quizRepository.findAll();
        for(Quiz quiz : quizList)
        {
            QuizType quizType = new QuizType(quiz.getId(), quiz.getName());
            quizTypes.add(quizType);
        }

        return quizTypes;
    }

}
