package hu.ujratervezes.questionbank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class CheckCommand {

    @Schema(description = "The id of the question.", example = "1")
    private Long questionId;

    @Schema(description = "List of all the ids of the answers, which marked as correct.", example = "[1, 2]")
    @Size(min = 1, message = "At least one answer has to be marked to be correct.")
    private List<Long> selectedAnswerIds;
}
