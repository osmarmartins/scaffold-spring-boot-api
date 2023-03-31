package br.com.scaffold.api.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.scaffold.api.enums.Ativo;

@Entity
@Table(name = "login_usuario")
public class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;

	private String nome;

	private String login;

	private String email;

	private String senha;

	@Enumerated
	private Ativo ativo;

	@Version
	private Integer versao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="login_usuario_perfil",
		joinColumns = { @JoinColumn(name = "id_usuario") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_perfil")})
	@JsonIgnore
	private List<Perfil> perfis = new ArrayList<>();	
	
	
	// Sobrescrita dos métodos UserDetails
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Autorizacao> autorizacoes = new HashSet<>();
		if (this.perfis == null) {
			return Collections.emptySet();
		}
		for (Perfil perfil : this.perfis) {
			autorizacoes.addAll(perfil.getAutorizacoes());
		}
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.ativo.equals(Ativo.ATIVO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	public String listaPerfis() {
		String lista = "";
		for (Perfil perfil : perfis) {
			lista = lista + ", " + perfil.getDescricao().toUpperCase();
		}
		return lista.substring(2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login + ", email=" + email + ", senha=" + senha
				+ ", ativo=" + ativo + ", versao=" + versao + "]";
	}

}
