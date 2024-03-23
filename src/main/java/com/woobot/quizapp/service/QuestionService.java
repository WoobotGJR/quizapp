package com.woobot.quizapp.service;

import com.woobot.quizapp.dao.QuestionDao;
import com.woobot.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public Question createQuestion(Question data) {
        return questionDao.save(data);
    }
}
