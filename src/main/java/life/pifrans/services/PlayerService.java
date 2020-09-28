package life.pifrans.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import life.pifrans.exceptions.errors.DataIntegrityException;
import life.pifrans.exceptions.errors.ObjectNotFoundException;
import life.pifrans.models.Player;
import life.pifrans.repositories.PlayerRepository;

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepository;
	
	public Player find(Long id) {
		Optional<Player> object = playerRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Player.class.getName()));
	}

	public Player insert(Player player) {
		try {
			player.setId(null);
			player = playerRepository.save(player);
			return player;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Falha ao inserir objeto! " + e.getRootCause());
		}
	}

	public Player update(Player player) {
		find(player.getId());
		playerRepository.save(player);
		return player;
	}

	public void delete(Long id) {
		find(id);
		try {
			playerRepository.deleteById(id);
		} catch (Exception e) {
			e.getCause();
		}
	}

	public List<Player> findAll() {
		return playerRepository.findAll();
	}
}
