package life.pifrans.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import life.pifrans.exceptions.errors.DataIntegrityException;
import life.pifrans.exceptions.errors.ObjectNotFoundException;
import life.pifrans.models.Score;
import life.pifrans.repositories.ScoreRepository;

@Service
public class ScoreService {
	@Autowired
	private ScoreRepository scoreRepository;
	
	public Score find(Long id) {
		Optional<Score> object = scoreRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Score.class.getName()));
	}

	public Score insert(Score score) {
		try {
			score.setId(null);
			score = scoreRepository.save(score);
			return score;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Falha ao inserir objeto! " + e.getRootCause());
		}
	}

	public Score update(Score score) {
		find(score.getId());
		scoreRepository.save(score);
		return score;
	}

	public void delete(Long id) {
		find(id);
		try {
			scoreRepository.deleteById(id);
		} catch (Exception e) {
			e.getCause();
		}
	}

	public List<Score> findAll() {
		return scoreRepository.findAll();
	}
}
