package com.woobot.quizapp.service;

import com.woobot.quizapp.dao.QuestionDao;
import com.woobot.quizapp.dao.QuizDao;
import com.woobot.quizapp.model.Question;
import com.woobot.quizapp.model.QuestionWrapper;
import com.woobot.quizapp.model.Quiz;
import com.woobot.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;


    public Quiz createQuiz(String category, int questionsNum, String title) {
        List<Question> questionList = questionDao.findRandomQuestionsByCategory(category, questionsNum);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);

        return quizDao.save(quiz);
    }

    public List<QuestionWrapper> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);

        List<Question> questionsFromDB = quiz.get().getQuestions();

        List<QuestionWrapper> questionForUsers = new ArrayList<>();

        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getCategory(), q.getTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUsers.add(qw);
        }

        return questionForUsers;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        int i = 0;
        int rightAns = 0;

        for (Response res : responses) {
            if (res.getResponse().equals(questions.get(i).getRightAnswer()))
                rightAns++;

            i++;
        }


        return ResponseEntity.ok(rightAns);
    }
}
