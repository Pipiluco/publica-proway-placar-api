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

import life.pifrans.models.Game;
import life.pifrans.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameResource {
	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Game> find(@PathVariable Long id) {
		Game game = gameService.find(id);
		return ResponseEntity.ok().body(game);
	}
	
	@RequestMapping(value = "season/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Game>> findGamesBySeasonId(@PathVariable Long id) {
		List<Game> games = gameService.findGamesBySeasonId(id);
		return ResponseEntity.ok().body(games);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Game game) {
		game = gameService.insert(game);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Game game, @PathVariable Long id) {
		game.setId(id);
		game = gameService.update(game);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		gameService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Game>> findAll(){
		List<Game> games = gameService.findAll();
		return ResponseEntity.ok().body(games);
	}
	
}
