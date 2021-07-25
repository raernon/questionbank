package hu.ujratervezes.questionbank.repository;

import hu.ujratervezes.questionbank.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT MIN(asked_count) FROM questions", nativeQuery = true)
    Long getMinAskedCount();

    List<Question> findQuestionByAskedCount(Long count);
}
