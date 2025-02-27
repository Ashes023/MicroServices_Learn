package com.ashahar.quiz_service.Model;

import lombok.Data;

@Data
public class QuizDto {
    private String categoryName;
    private Integer NumQuestions;
    private String title;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getNumQuestions() {
        return NumQuestions;
    }

    public void setNumQuestions(Integer numQuestions) {
        NumQuestions = numQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
