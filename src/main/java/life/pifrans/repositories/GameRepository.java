package life.pifrans.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import life.pifrans.models.Game;
import life.pifrans.models.Season;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	/**
	 * Retorna um {@link List} de {@link Game} por {@link Season} id 
	 */
	@Query("SELECT g FROM Game g WHERE g.season.id = :id")
	public List<Game> findGamesBySeasonId(@Param("id") Long id);
}
