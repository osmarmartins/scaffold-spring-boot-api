package br.com.scaffold.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.scaffold.api.models.Usuario;

@Service
public class ApiUserDetailsService implements UserDetailsService {

	@Autowired
	private ApiUserDetailsRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findOneByLogin(login);
		if(usuario == null) { 
			throw new UsernameNotFoundException("Usuário e/ou senha incorretos"); 
		}
		return new ApiUserDetails(usuario, usuario.getAuthorities());
	}

}
