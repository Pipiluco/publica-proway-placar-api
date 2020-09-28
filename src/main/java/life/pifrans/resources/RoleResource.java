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

import life.pifrans.models.Role;
import life.pifrans.services.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleResource {
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Role> find(@PathVariable Long id) {
		Role role = roleService.find(id);
		return ResponseEntity.ok().body(role);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Role role) {
		role = roleService.insert(role);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Role role, @PathVariable Long id) {
		role.setId(id);
		role = roleService.update(role);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		roleService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Role>> findAll(){
		List<Role> roles = roleService.findAll();
		return ResponseEntity.ok().body(roles);
	}
	
}
