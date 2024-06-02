package com.example.catalog.serviceImpl;

import com.example.catalog.models.Question;
import com.example.catalog.models.Quiz;
import com.example.catalog.models.QuizType;
import com.example.catalog.models.Student;
import com.example.catalog.repository.QuestionRepository;
import com.example.catalog.repository.QuizRepository;
import com.example.catalog.repository.UserRepository;
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

    @Autowired
    UserRepository elevRepository;


    public Quiz getQuiz(int id)
    {
        return quizRepository.findById(id)
                .orElse(new Quiz());
    }

    @Override
    public List<QuizType> getAll(Integer userId) {
        List<QuizType> quizTypes = new ArrayList<>();
        Student student = elevRepository.findById(userId).orElse(null);
        List<Quiz> quizList = quizRepository.findAll();
        for(Quiz quiz : quizList)
        {
            if(student.getQuiuri() == null)
            {
                QuizType quizType = new QuizType(quiz.getId(), quiz.getName());
                quizTypes.add(quizType);
            }
            else if(!student.getQuiuri().contains(quiz.getId())) {
                QuizType quizType = new QuizType(quiz.getId(), quiz.getName());
                quizTypes.add(quizType);
            }
        }

        return quizTypes;
    }

    public void addPoints(Integer userId, Integer points, Integer quizId)
    {
        Student student = elevRepository.findById(userId).orElse(null);

        if(student != null)
        {
            Integer pointsFinal = (student.getResult() == null ? 0 : student.getResult()) + points;
            List<Integer> quizzes = student.getQuiuri();
            quizzes.add(quizId);

            student.setResult(pointsFinal);
            student.setQuiuri(quizzes);
            elevRepository.save(student);
        }

    }

}
