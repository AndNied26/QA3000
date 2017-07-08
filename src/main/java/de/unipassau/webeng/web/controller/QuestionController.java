package de.unipassau.webeng.web.controller;


import de.unipassau.webeng.application.service.MyUserDetailsService;
import de.unipassau.webeng.application.service.QuestionService;
import de.unipassau.webeng.persistence.entities.Answer;
import de.unipassau.webeng.persistence.entities.Question;
import de.unipassau.webeng.persistence.entities.User;
import de.unipassau.webeng.web.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/qa")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private MyUserDetailsService userService;


    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public List<QuestionDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @RequestMapping(value = "/loggedIn/myQuestions", method = RequestMethod.GET)
    public List<QuestionDto> getMyQuestions() {
        return questionService.getAllQuestions();
    }

    @RequestMapping(value = "/loggedIn/unansweredQuestions", method = RequestMethod.GET)
    public List<QuestionDto> getUnansweredQuestions() {
        return questionService.getUnansweredQuestions();
    }

    @RequestMapping(value = "/loggedIn/unresolvedQuestions", method = RequestMethod.GET)
    public List<QuestionDto> getUnresolvedQuestions() {
        return questionService.getUnresolvedQuestions();
    }

    @RequestMapping(value = "/loggedIn/myAnswers", method = RequestMethod.GET)
    public List<QuestionDto> getMyAnswersToQuestions(@RequestParam("username") String username) {
        return questionService.getMyAnswersToQuestions(username);
    }

    @RequestMapping(value = "/loggedIn/setQuestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setNewQuestion(@RequestBody Question question) {
        questionService.setQuestion(question);
    }

    @RequestMapping(value = "/loggedIn/deleteQuestion", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestion(Long id) {
           questionService.deleteQuestion(id);
    }


    @RequestMapping(value = "/loggedIn/setAnswer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void setNewAnswer(@RequestBody Answer answer, Long qid) {
        questionService.setNewAnswer(answer, qid);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void setQuestionAsAnswered(Long aid) {
        questionService.setQuestionAsAnswered(aid);
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteAnswer(Long id) {
        questionService.deleteAnswer(id);
    }

}
