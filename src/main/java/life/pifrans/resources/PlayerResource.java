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

import life.pifrans.models.Player;
import life.pifrans.services.PlayerService;

/**
 * Classe com endpoints referente a {@link Player}
 */
@RestController
@RequestMapping(value = "/players")
public class PlayerResource {
	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Player> find(@PathVariable Long id) {
		Player player = playerService.find(id);
		return ResponseEntity.ok().body(player);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Player player) {
		player = playerService.insert(player);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(player.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Player player, @PathVariable Long id) {
		player.setId(id);
		player = playerService.update(player);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		playerService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Player>> findAll(){
		List<Player> players = playerService.findAll();
		return ResponseEntity.ok().body(players);
	}
	
}
