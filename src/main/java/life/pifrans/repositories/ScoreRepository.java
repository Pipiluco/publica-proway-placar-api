package life.pifrans.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import life.pifrans.models.Game;
import life.pifrans.models.Player;
import life.pifrans.models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

	/**
	 * Retorna um objeto {@link Score} com menor pontuação vinculado ao {@link Player}
	 */
	@Query("SELECT s FROM Score s WHERE s.points = (SELECT MIN(s.points) FROM Score s WHERE s.player.id = :id)")
	public Score findMinRecord(@Param("id") Long id);

	/**
	 * Retorna um objeto {@link Score} com maior pontuação vinculado ao {@link Player}
	 */
	@Query("SELECT s FROM Score s WHERE s.points = (SELECT MAX(s.points) FROM Score s WHERE s.player.id = :id)")
	public Score findMaxRecord(@Param("id") Long id);

	/**
	 * Retorna uma List de Score viculados ao {@link Game}
	 */
	@Query("SELECT s FROM Score s WHERE s.game.id = :id")
	public List<Score> findScoresByGamesId(@Param("id") Long id);
	
	/**
	 * Retorna um int da soma da pontuação dos {@link Score} vinculado ao {@link Game}
	 */
	@Query("SELECT SUM(s.points) FROM Score s WHERE s.game.id = :id")
	public int findPointsByGameId(@Param("id") Long id);
}
