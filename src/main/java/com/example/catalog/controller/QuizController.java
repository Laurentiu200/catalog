package com.example.catalog.controller;

import com.example.catalog.models.Quiz;
import com.example.catalog.models.QuizType;
import com.example.catalog.models.Student;
import com.example.catalog.repository.UserRepository;
import com.example.catalog.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/getQuiz/{id}")
    @ResponseBody
    public Quiz getQuiz(@PathVariable Integer id)
    {
        Quiz quiz = quizService.getQuiz(id);
        return quiz;
    }

    @PostMapping(path = "/addPoints/{userId}/{points}/{quizId}")
    public void addPoints(@PathVariable Integer userId, @PathVariable Integer points, @PathVariable Integer quizId)
    {
        quizService.addPoints(userId, points, quizId);
    }


    @GetMapping (path = "/getQuizAll/{userId}")
    @ResponseBody
    public List<QuizType> getQuizAll(@PathVariable Integer userId)
    {
        return quizService.getAll(userId);
    }

    @GetMapping(path = "/students")
    @ResponseBody
    public List<Student> gettAllStundets()
    {
        return userRepository.findAll();
    }


}
