package life.pifrans.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import life.pifrans.models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

	@Query("SELECT s FROM Score s WHERE s.points = (SELECT MIN(s.points) FROM Score s WHERE s.player.id = :id)")
	public Score findMinRecord(@Param("id") Long id);

	@Query("SELECT s FROM Score s WHERE s.points = (SELECT MAX(s.points) FROM Score s WHERE s.player.id = :id)")
	public Score findMaxRecord(@Param("id") Long id);

	@Query("SELECT s FROM Score s WHERE s.game.id = :id")
	public List<Score> findScoresByGamesId(@Param("id") Long id);
}
