package life.pifrans.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import life.pifrans.exceptions.errors.DataIntegrityException;
import life.pifrans.exceptions.errors.ObjectNotFoundException;
import life.pifrans.models.Season;
import life.pifrans.repositories.SeasonRepository;

@Service
public class SeasonService {
	@Autowired
	private SeasonRepository seasonRepository;
	
	public Season find(Long id) {
		Optional<Season> object = seasonRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Season.class.getName()));
	}

	public Season insert(Season season) {
		try {
			season.setId(null);
			season = seasonRepository.save(season);
			return season;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Falha ao inserir objeto! " + e.getRootCause());
		}
	}

	public Season update(Season season) {
		find(season.getId());
		seasonRepository.save(season);
		return season;
	}

	public void delete(Long id) {
		find(id);
		try {
			seasonRepository.deleteById(id);
		} catch (Exception e) {
			e.getCause();
		}
	}

	public List<Season> findAll() {
		return seasonRepository.findAll();
	}
}
