package hu.ujratervezes.questionbank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerInfo {

    @Schema(description = "Id of this answer", example = "1")
    private Long id;

    @Schema(description = "The text of this answer", example = "Four.")
    private String answer;
}
