package hu.ujratervezes.questionbank.exception;

import hu.ujratervezes.questionbank.dto.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String HTTP_RESPONSE = "HTTP Response: %s, %s";

    @ExceptionHandler(ConstraintViolationException.class)
    public List<ValidationError> handleConstraintViolationException(ConstraintViolationException e) {
        return List.of(new ValidationError("count", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<ValidationError> conferenceErrors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        log.info(String.format(HTTP_RESPONSE, "BAD REQUEST", conferenceErrors));
        return new ResponseEntity<>(conferenceErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ValidationError handleQuestionNotFoundException() {
        ValidationError error = new ValidationError("id", "Question not found!");
        log.info(String.format(HTTP_RESPONSE, "NOT FOUND", error));
        return error;
    }
}
