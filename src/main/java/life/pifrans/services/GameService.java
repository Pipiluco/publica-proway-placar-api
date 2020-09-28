package life.pifrans.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import life.pifrans.exceptions.errors.DataIntegrityException;
import life.pifrans.exceptions.errors.ObjectNotFoundException;
import life.pifrans.models.Game;
import life.pifrans.repositories.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepository;
	
	public Game find(Long id) {
		Optional<Game> object = gameRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Game.class.getName()));
	}

	public Game insert(Game game) {
		try {
			game.setId(null);
			game = gameRepository.save(game);
			return game;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Falha ao inserir objeto! " + e.getRootCause());
		}
	}

	public Game update(Game game) {
		find(game.getId());
		gameRepository.save(game);
		return game;
	}

	public void delete(Long id) {
		find(id);
		try {
			gameRepository.deleteById(id);
		} catch (Exception e) {
			e.getCause();
		}
	}

	public List<Game> findAll() {
		return gameRepository.findAll();
	}

}
