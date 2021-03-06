package life.pifrans.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import life.pifrans.models.Score;
import life.pifrans.services.ScoreService;

/**
 * Classe com endpoints referente a {@link Score}
 */
@RestController
@RequestMapping(value = "/scores")
public class ScoreResource {
	@Autowired
	private ScoreService scoreService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Score> find(@PathVariable Long id) {
		Score score = scoreService.find(id);
		return ResponseEntity.ok().body(score);
	}
	
	@RequestMapping(value = "game/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Score>> findScoresByGamesId(@PathVariable Long id) {
		List<Score> scores = scoreService.findScoresByGamesId(id);
		return ResponseEntity.ok().body(scores);
	}
	
	@RequestMapping(value = "game/{id}/points", method = RequestMethod.GET)
	public ResponseEntity<Integer> findPointsByGameId(@PathVariable Long id) {
		int points = scoreService.findPointsByGameId(id);
		return ResponseEntity.ok().body(points);
	}
	
	@RequestMapping(value = "min-record-player/{id}", method = RequestMethod.GET)
	public ResponseEntity<Score> findMinRecord(@PathVariable Long id) {
		Score score = scoreService.findMinRecord(id);
		return ResponseEntity.ok().body(score);
	}
	
	@RequestMapping(value = "max-record-player/{id}", method = RequestMethod.GET)
	public ResponseEntity<Score> findMaxRecord(@PathVariable Long id) {
		Score score = scoreService.findMaxRecord(id);
		return ResponseEntity.ok().body(score);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Score score) {
		score = scoreService.insert(score);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(score.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Score score, @PathVariable Long id) {
		score.setId(id);
		score = scoreService.update(score);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		scoreService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Score>> findAll(){
		List<Score> scores = scoreService.findAll();
		return ResponseEntity.ok().body(scores);
	}
	
}
