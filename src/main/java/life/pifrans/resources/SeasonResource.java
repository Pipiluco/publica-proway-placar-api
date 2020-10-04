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

import life.pifrans.models.Season;
import life.pifrans.services.SeasonService;

/**
 * Classe com endpoints referente a {@link Season}
 */
@RestController
@RequestMapping(value = "/seasons")
public class SeasonResource {
	@Autowired
	private SeasonService seasonService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Season> find(@PathVariable Long id) {
		Season season = seasonService.find(id);
		return ResponseEntity.ok().body(season);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Season season) {
		season = seasonService.insert(season);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(season.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Season season, @PathVariable Long id) {
		season.setId(id);
		season = seasonService.update(season);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		seasonService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Season>> findAll(){
		List<Season> seasons = seasonService.findAll();
		return ResponseEntity.ok().body(seasons);
	}
	
}
