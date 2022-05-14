package com.example.quizapp;

import java.io.Serializable;

public class QuestionModel implements Serializable {
    private String[] options;
    private int correctOption;
    private int userSelectedOption;
    private String question;
    private boolean isBookmarked;

    public QuestionModel(String[] options, int correctOption, String question, boolean isBookmarked) {
        this.options = options;
        this.correctOption = correctOption;
        this.userSelectedOption = -1;
        this.question = question;
        this.isBookmarked = isBookmarked;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public int getUserSelectedOption() {
        return userSelectedOption;
    }

    public void setUserSelectedOption(int userSelectedOption) {
        this.userSelectedOption = userSelectedOption;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}
