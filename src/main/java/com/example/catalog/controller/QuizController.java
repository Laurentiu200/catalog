package com.example.catalog.controller;

import com.example.catalog.models.Quiz;
import com.example.catalog.service.QuizService;
import com.example.catalog.serviceImpl.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping(path = "/getQuiz/{id}")
    @ResponseBody
    public Quiz getQuiz(@PathVariable Integer id)
    {
        Quiz quiz = quizService.getQuiz(id);
        return quiz;
    }

}
