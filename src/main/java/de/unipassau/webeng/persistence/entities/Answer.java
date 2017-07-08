package de.unipassau.webeng.persistence.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Answer {

    public Answer() {
    }

    public Answer(String text, User answerer, Question question) {
        this.text = text;
        this.date = new Date();
        this.answerer = answerer;
        this.question = question;
        this.accepted = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;
    private Date date;


    @ManyToOne
    private User answerer;

    @ManyToOne
    private Question question;


    private boolean accepted;


    public Answer(String text, long id, User answerer) {
        this.text = text;
        this.date = new Date();
        this.id = id;

        this.answerer = answerer;
        this.accepted = false;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public User getAnswerer() {
        return answerer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setQuestion(Question question) {
        this.question = question;
    }
}

