package hu.ujratervezes.questionbank.controller;

import hu.ujratervezes.questionbank.dto.ValidationError;
import hu.ujratervezes.questionbank.dto.CheckCommand;
import hu.ujratervezes.questionbank.dto.CheckInfo;
import hu.ujratervezes.questionbank.dto.CheckListInfo;
import hu.ujratervezes.questionbank.dto.QuestionInfo;
import hu.ujratervezes.questionbank.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@Slf4j
@Tag(name = "Operations on questionbank")
public class QuestionBankController {

    private final QuestionService questionService;
    private final HttpServletRequest request;

    public QuestionBankController(QuestionService questionService, HttpServletRequest request) {
        this.questionService = questionService;
        this.request = request;
    }

    @Operation(summary = "Query a question with its possible answers")
    @ApiResponse(
            responseCode = "200",
            description = "Return a question with its possible answers.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = QuestionInfo.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Question not found if database is empty.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ValidationError.class)
            )
    )
    @GetMapping("/question")
    @ResponseStatus(HttpStatus.OK)
    public QuestionInfo getOneQuestion() {
        logRequest();

        QuestionInfo question = questionService.getOneQuestion();

        logResponse(HttpStatus.OK, question);
        return question;
    }


    @Operation(summary = "Query a list of questions and its possible answers")
    @ApiResponse(
            responseCode = "200",
            description = "Return a list of questions and its possible answers.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = QuestionInfo.class))
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Question not found if database is empty.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ValidationError.class)
            )
    )
    @GetMapping("/questionlist/{count}")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionInfo> getQuestionList(
            @Parameter(description = "Number of questions, minimum 2, maximum 50.", example = "10")
            @PathVariable("count")
            @Min(value = 2, message = "Count must be higher than 1!")
            @Max(value = 50, message = "Count must be lower than or equals to 50!")
                    Integer count
    ) {
        logRequest();

        List<QuestionInfo> questions = questionService.getQuestionList(count);

        logResponse(HttpStatus.OK, questions);
        return questions;
    }


    @Operation(summary = "Check the correctness of an answer and return the correct answer and explanation")
    @ApiResponse(
            responseCode = "200",
            description = "Return the correctness of an answer.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CheckInfo.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Question not found by the given id.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ValidationError.class)
            )
    )
    @PostMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public CheckInfo checkOneAnswer(@Valid @RequestBody CheckCommand command) {
        logRequest(command);

        CheckInfo answer = questionService.check(command);

        logResponse(HttpStatus.OK, answer);
        return answer;
    }


    @Operation(summary = "Check the correctness of a list of answers and return the correct answers and explanations")
    @ApiResponse(
            responseCode = "200",
            description = "Return the correctness of a list of answers.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CheckInfo.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Question not found by the given id."
    )
    @PostMapping("/checklist")
    @ResponseStatus(HttpStatus.OK)
    public CheckListInfo checkAnswerList(@Valid @RequestBody List<CheckCommand> command) {
        logRequest(command);

        CheckListInfo answer = questionService.checkList(command);

        logResponse(HttpStatus.OK, answer);
        return answer;
    }


    private void logRequest() {
        logRequest(null);
    }

    private void logRequest(Object body) {
        log.info(request.getMethod() + " " + request.getRequestURI() +
                (request.getQueryString() != null ? "?" + request.getQueryString() : "") +
                (body != null ? "; Body: " + body : ""));
    }

    private static void logResponse(HttpStatus status, Object body) {
        String responseFormat = "HTTP Response: %s %s";
        responseFormat += (body != null ? "; Body: %s" : "");
        log.info(String.format(responseFormat, status.value(), status.getReasonPhrase(), body));
    }
}
