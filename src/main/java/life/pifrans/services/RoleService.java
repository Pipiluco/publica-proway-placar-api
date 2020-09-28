package life.pifrans.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import life.pifrans.exceptions.errors.DataIntegrityException;
import life.pifrans.exceptions.errors.ObjectNotFoundException;
import life.pifrans.models.Role;
import life.pifrans.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	public Role find(Long id) {
		Optional<Role> object = roleRepository.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Role.class.getName()));
	}

	public Role insert(Role role) {
		try {
			role.setId(null);
			role = roleRepository.save(role);
			return role;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Falha ao inserir objeto! " + e.getRootCause());
		}
	}

	public Role update(Role role) {
		find(role.getId());
		roleRepository.save(role);
		return role;
	}

	public void delete(Long id) {
		find(id);
		try {
			roleRepository.deleteById(id);
		} catch (Exception e) {
			e.getCause();
		}
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}
}
