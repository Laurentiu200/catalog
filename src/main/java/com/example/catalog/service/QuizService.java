package com.example.catalog.service;

import com.example.catalog.models.Quiz;
import com.example.catalog.models.QuizType;
import com.example.catalog.serviceImpl.QuizServiceImpl;

import java.util.List;

public interface QuizService {

     Quiz getQuiz(int id);

     List<QuizType> getAll(Integer userId);

     void addPoints(Integer userId, Integer points, Integer quizId);
}
