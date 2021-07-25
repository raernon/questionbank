package hu.ujratervezes.questionbank.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 65535)
    private String question;

    private Boolean multipleAnswers;

    @Column(length = 65535)
    private String explanation;

    private Long askedCount;

    @OneToMany(mappedBy = "question")
    List<Answer> answers = new ArrayList<>();
}
