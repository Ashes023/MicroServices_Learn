package com.ashahar.quiz_service.Dao;

import com.ashahar.quiz_service.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
