package de.unipassau.webeng.application.service;



import de.unipassau.webeng.persistence.entities.Answer;
import de.unipassau.webeng.persistence.entities.Question;
import de.unipassau.webeng.persistence.repository.AnswerRepository;
import de.unipassau.webeng.persistence.repository.QuestionRepository;
import de.unipassau.webeng.web.dto.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository; this.answerRepository = answerRepository;
    }

    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = new LinkedList<>();
        questionRepository.findAll().forEach(questions :: add);

        List<QuestionDto> allQuestions = new LinkedList<>();
        for (Question question: questions) {
            allQuestions.add(convertToDto(question));
        }
        return allQuestions;
    }

    public List<QuestionDto> getUnansweredQuestions() {
        List<Question> questions = new LinkedList<>();
        questionRepository.findAll().forEach(questions :: add);

        List<QuestionDto> unansweredQuestions = new LinkedList<>();
        for (Question question: questions) {
            if(!question.isAnswered()) {
                unansweredQuestions.add(convertToDto(question));
            }
        }
        return unansweredQuestions;
    }

    public List<QuestionDto> getUnresolvedQuestions() {
        List<Question> questions = new LinkedList<>();
        questionRepository.findAll().forEach(questions :: add);

        List<QuestionDto> unresolvedQuestions = new LinkedList<>();
        for (Question question: questions) {
            boolean accepted = false;
            List<Answer> answers = question.getAnswers();
            for(Answer answer: answers) {
                if(answer.isAccepted()){
                    accepted = true;
                }
            }
            if(!accepted){
                unresolvedQuestions.add(convertToDto(question));
            }
        }
        return unresolvedQuestions;
    }

    public List<QuestionDto> getMyAnswersToQuestions(String username) {
        List<Answer> answers = answerRepository.getMyAnswersToQuestions(username);

        List<Question> questions = new LinkedList<>();
        for(Answer answer: answers) {
            questions.add(answer.getQuestion());
        }

        List<QuestionDto> myAnswersToQuestions = new LinkedList<>();
        for (Question question: questions) {
                myAnswersToQuestions.add(convertToDto(question));
        }
        return myAnswersToQuestions;
    }

    public void setQuestion(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestion(Long id){
        questionRepository.delete(id);
    }

    public void setNewAnswer(Answer answer, Long qid){
        answerRepository.save(answer);
        Question question = questionRepository.findOne(qid);
        question.addAnswer(answer);
        questionRepository.save(question);
    }

    //Set the given answer as accepted and the question as answered
    public void setQuestionAsAnswered(Long aid) {
        Answer answer = answerRepository.findOne(aid);
        answer.setAccepted(true);
        Question question = answer.getQuestion();
        question.setAnswered(true);
        answerRepository.save(answer);
        questionRepository.save(question);
    }

    public void deleteAnswer(Long aid) {
        answerRepository.delete(aid);
    }

    private QuestionDto convertToDto(Question q){
        QuestionDto dto = new QuestionDto();
        dto.setId(q.getId());
        dto.setTitle(q.getTitle());
        dto.setText(q.getText());
        dto.setDate(q.getDate());
        dto.setAnswers(q.getAnswers());
        dto.setQuestioner(q.getQuestioner());
        dto.setAnswered(q.isAnswered());
        return dto;
    }
}
