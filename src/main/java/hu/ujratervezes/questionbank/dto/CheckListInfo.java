package hu.ujratervezes.questionbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckListInfo {
    private Long answerCount;

    private Long correctCount;

    private Long percent;

    private List<CheckInfo> answers;
}
