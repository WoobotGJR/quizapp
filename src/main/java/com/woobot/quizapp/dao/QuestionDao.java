package com.woobot.quizapp.dao;

import com.woobot.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {// type of table/type of primary key
    List<Question> findByCategory(String category);

    @Query(value = "select * from question q where q.category=:category order by random() limit :questionsNum", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int questionsNum);
}
