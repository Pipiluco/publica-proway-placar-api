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
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Score.class.getName()));
	}

	public Score findMinRecord(Long id) {
		Score object = scoreRepository.findMinRecord(id);
		return object;
	}

	public Score findMaxRecord(Long id) {
		Score object = scoreRepository.findMaxRecord(id);
		return object;
	}

	public List<Score> findScoresByGamesId(Long id) {
		return scoreRepository.findScoresByGamesId(id);
	}

	public int findPointsByGameId(Long id) {
		try {
			int points = scoreRepository.findPointsByGameId(id);
			return points;
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Score.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
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

	public Score insert(Score score) {
		try {
			score.setId(null);
			List<Score> listAux = findAll();
			if (listAux.size() == 0) {
				score.setMinimumSeason(score.getPoints());
				score.setMaximumSeason(score.getPoints());
				score.setMinimumRecord(1);
				score.setMaximumRecord(1);
			} else {
				Score scoreAux = listAux.get(listAux.size() - 1);
				if (score.getPoints() > scoreAux.getPoints()) {
					score.setMinimumSeason(scoreAux.getMinimumSeason());
					score.setMaximumSeason(score.getPoints());
					score.setMinimumRecord(0);
					score.setMaximumRecord(1);
				} else {
					score.setMinimumSeason(score.getPoints());
					score.setMaximumSeason(scoreAux.getMaximumSeason());
					score.setMinimumRecord(1);
					score.setMaximumRecord(0);
				}
				update(scoreAux);
			}
			score = scoreRepository.save(score);
			return score;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Falha ao inserir objeto! " + e.getRootCause());
		}
	}
}

/*
 * public Score insert(Score score) { try { score.setId(null); List<Score>
 * listAux = findAll(); if (listAux.size() == 0) {
 * score.setMinimumSeason(score.getPoints());
 * score.setMaximumSeason(score.getPoints()); score.setMinimumRecord(0);
 * score.setMaximumRecord(0); } else { Score scoreAux =
 * listAux.get(listAux.size() - 1); if (score.getPoints() >
 * scoreAux.getPoints()) { score.setMinimumSeason(scoreAux.getMinimumSeason());
 * score.setMaximumSeason(score.getPoints());
 * score.setMinimumRecord(scoreAux.getMinimumRecord());
 * score.setMaximumRecord(1); } else {
 * score.setMinimumSeason(score.getPoints());
 * score.setMaximumSeason(scoreAux.getMaximumSeason());
 * score.setMinimumRecord(1);
 * score.setMaximumRecord(scoreAux.getMaximumRecord()); } } score =
 * scoreRepository.save(score); return score; } catch
 * (DataIntegrityViolationException e) { throw new
 * DataIntegrityException("Falha ao inserir objeto! " + e.getRootCause()); } }
 */
