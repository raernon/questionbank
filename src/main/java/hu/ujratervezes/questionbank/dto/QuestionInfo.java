package hu.ujratervezes.questionbank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class QuestionInfo {

    @Schema(description = "Id of this question", example = "1")
    private Long id;

    @Schema(description = "Text of this question", example = "How many letters are in word 'JAVA'?")
    private String question;

    @Schema(description = "Whether this question has multiple right answers", example = "false")
    private Boolean multipleAnswers;

    @Schema(description = "List of all answers related to this question")
    List<AnswerInfo> answers = new ArrayList<>();
}