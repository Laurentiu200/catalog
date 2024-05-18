package com.example.catalog.serviceImpl;

import com.example.catalog.models.Question;
import com.example.catalog.models.Quiz;
import com.example.catalog.repository.QuestionRepository;
import com.example.catalog.repository.QuizRepository;
import com.example.catalog.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
