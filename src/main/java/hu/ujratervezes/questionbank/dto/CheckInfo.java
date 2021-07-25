package hu.ujratervezes.questionbank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CheckInfo {

    @Schema(description = "The id of the question.", example = "1")
    private Long id;

    @Schema(description = "Whether all the answer was correct.", example = "true")
    private boolean correct;

    @Schema(description = "Explanation for the right answer(s).", example = "The 'JAVA' word consists of four letters.")
    private String explanation;

    @Schema(description = "List of all the correct answers.")
    private List<AnswerInfo> correctAnswers;
}
