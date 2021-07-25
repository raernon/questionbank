package hu.ujratervezes.questionbank.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    @Schema(description = "Field name related to the error.", example = "id")
    private String field;

    @Schema(description = "Textual representation of this error.", example = "Question not found.")
    private String message;
}
