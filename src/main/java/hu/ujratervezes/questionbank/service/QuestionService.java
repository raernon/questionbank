package hu.ujratervezes.questionbank.service;

import hu.ujratervezes.questionbank.domain.Answer;
import hu.ujratervezes.questionbank.domain.Question;
import hu.ujratervezes.questionbank.dto.CheckCommand;
import hu.ujratervezes.questionbank.dto.CheckInfo;
import hu.ujratervezes.questionbank.dto.CheckListInfo;
import hu.ujratervezes.questionbank.dto.AnswerInfo;
import hu.ujratervezes.questionbank.dto.QuestionInfo;
import hu.ujratervezes.questionbank.exception.QuestionNotFoundException;
import hu.ujratervezes.questionbank.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    private final Random random;

    public QuestionService(QuestionRepository questionRepository, ModelMapper modelMapper, Random random) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
        this.random = random;
    }

    public QuestionInfo getOneQuestion() {
        return mapToQuestionInfo(selectOneQuestion());
    }

    public List<QuestionInfo> getQuestionList(Integer count) {
        List<QuestionInfo> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(getOneQuestion());
        }
        return result;
    }

    public CheckInfo check(CheckCommand command) {
        Question question = questionRepository
                .findById(command.getQuestionId())
                .orElseThrow(QuestionNotFoundException::new);

        CheckInfo info = modelMapper.map(question, CheckInfo.class);
        info.setCorrectAnswers(
                question.getAnswers().stream()
                .filter(Answer::isGood)
                .map(answer -> modelMapper.map(answer, AnswerInfo.class))
                .collect(Collectors.toList())
        );

        List<Long> correctIds = info.getCorrectAnswers().stream()
                .map(AnswerInfo::getId)
                .collect(Collectors.toList());

        info.setCorrect(
            correctIds.containsAll(command.getSelectedAnswerIds()) &&
                    command.getSelectedAnswerIds().containsAll(correctIds)
        );

        return info;
    }

    public CheckListInfo checkList(List<CheckCommand> command) {
        List<CheckInfo> answers = command.stream().map(this::check).collect(Collectors.toList());
        long correctAnswers = answers.stream().filter(CheckInfo::isCorrect).count();
        return new CheckListInfo(
                (long) answers.size(),
                correctAnswers,
                (correctAnswers * 100) / answers.size(),
                answers
        );
    }


    private QuestionInfo mapToQuestionInfo(Question question) {
        QuestionInfo info = modelMapper.map(question, QuestionInfo.class);
        info.setAnswers(
                question.getAnswers().stream()
                        .map(answer -> modelMapper.map(answer, AnswerInfo.class))
                        .collect(Collectors.toList())
        );
        Collections.shuffle(info.getAnswers());
        return info;
    }

    private Question selectOneQuestion() {
        Long minAskedCount = questionRepository.getMinAskedCount();

        if (minAskedCount == null) {
            throw new QuestionNotFoundException();
        }

        List<Question> questions = questionRepository.findQuestionByAskedCount(minAskedCount);

        Question selected = questions.get(random.nextInt(questions.size()));

        selected.setAskedCount(selected.getAskedCount() + 1);
        questionRepository.save(selected);

        return selected;
    }
}