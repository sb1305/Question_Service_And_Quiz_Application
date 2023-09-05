package com.example.demo.model;

import lombok.Data;

@Data
public class QuestionWrapper {
    private Integer id;
    private String The_Question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;


    public QuestionWrapper(Integer id, String questionTitle, String option1, String option2, String option3, String option4) {
        this.id = id;
        this.The_Question = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option4 = option4;
        this.option3 = option3;
    }




}