package br.com.scaffold.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scaffold.api.models.Usuario;
import br.com.scaffold.api.security.ApiUserDetailsRepository;

@RestController
@RequestMapping("/usuarios")
public class TesteResource {
	
	@Autowired
	private ApiUserDetailsRepository repository;
	
	@GetMapping
	public List<Usuario> lista() {
		return repository.findAll();
	}

}
