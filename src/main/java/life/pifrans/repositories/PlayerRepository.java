package life.pifrans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import life.pifrans.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
