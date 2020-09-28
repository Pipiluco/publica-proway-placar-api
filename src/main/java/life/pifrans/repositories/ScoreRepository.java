package life.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import life.pifrans.models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

}
