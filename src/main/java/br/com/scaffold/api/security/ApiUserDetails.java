package br.com.scaffold.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.scaffold.api.models.Usuario;

public class ApiUserDetails extends User {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public ApiUserDetails(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getLogin(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
