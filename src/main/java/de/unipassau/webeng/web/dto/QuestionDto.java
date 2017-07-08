package de.unipassau.webeng.web.dto;





import de.unipassau.webeng.persistence.entities.Answer;
import de.unipassau.webeng.persistence.entities.User;

import java.util.Date;
import java.util.List;

public class QuestionDto {
    private Long id;
    private String title;
    private String text;
    private Date date;
    private List<Answer> answers;
    private User questioner;
    private boolean answered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswers(Answer a) {
        this.answers.add(a);
    }

    public User getQuestioner() {
        return questioner;
    }

    public void setQuestioner(User questioner) {
        this.questioner = questioner;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
